package dk.nodes.template.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.ui.Login.CustomTabActivity
import dk.nodes.template.presentation.ui.Login.FacebookActivity
import dk.nodes.template.presentation.ui.Login.LoginActivity
import dk.nodes.template.presentation.ui.base.BaseActivity
import dk.nodes.template.presentation.ui.shift.ShiftOverviewFragment
import kotlinx.android.synthetic.main.activity_facebook.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.login_button
import org.json.JSONException
import timber.log.Timber
import java.util.*


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{
    val callbackManager = CallbackManager.Factory.create()

    private var shownMenu: Int = 0
    lateinit var shiftOverviewFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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



        bottomNavigation_Main.setOnNavigationItemSelectedListener(this)


        shiftOverviewFragment =  ShiftOverviewFragment.newInstance()


        supportFragmentManager.beginTransaction()
                .add(R.id.main_frame, shiftOverviewFragment,"1")
                .show(shiftOverviewFragment)
                .commit()
    }




    override fun onNavigationItemSelected(item: MenuItem) : Boolean {

                if(shownMenu == item.itemId) return false
        when (item.itemId) {
            R.id.navigation_profile -> {
                startActivity(Intent(this, FacebookActivity::class.java))
            }


            R.id.notification->
                supportFragmentManager.beginTransaction()
                        .show(shiftOverviewFragment)
                        .commit()

        }
        shownMenu = item.itemId


        return false
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



















