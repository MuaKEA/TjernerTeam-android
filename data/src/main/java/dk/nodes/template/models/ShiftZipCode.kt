package dk.nodes.template.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ShiftZipCode(
        @SerializedName("postCode")
        var postCode : Int?
) : Parcelable{
    constructor(parcel: Parcel) : this(parcel.readValue(Int::class.java.classLoader) as? Int) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object CREATOR : Parcelable.Creator<ShiftZipCode> {
        override fun createFromParcel(parcel: Parcel): ShiftZipCode {
            return ShiftZipCode(parcel)
        }

        override fun newArray(size: Int): Array<ShiftZipCode?> {
            return arrayOfNulls(size)
        }
    }
}

