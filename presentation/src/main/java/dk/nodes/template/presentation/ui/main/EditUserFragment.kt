package dk.nodes.template.presentation.ui.main

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.FacebookActivity
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import dk.nodes.template.models.FacebookUser
import dk.nodes.template.models.PostCode
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.extensions.observeNonNull
import dk.nodes.template.presentation.ui.base.BaseFragment
import dk.nodes.template.presentation.ui.shift.JobFragment
import kotlinx.android.synthetic.main.edit_user_fragment.*
import timber.log.Timber

import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.activity_main.*
import com.facebook.GraphResponse
import kotlin.system.exitProcess




class EditUserFragment : BaseFragment(), View.OnClickListener {


    private val viewModel by viewModel<MainActivityViewModel>()
    private var listener: JobFragment.OnFragmentInteractionListener? = null
    private lateinit var maincontext: Context
    private var fcbUcer: FacebookUser? = null


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
        update_btn.setOnClickListener(this)
        delete_btn.setOnClickListener(this)


        viewModel.viewState.observeNonNull(this) { state ->
            handleUser(state)

        }


    }

    fun isLoggedIn(): String {
        val accessToken = AccessToken.getCurrentAccessToken()
        if (accessToken != null) {

            return accessToken.userId
        }
        var intent = Intent(maincontext, FacebookActivity::class.java)
        startActivity(intent)
        return ""
    }

    fun getAccesToken(): AccessToken {
        val accessToken = AccessToken.getCurrentAccessToken()

        return accessToken


    }

    private fun handleUser(state: MainActivityViewState) {
        state.facebookUser?.let { user ->
            Timber.e("EditUser user: " + user)
            if(user.fullName == null){
                viewModel.fetchUser(isLoggedIn())
            }

            Timber.e(user.toString())
            name_edittext.setText(user.fullName.toString())
            email_edittext.setText(user.email)
            adress_edittext.setText(user.address)
            city_edittext.setText(user.city)
            phoneNumb_edittext.setText(user.phoneNumber.toString())
            postCode_edittext.setText(user.postCode?.postCode.toString())
            cpr_edit.setText(user.cprNumber + "-XXXX")
            regNumber_edittext.setText(user.regNumber.toString())
            accountnr_edittext.setText(user.accountNumber.toString())
            dateofbirth_edittext.setText(user.dateOfBirth)

            when (user.gender) {

                "Male" -> radioM.isChecked = true
                "Female" -> radioF.isChecked = true
            }

            fcbUcer = user

        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {

            update_btn.id -> {
                fcbUcer?.address = adress_edittext.text.toString()
                fcbUcer?.fullName = name_edittext.text.toString()
                fcbUcer?.city = city_edittext.text.toString()
                fcbUcer?.phoneNumber = phoneNumb_edittext.text.toString().toLong()
                if(postCode_edittext.text?.contains("[dlksfml]")!!){



                }
                val postCode = PostCode(postCode_edittext.text.toString().toInt())
                fcbUcer?.postCode = postCode
                fcbUcer?.regNumber = regNumber_edittext.text.toString().toInt()
                fcbUcer?.accountNumber = accountnr_edittext.text.toString().toLong()
                fcbUcer?.dateOfBirth = dateofbirth_edittext.text.toString()
                fcbUcer?.cprNumber = cpr_edit.text.toString()
                if (radioF.isChecked) {
                    fcbUcer?.gender = radioF.text.toString()

                } else {

                    fcbUcer?.gender = radioM.text.toString()

                }



                viewModel.updateUser(fcbUcer!!)
            }

            delete_btn.id -> {
                val builder = AlertDialog.Builder(maincontext)

                builder.setTitle("Slet din konto")

                builder.setMessage("Er du sikker på at du ville slette din konto?")

                builder.setPositiveButton("JA") { dialog, which ->
                    fcbUcer?.facebookId?.let { viewModel.deleteUser(it) }
                    GraphRequest(getAccesToken(), "/me/permissions", null, HttpMethod.DELETE).executeAsync()
                    disconnectFromFacebook()
                    getActivity()?.finish()
                }


                builder.setNegativeButton("ANNULLER") { dialog, which ->
                dialog.dismiss()
                }

                val dialog: AlertDialog = builder.create()

                dialog.show()


            }
        }
    }

    fun disconnectFromFacebook() {
        GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, GraphRequest.Callback { LoginManager.getInstance().logOut() }).executeAsync()

        if (AccessToken.getCurrentAccessToken() == null) {
            Timber.e("logged out")
            return  // already logged out
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

