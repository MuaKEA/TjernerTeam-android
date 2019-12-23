package dk.nodes.template.repositories

import android.util.Log
import dk.nodes.template.models.FacebookUser
import dk.nodes.template.network.FaceBookService
import javax.inject.Inject

class FacebookRespository @Inject constructor(
        private val api: FaceBookService) {

    suspend fun saveUser(User: FacebookUser) {
        val response = api.saveFacebookUser(User.facebookId, User.fullName, User.email, User.fcmToken).execute()

        if (!response.isSuccessful) {
            Log.d("saveUser", response.body())
        }
    }

    suspend fun saveUserRequestedJob(userAndShiftId: Array<Long?>) {
        val response = api.saveUserRequestedJob(userAndShiftId[0], userAndShiftId[1]).execute()

        if (!response.isSuccessful) {
        }
    }

    suspend fun cancelAssignedJob(userAndShiftId: Array<Long?>) {
        val response = api.cancelAssignedJob(userAndShiftId[0], userAndShiftId[1]).execute()

        if (!response.isSuccessful) {
        }
    }

   suspend fun getFacebookUser(facebookUser: String): FacebookUser {
        var user: FacebookUser? = null

        val response = api.getFacebookUser(facebookUser).execute()

        if (response.isSuccessful) {
            user = response.body()

            if (user != null) {

                return user
            }
        }

        return user!!

    }

    suspend fun deleteUser(facebookId : Long){

        val response = api.deleteUser(facebookId).execute()

        if (!response.isSuccessful ){


        }


    }


    suspend fun updateUser(user: FacebookUser) {

        val response =api.updateUSer(user.facebookId, user.fullName, user.email,user.address,user.city,user.phoneNumber, user.postCode?.postCode, user.cprNumber,user.regNumber,user.accountNumber,user.dateOfBirth,user.gender).execute()

        if (response != null) {
            if (!response.isSuccessful) {
                Log.d("saveUser", response.body())

            }
        }
    }
}