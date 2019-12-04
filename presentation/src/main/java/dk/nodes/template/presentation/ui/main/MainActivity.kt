package dk.nodes.template.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.material.bottomnavigation.BottomNavigationView
import dk.nodes.template.models.FacebookUser
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.extensions.observeNonNull
import dk.nodes.template.presentation.ui.Login.FacebookActivity
import dk.nodes.template.presentation.ui.base.BaseActivity
import dk.nodes.template.presentation.ui.shift.ShiftOverviewFragment
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener{
    private val viewModel by viewModel<MainActivityViewModel>()

    private var shownMenu: Int = 0
    lateinit var shiftOverviewFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var bundle :Bundle ?=intent.extras

        if(bundle  != null){

            val user : FacebookUser =bundle.getParcelable<FacebookUser>("user") as FacebookUser
                viewModel.saveUser(user)
        }



        viewModel.viewState.observeNonNull(this){
            state->
            handleErrors(state)
        }
        val accessToken = AccessToken.getCurrentAccessToken()


        Log.d("testio", accessToken.userId)



        bottomNavigation_Main.setOnNavigationItemSelectedListener(this)


        shiftOverviewFragment =  ShiftOverviewFragment.newInstance()


        supportFragmentManager.beginTransaction()
                .add(R.id.main_frame, shiftOverviewFragment,"1")
                .show(shiftOverviewFragment)
                .commit()
    }

    private fun handleErrors(state: MainActivityViewState) {
            state.viewError?.let {
                Toast.makeText(this,"something went wrong",Toast.LENGTH_LONG).show()
            }

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

}



















