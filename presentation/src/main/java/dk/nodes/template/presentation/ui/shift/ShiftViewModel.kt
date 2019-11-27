package dk.nodes.template.presentation.ui.shift

import androidx.lifecycle.viewModelScope
import dk.nodes.template.domain.interactors.*
import dk.nodes.template.models.Shift
import dk.nodes.template.presentation.extensions.asResult
import dk.nodes.template.presentation.nstack.NStackPresenter
import dk.nodes.template.presentation.ui.base.BaseViewModel
import dk.nodes.template.presentation.util.SingleEvent
import dk.nodes.template.presentation.util.ViewErrorController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class ShiftViewModel @Inject constructor(
private val nStackPresenter: NStackPresenter,
private val fetchShiftsInteractor: FetchShiftsInteractor


): BaseViewModel<ShiftViewState>(){
    override val initState: ShiftViewState = ShiftViewState()




    private fun fetchShiftResult(result: CompleteResult<ArrayList<Shift>>): ShiftViewState {
        return when (result) {
            is Success -> state.copy(shiftOverviewList = result.data)
            is Loading<*> -> state.copy(isLoading = true)
            is Fail -> state.copy(
                    viewError = SingleEvent(ViewErrorController.mapThrowable(result.throwable)),
                    isLoading = false
            )
            else -> (ShiftViewState())
        }

    }

    fun fetchShifts() =  viewModelScope.launch {

        val result = withContext(Dispatchers.IO) { fetchShiftsInteractor.asResult().invoke() }
        state = fetchShiftResult(result)

    }



}

