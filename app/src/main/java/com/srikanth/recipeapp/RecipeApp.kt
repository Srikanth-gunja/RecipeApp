package com.srikanth.recipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(
    modifier: Modifier = Modifier,
    navController:NavHostController
) {
    val mainViewModel:MainViewModel= viewModel()

    val recipy by mainViewModel.recipies

    NavHost(navController=navController, startDestination = Screen.RecipeScreen.name ){
        composable(Screen.RecipeScreen.name){

        MainScreen(recipy=recipy) {

            navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
            navController.navigate(Screen.DetailScreen.name)
        }
        }

        composable(Screen.DetailScreen.name){
            val category=navController.previousBackStackEntry?.
            savedStateHandle?.get<CategoryModel>("cat")?:CategoryModel(0,"","","")

                CategoryScreen(category)


        }
    }
}