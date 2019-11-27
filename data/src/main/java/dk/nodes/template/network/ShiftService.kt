package dk.nodes.template.network

import dk.nodes.template.models.GetShiftsWrapper
import dk.nodes.template.models.Shift
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface ShiftService {
         @GET("/getshifts")
         fun getActiveShifts() : Call<GetShiftsWrapper>
}