package com.musashi.weatherapp.ui.helper

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import saman.zamani.persiandate.PersianDate

@Composable
fun getDateFromNow(days: Int): String{
    var result = ""
    result =
        if(LocalLayoutDirection.current == LayoutDirection.Ltr){
            PersianDate().addDays(days).toDate().toString().split(" ").subList(0, 3).joinToString(separator = " ")
        } else {
            PersianDate().addDays(days).toString().split(" ").subList(0, 3).joinToString(separator = " ")
        }
    return result
}
