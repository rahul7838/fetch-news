package news.agoda.com.sample.data.remote

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Provider

class RetrofitProvider(val gson: Gson) : Provider<Retrofit> {

    override fun get(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.myjson.com/bins/nl6jh/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }
}