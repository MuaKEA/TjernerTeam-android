package dk.nodes.template.presentation.ui.shift

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dk.nodes.template.models.FacebookUser
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.extensions.observeNonNull
import dk.nodes.template.presentation.ui.base.BaseActivity
import dk.nodes.template.presentation.ui.facebookUser.ShiftFragment
import dk.nodes.template.presentation.ui.main.MainActivityViewModel
import dk.nodes.template.presentation.ui.main.MainActivityViewState
import kotlinx.android.synthetic.main.activity_shift_details.*
import timber.log.Timber

class ShiftEndActivity : BaseActivity(),  BottomNavigationView.OnNavigationItemSelectedListener {

    private val viewModel by viewModel<MainActivityViewModel>()
    lateinit var shiftFragment: Fragment
    lateinit var completedShiftFragment: Fragment
    private var shownMenu: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shift_details)

        val bundle: Bundle? = intent.extras

        if (bundle?.getParcelable<FacebookUser?>("user") != null) {

            val user: FacebookUser? = (bundle.getParcelable<FacebookUser?>("user"))
            user?.let { viewModel.saveUser(it) }
        }

        val facebookUser = intent.getParcelableExtra<FacebookUser>("facebookUser")
        shiftFragment = ShiftFragment.newInstance(facebookUser)

        viewModel.viewState.observeNonNull(this) { state ->
            handleErrors(state)
        }

        top_shiftoverview_menu.setOnNavigationItemSelectedListener(this)
        completedShiftFragment = MessageFragment.newInstance()

        supportFragmentManager.beginTransaction()
                .add(R.id.shift_frame, shiftFragment, "1")
                .add(R.id.shift_frame,completedShiftFragment,"2")
                .show(shiftFragment)
                .hide(completedShiftFragment)
                .commit()

    }

    override fun onNavigationItemSelected(item: MenuItem) : Boolean {

        if(shownMenu == item.itemId) return false
        when (item.itemId) {
            R.id.navigation_upcoming_shift -> {
                supportFragmentManager.beginTransaction()
                        .show(shiftFragment)
                        .hide(completedShiftFragment)
                        .commit()
            }


            R.id.navigation_completed_shift-> {
                supportFragmentManager.beginTransaction()
                        .hide(shiftFragment)
                        .show(completedShiftFragment)
                        .commit()
            }
        }
        shownMenu = item.itemId


        return true
    }

    private fun handleErrors(state: MainActivityViewState) {
        state.viewError?.let {
            Timber.e("Something went wrong")
        }
    }
}

