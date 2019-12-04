package dk.nodes.template.presentation.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.ui.base.BaseActivity
import dk.nodes.template.presentation.ui.chat.ChatFragment
import dk.nodes.template.presentation.ui.shift.ShiftOverviewFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener{

    private var shownMenu: Int = 0
    lateinit var shiftOverviewFragment: Fragment
    lateinit var chatFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        bottomNavigation_Main.setOnNavigationItemSelectedListener(this)


        shiftOverviewFragment =  ShiftOverviewFragment.newInstance()
        chatFragment =  ChatFragment.newInstance()


        supportFragmentManager.beginTransaction()
                .add(R.id.main_frame, shiftOverviewFragment,"1")
                .commit()

        supportFragmentManager.beginTransaction()
                .add(R.id.main_frame, chatFragment,"2")
    }


    override fun onNavigationItemSelected(item: MenuItem) : Boolean {
        if(shownMenu == item.itemId) return false
        when (item.itemId) {
            R.id.notification->
                supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, shiftOverviewFragment)
                        .commit()

            R.id.chat ->
                supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, chatFragment)
                        .commit()
        }

        shownMenu = item.itemId


        return true
    }

}



















