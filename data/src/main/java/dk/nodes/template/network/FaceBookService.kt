package dk.nodes.template.network

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface FaceBookService {
    @POST("/saveUser")
    fun saveFacebookUser(@Query("facebook_id") facebook_id: Long?,
                         @Query("name") name: String?,
                         @Query("email") email: String?,
                         @Query("address") address: String?,
                         @Query("phonenumber") phonenumber: Long?,
                         @Query("city") city: String?,
                         @Query("postCode") postCode: Int?): Call<String>


}