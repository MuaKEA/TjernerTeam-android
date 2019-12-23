package dk.nodes.template.repositories

import android.media.FaceDetector
import android.util.Log
import dk.nodes.template.models.FacebookUser
import dk.nodes.template.network.FaceBookService
import javax.inject.Inject

class FacebookRespository @Inject constructor(
        private val api: FaceBookService) {
        private val facebookRespository: FacebookRespository? = null

    suspend fun saveUser(User: FacebookUser) {
        val response = api.saveFacebookUser(User.facebookId, User.fullName, User.email, User.fcmToken, User.city,
                User.phoneNumber, User.postCode?.postCode!!.toInt(), User.address).execute()

        if (!response.isSuccessful) {
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

    fun getFacebookUser(facebookUser: String): FacebookUser {
        var user: FacebookUser? = null

        val response = api.getFacebookUser(facebookUser).execute()

        if (response.isSuccessful) {
            user = response.body()

            if (user != null) {

                return user
            }
        }
        Log.d("shadush", user.toString())

        return user!!

    }

    suspend fun deleteUser(facebookID: Long) {
        var fUser: FacebookUser? = null
        if(fUser != null){
            facebookRespository?.deleteUser(facebookID)

            val response = api.deleteUser(facebookID).execute()

        }

    }


}