package com.compose.movieappcompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.compose.movieappcompose.model.Movie
import com.compose.movieappcompose.navigation.MovieNavigation
import com.compose.movieappcompose.ui.theme.MovieAppcomposeTheme
import com.compose.movieappcompose.utils.ReadJSONFromAssets
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

var movies: List<Movie> = listOf()
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val jsonString = ReadJSONFromAssets(baseContext, "movie.json")
            val typeToken = object :TypeToken<List<Movie>>() {}.type
            movies = Gson().fromJson<List<Movie>>(jsonString, typeToken)

            Log.d("Movie", "Movies name ")
        }catch (e: Exception){
            Log.d("Exception", "onCreate: e")
        }

        setContent {
            MyApp {
                MovieNavigation()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit){
    MovieAppcomposeTheme {
        content()
    }

}






@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
   MyApp {
      MovieNavigation()
   }
}