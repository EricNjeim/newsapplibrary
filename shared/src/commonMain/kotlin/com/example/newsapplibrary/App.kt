import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.example.newsapplibrary.screens.NewsDetailScreen
import com.example.newsapplibrary.screens.NewsListScreen
class NewsLibrary {
    @Composable
    @Preview
    fun App() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "newsList") {
            composable("newsList") { NewsListScreen(navController) }
            composable("newsDetail/{newsId}") { backStackEntry ->
                val newsId = backStackEntry.arguments?.getString("newsId")?.toInt()
                if (newsId != null) {
                    NewsDetailScreen(newsId, navController)
                }

            }
        }


    }

}