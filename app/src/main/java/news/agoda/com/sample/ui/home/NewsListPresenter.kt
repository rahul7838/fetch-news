package news.agoda.com.sample.ui.home

import news.agoda.com.sample.data.AppDataManager
import news.agoda.com.sample.data.model.NYResponse

class NewsListPresenter(private val appDataManager: AppDataManager<NYResponse>) : MainContract.Presenter{

//    @Inject
//    lateinit var appDataManager: AppDataManager<NYResponse>

    var view: MainContract.View? = null

//    init {
//        AndroidChallengeApplication.newsComponent.inject(this)
//    }

    override fun attachView(view: MainContract.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun fetchNews() {
        view?.showLoading()
        appDataManager.fetchDataFromServer(object : AppDataManager.Callback<NYResponse>{
            override fun onResponse(result: NYResponse?) {
                view?.hideLoading()
                view?.updateUi(result)
            }

            override fun onError() {
                view?.hideLoading()
            }

        })
    }
}