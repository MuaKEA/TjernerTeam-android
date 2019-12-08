package dk.nodes.template.presentation.ui.shift

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.facebook.CallbackManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import dk.nodes.template.models.FacebookUser
import dk.nodes.template.models.Shift
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.extensions.observeNonNull
import dk.nodes.template.presentation.ui.Login.FacebookActivity
import dk.nodes.template.presentation.ui.base.BaseActivity
import dk.nodes.template.presentation.ui.main.MainActivityViewModel
import dk.nodes.template.presentation.ui.main.MainActivityViewState
import kotlinx.android.synthetic.main.activity_shift_details_activity.*
import kotlinx.android.synthetic.main.fragment_job_fragment.*
import kotlinx.android.synthetic.main.shift_recyclerview_row.*
import timber.log.Timber
import java.time.LocalDate

class shiftDetailsActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val viewModel by viewModel<MainActivityViewModel>()
    lateinit var Jobfragment: Fragment
    lateinit var message_fragment: Fragment


    private var shownMenu: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shift_details_activity)

        var bundle: Bundle? = intent.extras

        if (bundle?.getParcelable<FacebookUser?>("user") != null) {

            val user: FacebookUser? = (bundle.getParcelable<FacebookUser?>("user") as FacebookUser?)
            user?.let { viewModel.saveUser(it) }
        }

        val shift = intent.getParcelableExtra<Shift>("shift")
        Jobfragment = Job_fragment.newInstance(shift)

        Timber.e("Facebook intent information: " + intent.getParcelableExtra<Shift>("shift") + "Shift address: " + shift.address)




        viewModel.viewState.observeNonNull(this) { state ->
            handleErrors(state)
        }

        top_shiftoverview_menu.setOnNavigationItemSelectedListener(this)
        message_fragment = Message_fragment.newInstance()
        supportFragmentManager.beginTransaction()
                .add(R.id.shift_frame, Jobfragment, "1")
                .add(R.id.shift_frame,message_fragment,"2")
                .show(Jobfragment)
                .hide(message_fragment)
                .commit()

    }

    override fun onNavigationItemSelected(item: MenuItem) : Boolean {

        if(shownMenu == item.itemId) return false
        when (item.itemId) {
            R.id.navigation_job_info -> {
                supportFragmentManager.beginTransaction()
                        .show(Jobfragment)
                        .hide(message_fragment)
                        .commit()
            }


            R.id.navigation_message-> {
                supportFragmentManager.beginTransaction()
                        .hide(Jobfragment)
                        .show(message_fragment)
                        .commit()
            }
        }
        shownMenu = item.itemId


        return false
    }

    private fun handleErrors(state: MainActivityViewState) {
        state.viewError?.let {
            Toast.makeText(this, "something went wrong", Toast.LENGTH_LONG).show()
        }
    }

}

