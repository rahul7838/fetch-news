package news.agoda.com.sample.ui.home

import news.agoda.com.sample.data.model.NYResponse

interface MainContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun updateUi(nyResponse: NYResponse?)
    }

    interface Presenter {
        fun fetchNews()
        fun attachView(view: View?)
        fun detachView()

    }
}