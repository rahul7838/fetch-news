package news.agoda.com.sample.data.model

import android.os.Parcel
import android.os.Parcelable

data class Multimedia(
        val caption: String,
        val copyright: String,
        val format: String,
        val height: Int,
        val subtype: String,
        val type: String,
        val url: String,
        val width: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(caption)
        parcel.writeString(copyright)
        parcel.writeString(format)
        parcel.writeInt(height)
        parcel.writeString(subtype)
        parcel.writeString(type)
        parcel.writeString(url)
        parcel.writeInt(width)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Multimedia> {
        override fun createFromParcel(parcel: Parcel): Multimedia {
            return Multimedia(parcel)
        }

        override fun newArray(size: Int): Array<Multimedia?> {
            return arrayOfNulls(size)
        }
    }

}