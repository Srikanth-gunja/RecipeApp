package com.srikanth.recipeapp

sealed class Screen(val name:String){
    object RecipeScreen:Screen("recipescreen")
    object DetailScreen:Screen("detaialscreen")
}