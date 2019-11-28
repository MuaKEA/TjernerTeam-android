package dk.nodes.template.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.ui.Login.FacebookActivity
import dk.nodes.template.presentation.ui.base.BaseActivity
import dk.nodes.template.presentation.ui.shift.ShiftOverviewFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener{

    private var shownMenu: Int = 0
    lateinit var shiftOverviewFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        bottomNavigation_Main.setOnNavigationItemSelectedListener(this)


        shiftOverviewFragment =  ShiftOverviewFragment.newInstance()


        supportFragmentManager.beginTransaction()
                .add(R.id.main_frame, shiftOverviewFragment,"1")
                .show(shiftOverviewFragment)
                .commit()
    }




    override fun onNavigationItemSelected(item: MenuItem) : Boolean {



        shownMenu = item.itemId


        return false
    }

}



















