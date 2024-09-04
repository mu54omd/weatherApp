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
            PersianDate().addDays(days).dayEnglishName().toString()
        } else {
            PersianDate().addDays(days).dayName().toString()
        }
    return result
}
