package dk.nodes.template.repositories

import dk.nodes.template.models.Post
import dk.nodes.template.models.Shift
import dk.nodes.template.network.Api
import dk.nodes.template.network.ShiftService
import javax.inject.Inject

class ShiftRepository @Inject constructor(private val api: ShiftService) {

    suspend fun getShifts(): ArrayList<Shift> {
        val shiftsArrayList =ArrayList<Shift>()

        val response = api.getActiveShifts().execute()
        if (response.isSuccessful) {
           val shiftResponse = response.body()

            if (shiftResponse != null){
                shiftsArrayList.addAll(shiftResponse.shiftsWrapper)

                return shiftsArrayList
            }
        }

        return shiftsArrayList
    }
}

