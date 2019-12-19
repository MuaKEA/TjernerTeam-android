package dk.nodes.template.presentation.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dk.nodes.template.models.FacebookUser

import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.extensions.observeNonNull
import dk.nodes.template.presentation.ui.base.BaseFragment
import dk.nodes.template.presentation.ui.base.BaseFragment_MembersInjector
import dk.nodes.template.presentation.ui.shift.JobFragment
import dk.nodes.template.presentation.ui.shift.MessageFragment
import kotlinx.android.synthetic.main.edit_user_fragment.*
import timber.log.Timber
import com.facebook.AccessToken
import com.facebook.FacebookActivity


class EditUserFragment : BaseFragment() {
    private val viewModel by viewModel<MainActivityViewModel>()
    private var listener: JobFragment.OnFragmentInteractionListener? = null
    private lateinit var maincontext : Context
    companion object {
        fun newInstance() = EditUserFragment()
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_user_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchUser(isLoggedIn())


        viewModel.viewState.observeNonNull(this) { state ->
           Log.d("shadush","working")
            handleUser(state)

        }
        }
    fun isLoggedIn(): String {
        val accessToken = AccessToken.getCurrentAccessToken()
        if(accessToken != null) {
            Log.d("shadush",accessToken.userId)

            return accessToken.userId
        }
        var intent = Intent(maincontext,FacebookActivity::class.java)
        startActivity(intent)
        return ""
    }

    private fun handleUser(state: MainActivityViewState) {
        state.facebookUser?.let { user ->
            Log.d("shadush",user.toString())

            Timber.e(user.toString())
        name_edittext.setText(user.fullName.toString())
        adress_edittext.setText(user.email)
        city_edittext.setText(user.city)
        phoneNumb_edittext.setText(user.phoneNumber.toString())
        postCode_edittext.setText(user.postCode?.postCode.toString())


        }
    }


        fun onButtonPressed(uri: Uri) {
            listener?.onFragmentInteraction(uri)
        }


        override fun onDetach() {
            super.onDetach()
            listener = null
        }


        interface OnFragmentInteractionLitener {
            fun onFragmentInteraction(uri: Uri)
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        maincontext = context
    }
}
