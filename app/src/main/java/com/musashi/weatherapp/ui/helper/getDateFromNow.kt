package com.musashi.weatherapp.ui.helper

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat

@Composable
fun getDateFromNow(days: Int): String{
    var result = ""
    result =
        if(LocalLayoutDirection.current == LayoutDirection.Ltr){
            PersianDate().addDays(days).toDate().toString().split(" ").subList(0, 3).joinToString(separator = " ")
        } else {
            PersianDateFormat("l d F").format(PersianDate().addDays(days))
        }
    return result
}
