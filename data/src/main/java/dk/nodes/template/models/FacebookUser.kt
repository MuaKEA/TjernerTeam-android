package dk.nodes.template.models

import com.google.gson.annotations.SerializedName

class FacebookUser(
        @SerializedName("id")
        var facebookId: Long,
        @SerializedName("name")
        var fullName : String,
        @SerializedName("email")
        var email : String,
        @SerializedName("address")
        var address : String,
        @SerializedName("city")
        var city : String,
        @SerializedName("phoneNumber")
        var phoneNumber : Long,
        @SerializedName("postCode")
        var postCode: Int

)
