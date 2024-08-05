package com.srikanth.recipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class MainViewModel :ViewModel(){

    private val _recipe= mutableStateOf(recipeState())

    val recipies:State<recipeState> = _recipe

    init {
        fetchCategories()
    }

    fun fetchCategories(){
        viewModelScope.launch {
            try{
                val recipy= recipeService.getCategories()
                _recipe.value = _recipe.value.copy(
                    loading = false,
                    categories = recipy.categories
                )
            }
            catch(e:Exception){
                _recipe.value = _recipe.value.copy(
                    loading = false,
                    error = "Error occured ${e.message}"
                )
            }
        }
    }


    data class recipeState(
        val loading:Boolean=true,
        val categories:List<CategoryModel> = emptyList(),
        val error:String?=null
    )
}