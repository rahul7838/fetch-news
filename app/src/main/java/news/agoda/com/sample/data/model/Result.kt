package news.agoda.com.sample.data.model

import android.os.Parcel
import android.os.Parcelable

data class Result(
        val abstract: String,
        val byline: String,
        val created_date: String,
        val des_facet: Array<String>,
        val geo_facet: Array<String>,
        val item_type: String,
        val kicker: String,
        val material_type_facet: String,
        val multimedia: Array<Multimedia>,
        val org_facet: Array<String>,
        val per_facet: Array<String>,
        val published_date: String,
        val section: String,
        val subsection: String,
        val title: String,
        val updated_date: String,
        val url: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArray(),
            parcel.createStringArray(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createTypedArray(Multimedia),
            parcel.createStringArray(),
            parcel.createStringArray(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(abstract)
        dest?.writeString(byline)
        dest?.writeString(created_date)
        dest?.writeArray(des_facet)
        dest?.writeArray(geo_facet)
        dest?.writeString(item_type)
        dest?.writeString(kicker)
        dest?.writeString(material_type_facet)
        dest?.writeArray(multimedia)
        dest?.writeArray(org_facet)
        dest?.writeArray(per_facet)
        dest?.writeString(published_date)
        dest?.writeString(section)
        dest?.writeString(subsection)
        dest?.writeString(title)
        dest?.writeString(updated_date)
        dest?.writeString(url)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}


