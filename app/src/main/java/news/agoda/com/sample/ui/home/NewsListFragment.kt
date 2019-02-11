package news.agoda.com.sample.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.progress_bar.*
import news.agoda.com.sample.AndroidChallengeApplication
import news.agoda.com.sample.R
import news.agoda.com.sample.data.AppDataManagerImpl
import news.agoda.com.sample.data.model.NYResponse
import news.agoda.com.sample.data.model.Result
import news.agoda.com.sample.ui.detailsPage.DetailsPageFragment
import javax.inject.Inject

class NewsListFragment : Fragment(), MainContract.View {

    companion object {
        const val TAG = "NewsListFragment"
    }


    private lateinit var newsListAdapter: NewsListAdapter

    @Inject
    lateinit var presenter: MainContract.Presenter

//    @Inject
//    lateinit var appDataManager: AppDataManager<NYResponse>

    @Inject
    lateinit var appDataManagerImpl: AppDataManagerImpl

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.i(TAG, "onCreateView" )
        return inflater?.inflate(R.layout.activity_main,container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated" )
        AndroidChallengeApplication.newsComponent.inject(this)
        presenter.attachView(this)
        presenter.fetchNews()
        newsListAdapter = NewsListAdapter()
        newsListAdapter.onItemClick= {result -> openDetailPageFragment(result) }
        news_list_recycler_view_id.apply {
            layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
            adapter = newsListAdapter
        }
    }


    private fun openDetailPageFragment(result: Result) {
        activity?.supportFragmentManager?.beginTransaction()?.replace(
                R.id.fragment_container_id, DetailsPageFragment.newInstance(result))
                ?.addToBackStack(DetailsPageFragment.TAG)
                ?.commit()
        }


    override fun showLoading() {
        progress_bar_id.visibility = VISIBLE
    }

    override fun hideLoading() {
        if(progress_bar_id != null)
        progress_bar_id.visibility = GONE
    }

    override fun updateUi(nyResponse: NYResponse?) {
        newsListAdapter.prepareNewsList(nyResponse)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if(appDataManagerImpl.call!=null)
            appDataManagerImpl.call?.cancel()
        Log.i(TAG, "onDestroyView" )

    }
}