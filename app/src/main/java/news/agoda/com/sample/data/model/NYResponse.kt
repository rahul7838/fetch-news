package news.agoda.com.sample.data.model

data class NYResponse(
        val copyright: String,
        val last_updated: String,
        val num_results: Int,
        val results: List<Result>,
        val section: String,
        val status: String
)