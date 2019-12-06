package dk.nodes.template.repositories

import android.util.Log
import com.google.gson.Gson
import dk.nodes.template.models.Shift
import dk.nodes.template.network.ShiftService
import javax.inject.Inject


class ShiftRepository @Inject constructor(
        private val api: ShiftService,
        private val gson: Gson) {

   suspend fun getShifts(): ArrayList<Shift> {
        val shiftsArrayList =ArrayList<Shift>()
        Log.d("tupac", "shakur")

        val response = api.getActiveShifts().execute()

        Log.d("tupac", response.body().toString())



        if (response.isSuccessful) {
           val shiftResponse = response.body()

            if (shiftResponse != null){
                shiftsArrayList.addAll(shiftResponse.shiftsWrapper)
                Log.d("tupac", "shakur3")
                return shiftsArrayList
            }
        }

        return shiftsArrayList
    }
}

