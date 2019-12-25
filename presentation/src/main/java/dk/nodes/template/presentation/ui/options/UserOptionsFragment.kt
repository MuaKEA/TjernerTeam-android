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
import dk.nodes.template.presentation.extensions.observeNonNull
import dk.nodes.template.presentation.ui.base.BaseFragment
import dk.nodes.template.presentation.ui.main.MainActivityViewModel
import dk.nodes.template.presentation.ui.main.MainActivityViewState
import kotlinx.android.synthetic.main.fragment_user_options.*
import timber.log.Timber
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class UserOptionsFragment : BaseFragment(), View.OnClickListener {
    private val viewModel by viewModel<MainActivityViewModel>()
    private var mainContext: Context? = null
    private var userIsSnoozed: Boolean = false
    private var snoozeDaysLeft: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchUser(isLoggedIn())
        viewModel.viewState.observeNonNull(this) { state ->
            handleUser(state)
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

    private fun handleUser(state: MainActivityViewState) {
        state.facebookUser?.let { user ->
            viewModel.fetchUser(isLoggedIn())
            userIsSnoozed = checkUserSnoozeStatus(user)
        }
    }

    fun checkUserSnoozeStatus(user: FacebookUser): Boolean {
        val snoozeEndDate = user.notificationSnoozeEndDate
        val currentDate = LocalDate.now()
        if (snoozeEndDate == null) {
            Timber.e("null")
            snoozeDaysLeft = "Sluk"
            return true
        }
        else if (LocalDate.parse(snoozeEndDate).isAfter(currentDate)) {
            Timber.e("after current date")
            snoozeDaysLeft = currentDate.until(LocalDate.parse(snoozeEndDate),ChronoUnit.DAYS).toString()
            return true
        }else {
            Timber.e("before or at current date")
            return false
        }
    }

    override fun onClick(v: View) {
        if (userIsSnoozed) {
            val cancelSnoozePopup = CancelSnoozeNotificationPopUpFragment()
            cancelSnoozePopup.showPopupWindow(v, viewModel, snoozeDaysLeft)
        } else {
            val snoozePopup = SnoozeNotificationPopUpFragment()
            snoozePopup.showPopupWindow(v, viewModel)
        }
    }
}