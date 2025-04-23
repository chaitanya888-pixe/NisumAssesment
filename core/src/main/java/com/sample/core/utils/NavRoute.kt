package com.sample.core.utils

sealed class NavRoute (val route:String){
    object List : NavRoute("list")
    object Details :NavRoute("details")
}