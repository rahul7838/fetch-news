package news.agoda.com.sample

import android.app.Application
import android.content.Context
import news.agoda.com.sample.dependencyInjection.DaggerNewsComponent
import news.agoda.com.sample.dependencyInjection.NewsComponent
import news.agoda.com.sample.dependencyInjection.NewsModule

class AndroidChallengeApplication : Application() {

    lateinit var context: Context
    companion object {
        lateinit var newsComponent: NewsComponent
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        news.agoda.com.sample.AndroidChallengeApplication.newsComponent =
                DaggerNewsComponent.builder().newsModule(NewsModule()).build()
    }
}