package dk.nodes.template.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Shift(
        @SerializedName("address")
        var address : String?,
        @SerializedName("salary")
        var salary : Double?,
        @SerializedName("employeeType")
        var employeeType : String?,
        @SerializedName("eventName")
        var eventName : String?,
        @SerializedName("eventDate")
        var eventDate : Date?,
        @SerializedName("eventStart")
        var eventStart : String?,
        @SerializedName("eventEnd")
        var eventEnd : String?,
        @SerializedName("customerName")
        var customerName : String?,
        @SerializedName("numberOfEmployees")
        var numberOfEmployees : Int?,
        @SerializedName("eventDescription")
        var eventDescription : String?,
        @SerializedName("dressCode")
        var dressCode : String?
        )