package news.agoda.com.sample.data

import android.util.Log
import news.agoda.com.sample.AndroidChallengeApplication
import news.agoda.com.sample.data.model.NYResponse
import news.agoda.com.sample.data.remote.NewsRetrofitApiService
import retrofit2.Call
import retrofit2.Response

open class AppDataManagerImpl(private val newsRetrofitApiService: NewsRetrofitApiService) : AppDataManager<NYResponse> {

    var call: Call<NYResponse>? = null
    private val TAG = AppDataManagerImpl::class.qualifiedName

    init {
        AndroidChallengeApplication.newsComponent.inject(this)
    }


    override fun fetchDataFromServer(callback: AppDataManager.Callback<NYResponse>) {
        call = newsRetrofitApiService.getNews()
        call?.enqueue(object : retrofit2.Callback<NYResponse> {
            override fun onFailure(call: Call<NYResponse>?, t: Throwable?) {
//                TODO Handle the error
                Log.i(TAG, "onError" + t?.message)
            }

            override fun onResponse(call: Call<NYResponse>?, response: Response<NYResponse>?) {
                val nyResponse = response?.body()
                callback.onResponse(nyResponse)
            }
        })
    }
}