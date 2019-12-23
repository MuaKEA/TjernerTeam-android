package dk.nodes.template.network

import dk.nodes.template.models.FacebookUser
import dk.nodes.template.models.PostCode
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FaceBookService {
    @POST("/saveUser")
    fun saveFacebookUser(@Query("facebook_id") facebook_id: Long?,
                         @Query("name") fullName: String?,
                         @Query("email") email: String?,
                         @Query("fcmtoken") fcmToken : String?): Call<String>

    @POST("/saveUserRequestedJob")
    fun saveUserRequestedJob(@Query("user_id") user_id: Long?,
                             @Query("shift_id") shift_id: Long?): Call<String>

    @POST("/cancelAssignedJob")
    fun cancelAssignedJob(@Query("user_id") user_id: Long?,
                             @Query("shift_id") shift_id: Long?): Call<String>

    @GET("/getUser")
    fun getFacebookUser(@Query("facebook_id") userName :String?): Call<FacebookUser>

    @POST("/updateUser")
    fun updateUSer(@Query("id") facebook_id: Long?,
                         @Query("name") fullName: String?,
                         @Query("email") email: String?,
                         @Query("address") address : String?,
                         @Query("city") city : String?,
                         @Query("phoneNumber") phoneNumber: Long?,
                         @Query("postCodes") postCode: Int?,
                         @Query("cprNumber") cprNumber: String?,
                         @Query("regNumber") regNumber: Int?,
                         @Query("accountNumber") accountNumber: Long?,
                         @Query("dateOfBirth") dateOfBirth: String?,
                         @Query("gender") gender: String?): Call<String>

    @DELETE("/deleteUser")
    fun deleteUser(@Query("facebook_id") facebook_id: Long?): Call<String>



}