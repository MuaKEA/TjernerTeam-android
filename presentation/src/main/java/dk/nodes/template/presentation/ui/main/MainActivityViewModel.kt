package dk.nodes.template.presentation.ui.main

import androidx.lifecycle.viewModelScope
import com.facebook.AccessToken
import dk.nodes.template.domain.interactors.*
import dk.nodes.template.models.FacebookUser
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

class MainActivityViewModel @Inject constructor(
        private val nStackPresenter: NStackPresenter,
        private val fetchShiftsInteractor: FetchShiftsInteractor,
        private val saveUserProfileInteractor: SaveUserProfileInteractor,
        private val saveUserRequestedJobInteractor: SaveUserRequestedJobInteractor,
        private val cancelAssignedJobInteractor: SaveUserRequestedJobInteractor,
        private val fetchFacebookUserInteractor: FetchFacebookUserInteractor,
        private val saveUserSnoozeRequestInteractor: SaveUserSnoozeRequestInteractor,
        private val GetSnoozeStatusInteractor: GetSnoozeStatusInteractor


        ) : BaseViewModel<MainActivityViewState>() {
    override val initState: MainActivityViewState = MainActivityViewState()


    private fun fetchShiftResult(result: CompleteResult<ArrayList<Shift>>): MainActivityViewState {
        return when (result) {
            is Success -> state.copy(shiftOverviewList = result.data)
            is Loading<*> -> state.copy(isLoading = true)
            is Fail -> state.copy(
                    viewError = SingleEvent(ViewErrorController.mapThrowable(result.throwable)),
                    isLoading = false
            )
            else -> (MainActivityViewState())
        }

    }

    fun fetchShifts() = viewModelScope.launch {
        val userId = AccessToken.getCurrentAccessToken().userId.toLong()
        val result = withContext(Dispatchers.IO) { fetchShiftsInteractor.asResult().invoke(userId) }
        state = fetchShiftResult(result)

    }

    fun saveUser(facebookUser: FacebookUser) = viewModelScope.launch {

        val result = withContext(Dispatchers.IO) { saveUserProfileInteractor.asResult().invoke(facebookUser) }
        state = saveFacebookUserResult(result)

    }

    private fun saveFacebookUserResult(result: CompleteResult<Unit>): MainActivityViewState {
        return when (result) {
            is Fail -> state.copy(
                    viewError = SingleEvent(ViewErrorController.mapThrowable(result.throwable)),
                    isLoading = false
            )
            else -> (MainActivityViewState())
        }

    }

    fun fetchUser(facebookId: String) = viewModelScope.launch {

        val result = withContext(Dispatchers.IO) { fetchFacebookUserInteractor.asResult().invoke(facebookId) }
        state = fetchUsers(result)

    }


    private fun fetchUsers(result: CompleteResult<FacebookUser>): MainActivityViewState {
        return when (result) {

            is Success -> state.copy(facebookUser = result.data)
            is Loading<*> -> state.copy(isLoading = true)
            is Fail -> state.copy(
                    viewError = SingleEvent(ViewErrorController.mapThrowable(result.throwable)),
                    isLoading = false
            )
            else -> (MainActivityViewState())

        }

    }


    fun saveUserRequestedJob(userId: Long, shiftId: Long?) = viewModelScope.launch {
        val userAndShiftIdArray = arrayOf(userId, shiftId)
        withContext(Dispatchers.IO) { saveUserRequestedJobInteractor.asResult().invoke(userAndShiftIdArray) }
    }

    fun cancelAssignedJob(userId: Long, shiftId: Long?) = viewModelScope.launch {
        val userAndShiftIdArray = arrayOf(userId, shiftId)
        withContext(Dispatchers.IO) { cancelAssignedJobInteractor.asResult().invoke(userAndShiftIdArray) }
    }

    fun saveUserSnoozeRequest(userIdAndSnoozeValue: Array<String>) = viewModelScope.launch {
        withContext(Dispatchers.IO) { saveUserSnoozeRequestInteractor.asResult().invoke(userIdAndSnoozeValue) }
    }

    fun getSnoozeStatus(user: FacebookUser?) = viewModelScope.launch {
        val result = withContext(Dispatchers.IO) { GetSnoozeStatusInteractor.asResult().invoke(user) }
        getSnoozeStatusResult(result)
    }

    private fun getSnoozeStatusResult(result: CompleteResult<Array<Any>>): MainActivityViewState {
        return when (result) {

            is Success -> state.copy(snoozeStatusAndDaysLeft = result.data)
            is Loading<*> -> state.copy(isLoading = true)
            is Fail -> state.copy(
                    viewError = SingleEvent(ViewErrorController.mapThrowable(result.throwable)),
                    isLoading = false
            )
            else -> (MainActivityViewState())

        }

    }
}

