package dk.nodes.template.presentation.ui.shift

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.extensions.observeNonNull
import dk.nodes.template.presentation.ui.base.BaseFragment
import dk.nodes.template.presentation.ui.main.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_active_shifts.*



class ShiftEndFragment : BaseFragment() {

    private val viewModel by viewModel<MainActivityViewModel>()
    private var mainContext: Context? = null
    private var adapter: UserShiftAdapter? = null
    lateinit var UserShiftActivityIntent: Intent

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //adapter = mainContext?.let { UserShiftActivity(it, R.layout.shift_recyclerview_row) }
        //refreshShifts()
        addItemOnclick()

        viewModel.viewState.observeNonNull(this){
            state->
            //handleShift(state)
        }

        //val swipeLayout = swiperefresh as SwipeRefreshLayout
        //swipeLayout.setOnRefreshListener {
        //}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shift_end, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainContext = context
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }


    private fun addItemOnclick() {
        adapter?.onItemClickedListener = {shift ->
            UserShiftActivityIntent = Intent(mainContext, UserShiftActivity::class.java)
            UserShiftActivityIntent.putExtra("shift", shift)
            startActivity(UserShiftActivityIntent)
        }
    }

    /*private fun refreshShifts() {
        viewModel.fetchActiveShifts()
        if (swiperefresh.isRefreshing) {
            swiperefresh.isRefreshing = false
        }
    }*/


    companion object {
        @JvmStatic
        fun newInstance() =
                ShiftEndFragment().apply {

                }
    }

    fun updateRecyclerView(){
        rv_user_shift_overview.layoutManager = LinearLayoutManager(mainContext,LinearLayoutManager.VERTICAL,false)
        rv_user_shift_overview.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

}
