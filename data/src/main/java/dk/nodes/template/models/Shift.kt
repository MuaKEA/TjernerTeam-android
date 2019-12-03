package dk.nodes.template.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
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
        var eventDate : String?,
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
) :Parcelable{
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readValue(Double::class.java.classLoader) as? Double,
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString(),
                parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(address)
                parcel.writeValue(salary)
                parcel.writeString(employeeType)
                parcel.writeString(eventName)
                parcel.writeString(eventDate)
                parcel.writeString(eventStart)
                parcel.writeString(eventEnd)
                parcel.writeString(customerName)
                parcel.writeValue(numberOfEmployees)
                parcel.writeString(eventDescription)
                parcel.writeString(dressCode)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Shift> {
                override fun createFromParcel(parcel: Parcel): Shift {
                        return Shift(parcel)
                }

                override fun newArray(size: Int): Array<Shift?> {
                        return arrayOfNulls(size)
                }
        }
}
