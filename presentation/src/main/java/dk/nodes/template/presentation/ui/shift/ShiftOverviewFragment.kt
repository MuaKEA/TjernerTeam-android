package dk.nodes.template.presentation.ui.shift

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dk.nodes.template.models.Shift
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.extensions.observeNonNull
import dk.nodes.template.presentation.ui.base.BaseActivity
import dk.nodes.template.presentation.ui.base.BaseFragment
import dk.nodes.template.presentation.ui.main.MainActivityViewModel
import dk.nodes.template.presentation.ui.main.MainActivityViewState
import kotlinx.android.synthetic.main.fragment_shift_overview.*
import timber.log.Timber

class ShiftOverviewFragment : BaseFragment() {
    private val viewModel by viewModel<MainActivityViewModel>()

    private var mainContext: Context? = null
    private var adapter: ShiftOverviewAdapter? = null



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = mainContext?.let { ShiftOverviewAdapter(it, R.layout.shift_recyclerview_row) }
        refreshShifts()

        viewModel.viewState.observeNonNull(this){
            state->
            handleShift(state)
            handleSemilarShifts(state)
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shift_overview, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainContext = context
    }




    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                ShiftOverviewFragment().apply {

                }
    }


    private fun refreshShifts() {
        viewModel.fetchShifts()
    }

    private fun handleShift(state: MainActivityViewState) {
        state.let {

            state.shiftOverviewList?.let { it -> adapter?.addShifts(it) }
            adapter?.notifyDataSetChanged()
            Timber.e("Adapter item count: %s", adapter?.itemCount)

            Timber.e( state.shiftOverviewList.toString())
            updateRecyclerView()
        }
    }

    fun updateRecyclerView(){
        rv_shift_overview.layoutManager = LinearLayoutManager(mainContext,LinearLayoutManager.VERTICAL,false)
        rv_shift_overview.adapter = adapter
        adapter?.notifyDataSetChanged()
    }


    private fun addItemOnclick() {
        adapter?.onItemClickedListener = { shift ->
            val intent = Intent(mainContext, shiftDetailsActivity::class.java)
            intent.putExtra("shift", shift)
            startActivity(intent)

        }

    }

    private fun handleSemilarShifts(viewState: MainActivityViewState){
        viewState.let { fetchRecommended->
            fetchRecommended.shiftOverviewList?.let {adapter?.addShifts(it) }

            addItemOnclick()
            Timber.e(fetchRecommended.toString())
            updateRecyclerView()
            adapter?.notifyDataSetChanged()
        }
    }

    private fun onCreate(){

    }

}
