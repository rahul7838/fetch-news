package news.agoda.com.sample.ui.home

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.interfaces.DraweeHierarchy
import com.facebook.drawee.view.DraweeView
import com.facebook.imagepipeline.request.ImageRequest
import news.agoda.com.sample.data.model.NYResponse
import news.agoda.com.sample.R
import news.agoda.com.sample.data.model.Result
import java.util.Collections.addAll

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>() {


    var list: MutableList<Result> = ArrayList()

    var onItemClick: ((Result) -> Unit )? = null

    fun prepareNewsList(nyResponse: NYResponse?) {
        list.clear()
        list.addAll(nyResponse?.results!!)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewsListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_news, parent, false)
        return NewsListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NewsListViewHolder?, position: Int) {
        holder?.txtNewsHeading?.text = list[position].title

        var draweeController: DraweeController?= null
        val pipelineDraweeControllerBuilder = Fresco.newDraweeControllerBuilder()
        if(!list[position].multimedia.isEmpty()){
            draweeController = pipelineDraweeControllerBuilder.setImageRequest(ImageRequest.fromUri(Uri.parse(list[position].multimedia[0].url)))
                    .setOldController(holder?.draweeView?.controller)
                    .build()
        }
        holder?.draweeView?.controller = draweeController
    }

    inner class NewsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNewsHeading: TextView? = itemView.findViewById(R.id.news_title) as TextView?
        val draweeView: DraweeView<*> = itemView.findViewById(R.id.news_item_image) as DraweeView<*>
        init {
            itemView.setOnClickListener {onItemClick?.invoke(list[adapterPosition])}
        }
    }
}