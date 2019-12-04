package dk.nodes.template.presentation.ui.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginResult
import com.squareup.picasso.Picasso
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_facebook.*
import org.json.JSONException
import timber.log.Timber
import java.util.*


class FacebookActivity : AppCompatActivity() {

    val callbackManager = CallbackManager.Factory.create()
    lateinit var mainActivityIntent :Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facebook)
        mainActivityIntent = Intent(this,MainActivity::class.java)

        var loginButton = login_button
         loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));


        // Creating CallbackManager
        // Registering CallbackManager with the LoginButton
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
               Timber.e(loginResult.toString())

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

                Log.d("tupac" , "1")
                mainActivityIntent.putExtra("name",name);
                mainActivityIntent.putExtra("email",emails)
                mainActivityIntent.putExtra("id",id)
                startActivity(mainActivityIntent)

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        // We set parameters to the GraphRequest using a Bundle.
        val parameters = Bundle()
        parameters.putString("fields", "id,name,email,picture.width(200)")
        request.parameters = parameters
        Log.d("facebook" , "2")

        if(parameters != null){
           Log.d("tupac" , parameters.get("fields").toString())


        }


        request.executeAsync()


        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val accessToken = loginResult.accessToken



                useLoginInformation(accessToken)
                startActivity(mainActivityIntent)

                Log.d("tupac" , "3")

            }

            override fun onCancel() {}
            override fun onError(error: FacebookException) {}
        })


    }



}
