package news.agoda.com.sample.data

import news.agoda.com.sample.data.model.NYResponse

interface AppDataManager<T> {

    fun fetchDataFromServer(callback: Callback<NYResponse>)

    interface Callback<T> {

        fun onResponse(result: T?)

        fun onError()
    }
}