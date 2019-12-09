package dk.nodes.template.presentation.ui.Login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import dk.nodes.template.models.FacebookUser
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.ui.shift.shiftDetailsActivity
import kotlinx.android.synthetic.main.activity_facebook.*
import org.json.JSONException
import timber.log.Timber
import java.util.*
import com.facebook.AccessToken
import com.facebook.login.LoginManager
import dk.nodes.template.models.Shift


class FacebookActivity : AppCompatActivity() {

    val callbackManager = CallbackManager.Factory.create()
    lateinit var shiftDetailsActivityIntent: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facebook)
        updateWithToken(AccessToken.getCurrentAccessToken())

        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        return@OnCompleteListener
                    }
                })

        shiftDetailsActivityIntent = Intent(this, shiftDetailsActivity::class.java)

        val loginButton = login_button
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"))
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Timber.e(loginResult.toString())
                val accessToken = loginResult.accessToken
                useLoginInformation(accessToken)
            }

            override fun onCancel() {}
            override fun onError(error: FacebookException) {}


        })


    }

    private fun updateWithToken(currentAccessToken: AccessToken?) {

        if (currentAccessToken != null) {
            LoginManager.getInstance().logOut()
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)

        super.onActivityResult(requestCode, resultCode, data)


    }

    private fun useLoginInformation(accessToken: AccessToken) {
        val request = GraphRequest.newMeRequest(accessToken) { `object`, response ->
            try {
                val name = `object`.getString("name")
                val emails = `object`.getString("email")
                val id = `object`.getString("id")
                shiftDetailsActivityIntent.putExtra("shift", intent.getParcelableExtra<Shift>("shift"))
                shiftDetailsActivityIntent.putExtra("user", FacebookUser(id.toLong(), name, emails, null, null, null, null))

                startActivity(shiftDetailsActivityIntent)
                finish()

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        val parameters = Bundle()
        parameters.putString("fields", "id,name,email,picture.width(200)")
        request.parameters = parameters

        request.executeAsync()

        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val accessToken = loginResult.accessToken

                useLoginInformation(accessToken)
                startActivity(shiftDetailsActivityIntent)
            }

            override fun onCancel() {}
            override fun onError(error: FacebookException) {}
        })


    }
}