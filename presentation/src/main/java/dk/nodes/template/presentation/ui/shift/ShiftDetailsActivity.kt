package dk.nodes.template.presentation.ui.shift

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.facebook.CallbackManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import dk.nodes.template.models.Shift
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.ui.Login.FacebookActivity
import dk.nodes.template.presentation.ui.base.BaseActivity
import dk.nodes.template.presentation.ui.main.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_shift_details_activity.*
import net.hockeyapp.android.metrics.model.User

class ShiftDetailsActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val viewModel by viewModel<MainActivityViewModel>()
    lateinit var Jobfragment: Fragment
    lateinit var message_fragment: Fragment
    var shift: Shift ? = null
    private var shownMenu: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shift_details_activity)

        var bundle :Bundle ?=intent.extras

        if(bundle  != null){

            shift =bundle.getParcelable<Shift>("shift") as Shift

        }

        top_shiftoverview_menu.setOnNavigationItemSelectedListener(this)
        Jobfragment = Job_fragment.newInstance(shift!!)
        message_fragment = Message_fragment.newInstance()
        supportFragmentManager.beginTransaction()
                .add(R.id.shift_frame, Jobfragment, "1")
                .add(R.id.shift_frame,message_fragment,"2")
                .show(message_fragment)
                .hide(Jobfragment)
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


}

