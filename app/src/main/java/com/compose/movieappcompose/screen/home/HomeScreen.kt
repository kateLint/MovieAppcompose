package com.compose.movieappcompose.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.compose.movieappcompose.model.Movie
import com.compose.movieappcompose.model.getMovies
import com.compose.movieappcompose.navigation.MovieScreens
import com.compose.movieappcompose.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Movies")
                }
            )
        },
    ) {

        MainContent(Modifier.padding(top =  it.calculateTopPadding()), navController = navController)

        // scrollContent()
        //ScrollContent(innerPadding)
    }
}

@Composable
fun MainContent(
    modifier: Modifier, navController: NavController,
    movieList: List<Movie> = getMovies()
){

    Column(modifier = modifier
        .fillMaxSize()) {
        LazyColumn {
            items(movieList.size){index->
                MovieRow(movie =  movieList[index]){ movie ->
                    navController.navigate(route = MovieScreens.DetailsScreen.name+"/$movie")

                }
                //Text(text = movieList[index])
            }
        }
    }
}