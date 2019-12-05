package dk.nodes.template.presentation.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.extensions.observeNonNull
import dk.nodes.template.presentation.ui.base.BaseActivity
import dk.nodes.template.presentation.ui.menu.MenuFragment
import dk.nodes.template.presentation.ui.shift.ShiftOverviewFragment
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener{
    private val viewModel by viewModel<MainActivityViewModel>()

    private var shownMenu: Int = 0
    lateinit var shiftOverviewFragment: Fragment
    lateinit var menuFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(header as Toolbar?)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        var bundle: Bundle? = intent.extras

        /*if (bundle != null) {

            val user: FacebookUser = (bundle.getParcelable<FacebookUser?>("user") as FacebookUser?)!!
            viewModel.saveUser(user)
        }*/



        viewModel.viewState.observeNonNull(this) { state ->
            handleErrors(state)
        }

        bottomNavigation_Main.setOnNavigationItemSelectedListener(this)


        shiftOverviewFragment = ShiftOverviewFragment.newInstance()
        menuFragment = MenuFragment.newInstance()


        supportFragmentManager.beginTransaction()
                .add(R.id.main_frame, shiftOverviewFragment, "1")
                .add(R.id.main_frame, menuFragment, "2")
                .show(shiftOverviewFragment)
                .hide(menuFragment)
                .commit()
    }



    override fun onNavigationItemSelected(item: MenuItem) : Boolean {
        if(shownMenu == item.itemId) return false
        when (item.itemId) {
            R.id.available_jobs -> {
                supportFragmentManager.beginTransaction()
                        .show(shiftOverviewFragment)
                        .hide(menuFragment).commit()
            }
            R.id.other_options -> {
                supportFragmentManager.beginTransaction()
                        .show(menuFragment)
                        .hide(shiftOverviewFragment)
                        .commit()
            }
        }
        shownMenu = item.itemId


        return true
    }


private fun handleErrors(state: MainActivityViewState) {
    state.viewError?.let {
        Toast.makeText(this, "something went wrong", Toast.LENGTH_LONG).show()
    }
}
}




















