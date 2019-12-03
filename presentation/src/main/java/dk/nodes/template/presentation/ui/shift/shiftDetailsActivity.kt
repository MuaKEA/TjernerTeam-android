package dk.nodes.template.presentation.ui.shift

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.material.bottomnavigation.BottomNavigationView
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.ui.Login.FacebookActivity
import dk.nodes.template.presentation.ui.base.BaseActivity
import dk.nodes.template.presentation.ui.main.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import java.util.*

class shiftDetailsActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener{
    private val viewModel by viewModel<MainActivityViewModel>()
    lateinit var Job_fragment: Fragment
    lateinit var Message_fragment: Fragment
    val callbackManager = CallbackManager.Factory.create()
    private var shownMenu: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shift_details_activity)


        bottomNavigation_Main.setOnNavigationItemSelectedListener(this)
        Job_fragment = ShiftOverviewFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .add(R.id.shift_frame, Job_fragment, "1")
                .show(Job_fragment)
                .commit()

    }

    override fun onNavigationItemSelected(item: MenuItem) : Boolean {

        if(shownMenu == item.itemId) return false
        when (item.itemId) {
            R.id.navigation_profile ->
                startActivity(Intent(this, FacebookActivity::class.java))



            R.id.notification->
                supportFragmentManager.beginTransaction()
                        .show(Job_fragment)
                        .commit()

        }
        shownMenu = item.itemId


        return false
    }



}

