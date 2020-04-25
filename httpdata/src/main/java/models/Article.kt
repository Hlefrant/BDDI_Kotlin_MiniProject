package models

data class ArticleResult (
    val status: String,
    val totalsResult: Int,
    val articles: List<Article>
)

data class Article(
    val author: String,
    val source: SourceItem,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

