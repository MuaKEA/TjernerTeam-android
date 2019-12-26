package dk.nodes.template.presentation.ui.options

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import com.facebook.AccessToken
import dk.nodes.template.models.FacebookUser
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.extensions.observe
import dk.nodes.template.presentation.extensions.observeNonNull
import dk.nodes.template.presentation.ui.base.BaseFragment
import dk.nodes.template.presentation.ui.main.MainActivityViewModel
import dk.nodes.template.presentation.ui.main.MainActivityViewState
import dk.nodes.template.repositories.FacebookRespository
import kotlinx.android.synthetic.main.fragment_user_options.*
import timber.log.Timber
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class UserOptionsFragment : BaseFragment(), View.OnClickListener {
    private val viewModel by viewModel<MainActivityViewModel>()
    private var mainContext: Context? = null
    private var userIsSnoozed: Boolean = false
    private var snoozeDaysLeft: String = ""
    private var facebookUser: FacebookUser? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchUser(isLoggedIn())
        viewModel.viewState.observeNonNull(this) { state ->
            handleUser(state)
            handleStatus(state)
        }

        chat_btn.setOnClickListener {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(mainContext, Uri.parse("https://m.facebook.com/thomas.tjenerteamet"))
        }

        faq_btn.setOnClickListener {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(mainContext, Uri.parse("http://www.tjenerteamet.dk/faq-app.html"))
        }

        snooze_btn.setOnClickListener(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_options, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainContext = context
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                UserOptionsFragment().apply {
                }
    }

    fun isLoggedIn(): String {
        val accessToken = AccessToken.getCurrentAccessToken()
        if (accessToken != null) {
            return accessToken.userId
        }
        return "-1"
    }

    private fun handleUser(state: MainActivityViewState?) {
        state?.facebookUser?.let { user ->
            facebookUser = user
        }
    }

    private fun handleStatus(state: MainActivityViewState?) {
        state?.snoozeStatusAndDaysLeft?.let { snoozeArray ->
            userIsSnoozed = snoozeArray[0] as Boolean
            snoozeDaysLeft = snoozeArray[1] as String
            Timber.e("array: " + snoozeArray.toString())
        }
    }

    override fun onClick(v: View) {
        viewModel.fetchUser(isLoggedIn())
        Timber.e("Facebookuser: " + facebookUser + " snoozeDaysLeft: " + snoozeDaysLeft)
        viewModel.getSnoozeStatus(facebookUser)
        if (userIsSnoozed) {
            val cancelSnoozePopup = CancelSnoozeNotificationPopUpFragment()
            cancelSnoozePopup.showPopupWindow(v, viewModel, snoozeDaysLeft)
        } else {
            val snoozePopup = SnoozeNotificationPopUpFragment()
            snoozePopup.showPopupWindow(v, viewModel)
        }
    }
}