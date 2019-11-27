package dk.nodes.template.presentation.ui.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.CallbackManager
import dk.nodes.template.presentation.R
import kotlinx.android.synthetic.main.activity_facebook.*
import java.util.*
import com.facebook.FacebookException
import com.facebook.AccessToken
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.content.Intent
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import org.json.JSONException
import com.facebook.GraphResponse
import org.json.JSONObject
import com.facebook.GraphRequest
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.squareup.picasso.Picasso
import timber.log.Timber
import java.util.*



class FacebookActivity : AppCompatActivity() {

    val callbackManager = CallbackManager.Factory.create()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facebook)


        var displayName = display_name
        var emailID = email
        var displayImage = image_view
        var loginButton = login_button
         loginButton.setReadPermissions(Arrays.asList("email", "public_profile","id"));


        // Creating CallbackManager
        // Registering CallbackManager with the LoginButton
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                // Retrieving access token using the LoginResult
                val accessToken = loginResult.accessToken
                useLoginInformation(accessToken)
            }

            override fun onCancel() {}
            override fun onError(error: FacebookException) {}
        })




    }

    public override fun onActivityResult(requestCode: Int, resulrCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resulrCode, data)
        super.onActivityResult(requestCode, resulrCode, data)
    }

    private fun useLoginInformation(accessToken: AccessToken) {
        /**
         * Creating the GraphRequest to fetch user details
         * 1st Param - AccessToken
         * 2nd Param - Callback (which will be invoked once the request is successful)
         */
        val request = GraphRequest.newMeRequest(accessToken) { `object`, response ->
            //OnCompleted is invoked once the GraphRequest is successful
            try {
                val name = `object`.getString("name")
                val emails = `object`.getString("email")
//                val id = `object`.getString("id")
              Timber.e(name)
                val image = `object`.getJSONObject("picture").getJSONObject("data").getString("url")
                display_name.setText(name)
                email.setText(emails.toString())
             //   Timber.e(id)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        // We set parameters to the GraphRequest using a Bundle.
        val parameters = Bundle()
        parameters.putString("fields", "id,name,email,picture.width(200)")
        request.parameters = parameters

        if(parameters != null){
            Timber.e(parameters.get("fields").toString())


        }


        request.executeAsync()


        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val accessToken = loginResult.accessToken
                useLoginInformation(accessToken)

            }

            override fun onCancel() {}
            override fun onError(error: FacebookException) {}
        })


    }



}
