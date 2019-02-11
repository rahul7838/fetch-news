package news.agoda.com.sample.dependencyInjection

import dagger.Component
import news.agoda.com.sample.data.AppDataManagerImpl
import news.agoda.com.sample.ui.home.NewsListFragment
import news.agoda.com.sample.ui.home.NewsListPresenter

@Component(modules = [(NewsModule::class)])
interface NewsComponent {

//    fun inject(retrofitProvider: RetrofitProvider)
    fun inject(appDataManagerImpl: AppDataManagerImpl)
    fun inject(newsListFragment: NewsListFragment)
//    fun inject(newsListPresenter: NewsListPresenter)

}