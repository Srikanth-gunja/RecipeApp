package com.srikanth.recipeapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter


@Composable
fun MainScreen(
    modifier:Modifier=Modifier,
    recipy:MainViewModel.recipeState,
    navigateToCategory: (CategoryModel) -> Unit
){


    Column(modifier.fillMaxSize()
        .padding(5.dp)){
      when{
          recipy.loading->{
              Box(
                  modifier = Modifier
                      .fillMaxSize() // Ensures the Box takes up the entire screen
              ) {
                  CircularProgressIndicator(
                      modifier = Modifier
                          .align(Alignment.Center) // Centers the progress indicator
                  )
              }
          }
          recipy.error !=null->{
              Box(
                  modifier = Modifier
                      .fillMaxSize() // Ensures the Box takes up the entire screen
              ) {
                  Text("Error occured ${recipy.error}",modifier.align(Alignment.Center))
              }
          }
          else->{
              CatgoryListt(categories = recipy.categories, navigateToCategory = navigateToCategory)
          }
      }
    }
}


@Composable
fun CatgoryListt(modifier:Modifier=Modifier,
                 categories:List<CategoryModel>,
                 navigateToCategory: (CategoryModel) -> Unit
){
    LazyVerticalGrid(GridCells.Fixed(2),modifier.fillMaxSize().padding(10.dp)) {
        items(categories){
             cat->   Category(category = cat, navigateToCategory = navigateToCategory)


        }
    }
}

@Composable
fun Category(modifier: Modifier = Modifier,
             category: CategoryModel,
             navigateToCategory:(CategoryModel)->Unit
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(modifier.fillMaxSize()

            ) {
            Column(modifier.fillMaxSize()
                .clickable {
                    navigateToCategory(category)
                    Log.d("category", category.toString())
                }
            ) {
                Image(
                    painter = rememberAsyncImagePainter(category.strCategoryThumb) ,
                    contentDescription = "Category Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(category.strCategory,modifier.align(Alignment.CenterHorizontally))
            }
        }
    }

}

