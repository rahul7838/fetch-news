package news.agoda.com.sample.data.remote

import news.agoda.com.sample.data.model.NYResponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsRetrofitApiService {

    @GET(".")
    fun getNews(): Call<NYResponse>
}