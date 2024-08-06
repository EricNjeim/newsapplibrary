import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import com.example.newsapplibrary.model.ListingItemResponse

object ApiService {
    private const val API_URL = "http://10.0.2.2:8080"
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }

    suspend fun getItems(): List<ListingItemResponse> = client.get("$API_URL/items").body()
    suspend fun  fetchNewsItemById(newsId:Int): ListingItemResponse =client.get("$API_URL/items/$newsId").body()}