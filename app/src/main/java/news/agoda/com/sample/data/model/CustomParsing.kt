package news.agoda.com.sample.data.model

import com.google.gson.*
import org.json.JSONException
import java.lang.reflect.Type

class CustomParsing : JsonDeserializer<Result> {

    var typeOfT: Type? = null
    var context: JsonDeserializationContext? = null

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Result {

        this.typeOfT = typeOfT
        this.context = context

        var per_facet: JsonElement? = json?.asJsonObject?.get("per_facet")

        var des_facet: JsonElement? = json?.asJsonObject?.get("des_facet")
        var geo_facet: JsonElement? = json?.asJsonObject?.get("geo_facet")
        var org_facet: JsonElement? = json?.asJsonObject?.get("org_facet")
        var multimedia: JsonElement? = json?.asJsonObject?.get("multimedia")

        var item_type: String = json?.asJsonObject?.get("item_type").toString()
        var kicker: String = json?.asJsonObject?.get("kicker").toString()
        var material_type_facet: String = json?.asJsonObject?.get("material_type_facet").toString()
        var published_date: String = json?.asJsonObject?.get("published_date").toString()
        var section: String = json?.asJsonObject?.get("section").toString()
        var title: String = json?.asJsonObject?.get("title").toString()
        var subsection: String = json?.asJsonObject?.get("subsection").toString()
        var updated_date: String = json?.asJsonObject?.get("updated_date").toString()
        var url: String = json?.asJsonObject?.get("url").toString()
        var abstract: String = json?.asJsonObject?.get("abstract").toString()
        var byline: String = json?.asJsonObject?.get("byline").toString()
        var created_date: String = json?.asJsonObject?.get("created_date").toString()

        return Result(abstract,
                byline,
                created_date,
                getObjectType(des_facet, "des_facet", context),
                getObjectType(geo_facet, "geo_facet", context),
                item_type,
                kicker,
                material_type_facet,
                getObjectTypeMultimedia(multimedia, "multimedia", context),
                getObjectType(org_facet, "org_facet", context),
                getObjectType(per_facet, "per_facet", context),
                published_date,
                section,
                subsection,
                title,
                updated_date,
                url)
    }

    private fun getObjectTypeMultimedia(multimedia: JsonElement?, s: String, context: JsonDeserializationContext?): Array<Multimedia> {
        if (multimedia is JsonArray) {
            return Gson().fromJson(multimedia, Array<Multimedia>::class.java)
//        } else (multimedia?.asString.toString() is String) {
            //             (Gson().fromJson(multimedia?.asString.toString(), Array<Multimedia>::class.java) == null) {
        } else{
            return arrayOf()
        }
    }



    private fun getObjectType(json: JsonElement?, key: String, context: JsonDeserializationContext?): Array<String> {
        return when {
            json is JsonArray -> FacetParsingArray(key).deserialize(json, typeOfT, context)
            json?.asString?.toString() is String -> arrayOf(FacetParsingString(key).deserialize(json, typeOfT, context))
            else -> throw JSONException("UnExpected Json format")
        }
    }


    inner class FacetParsingArray(val key: String) : JsonDeserializer<Array<String>> {

        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Array<String> {
            var json = json?.asJsonArray!!
            return Gson().fromJson(json, Array<String>::class.java)
        }

    }

    inner class FacetParsingString(val key: String) : JsonDeserializer<String> {

        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): String {
            return when {
                Gson().fromJson(json?.asString?.toString(), String::class.java) == null -> ""
                else -> Gson().fromJson(json?.asString?.toString(), String::class.java)
            }
        }
    }
}