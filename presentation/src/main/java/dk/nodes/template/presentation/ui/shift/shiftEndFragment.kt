package dk.nodes.template.presentation.ui.shift

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.extensions.observeNonNull
import dk.nodes.template.presentation.ui.base.BaseFragment
import dk.nodes.template.presentation.ui.main.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_shift_overview.*


class shiftEndFragment : BaseFragment() {

    private val viewModel by viewModel<MainActivityViewModel>()
    private var mainContext: Context? = null
    private var adapter: ShiftOverviewAdapter? = null
    lateinit var shiftDetailsActivityIntent: Intent


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = mainContext?.let { ShiftOverviewAdapter(it, R.layout.fragment_shift_end) }
        //refreshShifts()
        //addItemOnclick()

        viewModel.viewState.observeNonNull(this){
            state->
            //handleShift(state)
        }

        val swipeLayout = swiperefresh as SwipeRefreshLayout
        swipeLayout.setOnRefreshListener {
            //refreshShifts()
        }
    }








}
