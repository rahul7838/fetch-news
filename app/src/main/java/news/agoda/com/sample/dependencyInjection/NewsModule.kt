package news.agoda.com.sample.dependencyInjection

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import news.agoda.com.sample.data.AppDataManager
import news.agoda.com.sample.data.AppDataManagerImpl
import news.agoda.com.sample.data.model.CustomParsing
import news.agoda.com.sample.data.model.NYResponse
import news.agoda.com.sample.data.model.Result
import news.agoda.com.sample.data.remote.NewsRetrofitApiService
import news.agoda.com.sample.data.remote.RetrofitProvider
import news.agoda.com.sample.ui.home.MainContract
import news.agoda.com.sample.ui.home.NewsListPresenter
import retrofit2.Retrofit


@Module
class NewsModule {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().registerTypeAdapter(Result::class.java, CustomParsing()).create()
    }


    @Provides
    fun provideRetrofitProvider(gson: Gson): RetrofitProvider {
        return RetrofitProvider(gson)
    }

    @Provides
    fun providesRetrofit(retrofitProvider: RetrofitProvider): Retrofit {
        return retrofitProvider.get()
    }

    @Provides
    fun provideNewsRetrofitApiService(retrofit: Retrofit): NewsRetrofitApiService {
        return retrofit.create(NewsRetrofitApiService::class.java)
    }

    @Provides
    fun provideAppDataManagerImpl(newsRetrofitApiService: NewsRetrofitApiService): AppDataManagerImpl {
        return AppDataManagerImpl(newsRetrofitApiService)
    }

    @Provides
    fun provideAppDataManager(newsRetrofitApiService: NewsRetrofitApiService): AppDataManager<NYResponse> {
        return AppDataManagerImpl(newsRetrofitApiService)
    }

    @Provides
    fun provideNewsListPresenter(appDataManager: AppDataManager<NYResponse>): MainContract.Presenter {
        return NewsListPresenter(appDataManager)
    }
}