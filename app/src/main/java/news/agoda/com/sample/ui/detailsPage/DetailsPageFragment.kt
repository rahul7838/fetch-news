package news.agoda.com.sample.ui.detailsPage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.request.ImageRequest
import kotlinx.android.synthetic.main.activity_detail.*
import news.agoda.com.sample.R
import news.agoda.com.sample.data.model.Result

class DetailsPageFragment : Fragment() {

    lateinit var result: Result


    companion object {

        const val TAG = "DetailsPageFragment"

        const val RESULT_KEY = "result"

        fun newInstance(result: Result) = DetailsPageFragment().apply {
            arguments = Bundle().apply {
                putParcelable(RESULT_KEY, result)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        result = requireNotNull(arguments).getParcelable(RESULT_KEY)
        return inflater?.inflate(R.layout.activity_detail, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        summary_content.text = result.abstract.replace("\"", "")
        title.text = result.title.replace("\"", "")
        if (!result.multimedia.isEmpty()) {
            val draweeController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(ImageRequest.fromUri(Uri.parse(result.multimedia[0].url)))
                    .setOldController(news_image.controller).build()
            news_image.controller = draweeController
        }
        full_story_link.setOnClickListener {
            onFullStoryClicked()
        }
    }

    private fun onFullStoryClicked() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(result.url.replace("\"", ""))
        startActivity(intent)
    }


}