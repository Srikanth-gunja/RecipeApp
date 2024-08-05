package com.srikanth.recipeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CategoryModel(
    val idCategory:Int,
    val strCategory:String,
    val strCategoryThumb:String,
    val strCategoryDescription:String
): Parcelable

data class CategoryList(
    val categories:List<CategoryModel>
)