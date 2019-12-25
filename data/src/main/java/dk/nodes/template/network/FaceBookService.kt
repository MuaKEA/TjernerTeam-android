package dk.nodes.template.network

import dk.nodes.template.models.FacebookUser
import retrofit2.Call
import retrofit2.http.GET
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

    @POST("/cancelAssignedJob")
    fun cancelAssignedJob(@Query("user_id") user_id: Long?,
                             @Query("shift_id") shift_id: Long?): Call<String>

    @POST("/saveUserSnoozeRequest")
    fun saveUserSnoozeRequest(@Query("user_id") user_id: String?,
                          @Query("snooze_value") snooze_value: String?): Call<String>

    @GET("/getUser")
    fun getFacebookUser(@Query("facebook_id") userName :String?): Call<FacebookUser>




}