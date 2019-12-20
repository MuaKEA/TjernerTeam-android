package dk.nodes.template.repositories

import android.util.Log
import dk.nodes.template.models.FacebookUser
import dk.nodes.template.network.FaceBookService
import java.lang.NullPointerException
import javax.inject.Inject

class FacebookRespository @Inject constructor(
        private val api: FaceBookService) {

    suspend fun saveUser(User: FacebookUser) {
        val response = api.saveFacebookUser(User.facebookId, User.fullName, User.email, User.fcmToken, User.city,
                User.phoneNumber, User.postCode?.postCode.toInt(), User.address).execute()

        if (!response.isSuccessful) {
            Log.d("nameofname", response.code().toString())
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
}