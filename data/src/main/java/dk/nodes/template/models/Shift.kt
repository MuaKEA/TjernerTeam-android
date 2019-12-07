package dk.nodes.template.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.util.*

data class Shift(
        @SerializedName("address")
        var address : String?,
        @SerializedName("city")
        var city : String?,
        @SerializedName("salary")
        var salary : Double?,
        @SerializedName("employee_type")
        var employeeType : String?,
        @SerializedName("event_name")
        var eventName : String?,
        @SerializedName("event_date")
        var eventDate : String?,
        @SerializedName("start_time")
        var startTime : String?,
        @SerializedName("end_time")
        var endTime : String?,
        @SerializedName("customer_name")
        var customerName : String?,
        @SerializedName("number_of_employees")
        var numberOfEmployees : Int?,
        @SerializedName("event_description")
        var eventDescription : String?,
        @SerializedName("dress_code")
        var dressCode : String?,
        @SerializedName("staff_food")
        var staffFood : String?,
        @SerializedName("transport_supplements")
        var transportSupplements : Boolean?,
        @SerializedName("post_code")
        var postcode : Int?,
        @SerializedName("overtime")
        var overtime : Int?,
        @SerializedName("payment_date")
        var paymentDate : String?
) :Parcelable{
        constructor(parcel: Parcel) : this(
                parcel.readString(),
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
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString()){
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(address)
                parcel.writeString(city)
                parcel.writeValue(salary)
                parcel.writeString(employeeType)
                parcel.writeString(eventName)
                parcel.writeString(eventDate)
                parcel.writeString(startTime)
                parcel.writeString(endTime)
                parcel.writeString(customerName)
                parcel.writeValue(numberOfEmployees)
                parcel.writeString(eventDescription)
                parcel.writeString(dressCode)
                parcel.writeString(staffFood)
                parcel.writeValue(transportSupplements)
                parcel.writeValue(postcode)
                parcel.writeValue(overtime)
                parcel.writeValue(paymentDate)

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
