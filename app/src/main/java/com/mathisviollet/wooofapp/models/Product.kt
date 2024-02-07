package com.mathisviollet.wooofapp.models

data class Product(
    val title:String,
    val description:String,
    val price:Double,
    val date: String,
    val place: Place,
    val author:Author,
    val isFavourite: Boolean,
    val imageUrl: String,
)
