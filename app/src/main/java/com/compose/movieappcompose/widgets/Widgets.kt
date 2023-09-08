package com.compose.movieappcompose.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.compose.movieappcompose.model.Movie
import com.compose.movieappcompose.model.getMovies


@Composable
fun MovieRow(movie: Movie, onItemclick: (String) -> Unit){

    var expanded by remember {
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        // .height(130.dp)
        .clickable {
            movie.imdbID?.let { onItemclick(it) }
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row (verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start){
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp),
                shape = RectangleShape
            ) {

                Image(painter = rememberImagePainter(data = movie.Images[0],
                    builder = {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }), contentDescription = "Movie Poster")

                //Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Movie Image")
            }

            Column (modifier = Modifier.padding(4.dp)){
                movie.Title?.let { Text(text = it, style = MaterialTheme.typography.headlineMedium) }
                movie.Director?.let { Text(text = it) }
                movie.Released?.let { Text(text = it) }

                AnimatedVisibility(visible = expanded) {
                    Column {
                        //hidden information when arrow clicked
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)){
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp,
                                fontWeight = FontWeight.Bold)
                            ){
                                append(movie.Plot)
                            }
                        }, modifier = Modifier.padding(6.dp))

                        Divider(modifier = Modifier.padding(3.dp))
                        Text(text = "Director: ${movie.Director}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Actors: ${movie.Actors}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Rating: ${movie.Rated}", style = MaterialTheme.typography.bodyMedium)
                    }
                }



                Icon(imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown , contentDescription = "DOwn Arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.DarkGray
                )
            }

        }

    }
}
