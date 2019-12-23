package dk.nodes.template.presentation.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dk.nodes.template.models.FacebookUser
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.extensions.observeNonNull
import dk.nodes.template.presentation.ui.base.BaseActivity
import dk.nodes.template.presentation.ui.options.UserOptionsFragment
import dk.nodes.template.presentation.ui.shift.ShiftOverviewFragment
import dk.nodes.template.presentation.ui.shift.ShiftEndFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener{
    private val viewModel by viewModel<MainActivityViewModel>()

    private var shownMenu: Int = 0
    lateinit var shiftOverviewFragment: Fragment
    lateinit var userOptionsFragment : Fragment
    lateinit var shiftEndFragment : Fragment
    lateinit var editUserFragment: EditUserFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(header as Toolbar?)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val bundle: Bundle? = intent.extras

        if (bundle?.getParcelable<FacebookUser?>("user") != null) {

            val user: FacebookUser? = (bundle.getParcelable<FacebookUser?>("user"))
            user?.let { viewModel.saveUser(it) }

        }

        viewModel.viewState.observeNonNull(this) { state ->
            handleErrors(state)
        }


        bottomnavigation_main.setOnNavigationItemSelectedListener(this)


        shiftOverviewFragment = ShiftOverviewFragment.newInstance()
        userOptionsFragment = UserOptionsFragment.newInstance()
        shiftEndFragment = ShiftEndFragment.newInstance()

        editUserFragment = EditUserFragment.newInstance()

        supportFragmentManager.beginTransaction()
                .add(R.id.main_frame, shiftOverviewFragment, "1")
                .add(R.id.main_frame, userOptionsFragment, "2")
                .add(R.id.main_frame, shiftEndFragment, "3")
                .add(R.id.main_frame, editUserFragment, "3")
                .show(shiftOverviewFragment)
                .hide(userOptionsFragment)
                .hide(shiftEndFragment)
                .hide(editUserFragment)
                .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem) : Boolean {
        if(shownMenu == item.itemId) return false
        when (item.itemId) {
            R.id.available_jobs -> {
                supportFragmentManager.beginTransaction()
                        .show(shiftOverviewFragment)
                        .hide(userOptionsFragment)
                        .hide(editUserFragment)
                        .hide(userOptionsFragment)
                        .hide(shiftEndFragment).commit()
            }
            R.id.other_options -> {
                supportFragmentManager.beginTransaction()
                        .show(userOptionsFragment)
                        .hide(shiftOverviewFragment)
                        .hide(editUserFragment)
                        .commit()
            }

            R.id.navigation_profile -> {
                supportFragmentManager.beginTransaction()
                        .show(editUserFragment)
                        .hide(shiftOverviewFragment)
                        .hide(userOptionsFragment)
                        .hide(shiftEndFragment).commit()
            }
            R.id.navigation_endjob -> {
                supportFragmentManager.beginTransaction()
                        .show(shiftEndFragment)
                        .hide(shiftOverviewFragment)
                        .hide(userOptionsFragment).commit()
            }
        }
            shownMenu = item.itemId


        return true
    }

private fun handleErrors(state: MainActivityViewState) {
    state.viewError?.let { error->
        Log.d("facebooktest", error.toString())

    }
}
}




















