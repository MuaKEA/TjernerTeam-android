package dk.nodes.template.repositories

import android.util.Log
import dk.nodes.template.models.FacebookUser
import dk.nodes.template.network.FaceBookService
import java.lang.NullPointerException
import javax.inject.Inject

class FacebookRespository @Inject constructor(
        private val api: FaceBookService) {

    suspend fun saveUser(User: FacebookUser) {

        val response = api.saveFacebookUser(User.facebookId, User.fullName, User.email, User.address, User.phoneNumber,
                User.city, User.postCode).execute()


        if (!response.isSuccessful) {
            Log.d("nameofname", response.code().toString())


        }


    }
}