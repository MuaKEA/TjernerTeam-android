package dk.nodes.template.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.ui.base.BaseActivity
import dk.nodes.template.presentation.ui.shift.ShiftOverviewActivity


class MainActivity : BaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this,ShiftOverviewActivity::class.java))


    }


}



















