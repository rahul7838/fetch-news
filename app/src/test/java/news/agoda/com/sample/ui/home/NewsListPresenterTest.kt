package news.agoda.com.sample.ui.home

import com.nhaarman.mockitokotlin2.argumentCaptor
import news.agoda.com.sample.data.AppDataManager
import news.agoda.com.sample.data.model.NYResponse
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.*

@RunWith(JUnit4::class)
class NewsListPresenterTest {


    @Mock
    private var view: MainContract.View? = null

    @Mock
    private
    lateinit var appDataManager: AppDataManager<NYResponse>

    private lateinit var newsListPresenter: NewsListPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        newsListPresenter = NewsListPresenter(appDataManager)
        attachView()
    }

    @After
    fun tearDown() {
        detachView()
    }

    @Test
    fun attachView() {
        newsListPresenter.attachView(view)
    }

    @Test
    fun detachView() {
        newsListPresenter.detachView()
    }


    @Test
    fun fetchNewsSuccessTest() {
        newsListPresenter.fetchNews()
        Mockito.verify(view)?.showLoading()
        argumentCaptor<AppDataManager.Callback<NYResponse>>().apply {
            Mockito.verify(appDataManager).fetchDataFromServer(this.capture())
            firstValue.onResponse(getResponse())
        }
        Mockito.verify(view)?.hideLoading()
        Mockito.verify(view)?.updateUi(getResponse())
    }

    @Test
    fun fetchNewsErrorTest() {
        newsListPresenter.fetchNews()
        Mockito.verify(view)?.showLoading()
        argumentCaptor<AppDataManager.Callback<NYResponse>>().apply {
            Mockito.verify(appDataManager).fetchDataFromServer(this.capture())
            firstValue.onError()
        }
        Mockito.verify(view)?.hideLoading()
    }


    private fun getResponse(): NYResponse? {
        return NYResponse("copyright",
                "last_updated",
                5,
                arrayListOf(),
                "section",
                "status")
    }
}