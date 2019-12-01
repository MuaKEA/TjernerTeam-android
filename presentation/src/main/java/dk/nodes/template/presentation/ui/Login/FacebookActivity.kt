package dk.nodes.template.presentation.ui.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginResult
import com.squareup.picasso.Picasso
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_facebook.*
import org.json.JSONException
import timber.log.Timber
import java.util.*


class FacebookActivity : AppCompatActivity() {

    val callbackManager = CallbackManager.Factory.create()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facebook)


        var loginButton = login_button
         loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));


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
                var name = `object`.getString("name")
                val emails = `object`.getString("email")
                val image = `object`.getJSONObject("picture").getJSONObject("data").getString("url")
                val id =  `object`.getString("id")
                Picasso.get().load(image).into(image_view)
                facebookId_txt.setText(id)
                Timber.e(id  + " " )
                display_name.setText(name)
                email.setText(emails.toString())
                Log.d("tupac" , "1")
              var s =  Intent(this, CustomTabActivity ::class.java)
               s.putExtra("name",name);
                s.putExtra("email",emails)
                s.putExtra("id",id)
                startActivity(s)

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        // We set parameters to the GraphRequest using a Bundle.
        val parameters = Bundle()
        parameters.putString("fields", "id,name,email,picture.width(200)")
        request.parameters = parameters
        Log.d("tupac" , "2")

        if(parameters != null){
           Log.d("tupac" , parameters.get("fields").toString())


        }


        request.executeAsync()


        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val accessToken = loginResult.accessToken
                useLoginInformation(accessToken)
                Log.d("tupac" , "3")

            }

            override fun onCancel() {}
            override fun onError(error: FacebookException) {}
        })


    }



}
