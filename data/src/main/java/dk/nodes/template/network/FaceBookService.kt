package dk.nodes.template.network

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface FaceBookService {
    @POST("/saveUser")
    fun saveFacebookUser(@Query("facebook_id") facebook_id: Long?,
                         @Query("name") name: String?,
                         @Query("email") email: String?,
                        @Query("fcmtoken") fcmToken : String?): Call<String>

    @POST("/saveUserRequestedJob")
    fun saveUserRequestedJob(@Query("user_id") user_id: Long?,
                             @Query("shift_id") shift_id: Long?): Call<String>
}