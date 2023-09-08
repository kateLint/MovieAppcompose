@file:OptIn(ExperimentalMaterial3Api::class)

package com.compose.movieappcompose.screen.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.compose.movieappcompose.model.Movie
import com.compose.movieappcompose.model.getMovies
import com.compose.movieappcompose.widgets.MovieRow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navController: NavController, movieId: String?){

    val newMovieList = getMovies().filter {movie ->
        movie.imdbID == movieId

    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),

                title = {
                    Row(horizontalArrangement = Arrangement.Start) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back",
                            modifier = Modifier.clickable {
                                navController.popBackStack()
                            })
                        Spacer(modifier = Modifier.width(100.dp))
                        Text("Movies")
                    }

                }
            )
        },
    ) {

        Surface (modifier = Modifier
            .padding(top = it.calculateTopPadding())
            .fillMaxHeight()
            .fillMaxWidth()
    ){
        Column (horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top){
            MovieRow(movie = newMovieList.first(),{} )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Text(text = "Movie Images")
            HorizontalScrolableImageView(newMovieList)
        }

    }

    }

}

@Composable
private fun HorizontalScrolableImageView(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList[0].Images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                Image(
                    painter = rememberImagePainter(data = image),
                    contentDescription = "Movie Poster",
                    modifier = Modifier.fillMaxWidth()

                )

            }

        }
    }
}