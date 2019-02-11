package news.agoda.com.sample.ui.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.facebook.drawee.backends.pipeline.Fresco
import news.agoda.com.sample.R
import news.agoda.com.sample.utils.isNetworkAvailable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Fresco.initialize(this)

        if (isNetworkAvailable(context = this)) {
            val newsListFragment = NewsListFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container_id, newsListFragment, NewsListFragment.TAG)
                    .commit()
        } else {
            Toast.makeText(this, "No internet connectivity", Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
