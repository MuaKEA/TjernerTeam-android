package dk.nodes.template.domain.interactors

import dk.nodes.template.models.Shift
import dk.nodes.template.repositories.ShiftRepository
import javax.inject.Inject

class FetchShiftsInteractor @Inject constructor(private val shiftRepository :ShiftRepository)
:  BaseAsyncInteractor<ArrayList<Shift>> {
    override suspend fun invoke(): ArrayList<Shift> {
    return shiftRepository.getShifts()
    }


}