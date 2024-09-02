package com.musashi.weatherapp.ui.helper

import java.util.Date

fun getCurrentDate():String{
    val monthMap = mapOf("Jan" to "01", "Feb" to "02", "Mar" to "03", "Apr" to "04", "May" to "05", "Jun" to "06", "Jul" to "07", "Aug" to "08", "Sep" to "09", "Oct" to "10", "Nov" to "11", "Dec" to "12")
    val dateAndTime = Date().toString().split(" ")
    val date = dateAndTime[5] + "-" + monthMap[dateAndTime[1]] + "-" + dateAndTime[2]
    val time = dateAndTime[3].split(":")[0] + ":00"
    return "${date}T${time}"
}