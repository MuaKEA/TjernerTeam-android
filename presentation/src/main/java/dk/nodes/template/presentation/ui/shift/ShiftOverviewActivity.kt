package dk.nodes.template.presentation.ui.shift

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.extensions.observeNonNull
import dk.nodes.template.presentation.ui.base.BaseActivity
import dk.nodes.template.presentation.ui.main.ShiftOverviewAdapter
import kotlinx.android.synthetic.main.activity_shift_overview.*
import kotlinx.android.synthetic.main.shift_recyclerview_row.*
import timber.log.Timber

class ShiftOverviewActivity : BaseActivity(), View.OnClickListener {
private val viewModel by viewModel<ShiftViewModel>()

    private var adapter: ShiftOverviewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shift_overview)
        fetch_shift_btn.setOnClickListener(this)
        adapter = ShiftOverviewAdapter(this,R.layout.shift_recyclerview_row)

        viewModel.viewState.observeNonNull(this){
            state->
            handleShift(state)
        }
    }

    override fun onClick(v: View?) {
        refreshShifts()
    }

    private fun refreshShifts() {
        viewModel.fetchShifts()
    }

    private fun handleShift(state: ShiftViewState) {
        state.let {

            state.shiftOverviewList?.let { it -> adapter?.addShifts(it) }
            adapter?.notifyDataSetChanged()
            Timber.e("Adapter item count: %s", adapter?.itemCount)

            Timber.e( state.shiftOverviewList.toString())
            updateRecyclerView()
        }
    }

    fun updateRecyclerView(){
        rv_shift_overview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv_shift_overview.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

    private fun addItemOnclick() {
        adapter?.onItemClickedListener = {shift ->
            // todo
            // new activity
        }
    }


}
