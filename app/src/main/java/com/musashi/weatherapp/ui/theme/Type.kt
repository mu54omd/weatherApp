package com.musashi.weatherapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.musashi.weatherapp.R

val vazirFont = FontFamily(
    Font(R.font.vazirmatn_bold, weight = FontWeight.Bold),
    Font(R.font.vazirmatn_thin, weight = FontWeight.Thin),
    Font(R.font.vazirmatn_black, weight = FontWeight.Black),
    Font(R.font.vazirmatn_extra_bold, weight = FontWeight.ExtraBold),
    Font(R.font.vazirmatn_extra_light, weight = FontWeight.ExtraLight),
    Font(R.font.vazirmatn_medium, weight = FontWeight.Medium),
    Font(R.font.vazirmatn_regular, weight = FontWeight.Normal),
    Font(R.font.vazirmatn_light, weight = FontWeight.Light),
    Font(R.font.vazirmatn_semi_bold, weight = FontWeight.SemiBold),
)
val vazirFontFarsiDigit = FontFamily(
    Font(R.font.vazirmatn_ui_fd_regular, weight = FontWeight.Normal),
    Font(R.font.vazirmatn_ui_fd_bold, weight = FontWeight.Bold),
    Font(R.font.vazirmatn_ui_fd_medium, weight = FontWeight.Medium),
    Font(R.font.vazirmatn_ui_fd_extra_bold, weight = FontWeight.ExtraBold),
    Font(R.font.vazirmatn_ui_fd_extra_light, weight = FontWeight.ExtraLight),
    Font(R.font.vazirmatn_ui_fd_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.vazirmatn_ui_fd_thin, weight = FontWeight.Thin),
    Font(R.font.vazirmatn_ui_fd_black, weight = FontWeight.Black),
    Font(R.font.vazirmatn_ui_fd_light, weight = FontWeight.Light),

)
val baseline = Typography()

val Typography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = vazirFont),
    displayMedium = baseline.displayMedium.copy(fontFamily = vazirFont),
    displaySmall = baseline.displaySmall.copy(fontFamily = vazirFont),

    headlineLarge = baseline.headlineLarge.copy(fontFamily = vazirFont),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = vazirFont),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = vazirFont),

    titleLarge = baseline.titleLarge.copy(fontFamily = vazirFont),
    titleMedium = baseline.titleMedium.copy(fontFamily = vazirFont),
    titleSmall = baseline.titleSmall.copy(fontFamily = vazirFont),

    bodyLarge = baseline.bodyLarge.copy(fontFamily = vazirFont),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = vazirFont),
    bodySmall = baseline.bodySmall.copy(fontFamily = vazirFontFarsiDigit),

    labelLarge = baseline.labelLarge.copy(fontFamily = vazirFont),
    labelMedium = baseline.labelMedium.copy(fontFamily = vazirFontFarsiDigit),
    labelSmall = baseline.labelSmall.copy(fontFamily = vazirFont),
)
// Set of Material typography styles to start with
//val Typography = Typography(
//    //Body
//    bodyLarge = TextStyle(
//        fontFamily = vazirFont,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    ),
//    bodyMedium = TextStyle(
//        fontFamily = vazirFont,
//        fontWeight = FontWeight.Normal,
//        fontSize = 12.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    ),
//    bodySmall = TextStyle(
//        fontFamily = vazirFont,
//        fontWeight = FontWeight.Normal,
//        fontSize = 10.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    ),
//    //Title
//    titleLarge = TextStyle(
//        fontFamily = vazirFont,
//        fontWeight = FontWeight.Normal,
//        fontSize = 22.sp,
//        lineHeight = 28.sp,
//        letterSpacing = 0.sp
//    ),
//    titleMedium = TextStyle(
//        fontFamily = vazirFont,
//        fontWeight = FontWeight.Normal,
//        fontSize = 18.sp,
//        lineHeight = 28.sp,
//        letterSpacing = 0.sp
//    ),
//    titleSmall = TextStyle(
//        fontFamily = vazirFont,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 28.sp,
//        letterSpacing = 0.sp
//    ),
//    //Label
//    labelLarge = TextStyle(
//        fontFamily = vazirFont,
//        fontWeight = FontWeight.Medium,
//        fontSize = 15.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.5.sp
//    ),
//    labelMedium = TextStyle(
//        fontFamily = vazirFont,
//        fontWeight = FontWeight.Medium,
//        fontSize = 13.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.5.sp
//    ),
//    labelSmall = TextStyle(
//        fontFamily = vazirFont,
//        fontWeight = FontWeight.Medium,
//        fontSize = 11.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.5.sp
//    ),
//    //Display
//    displayLarge = TextStyle(
//        fontFamily = vazirFont,
//        fontWeight = FontWeight.Bold,
//        fontSize = 16.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.5.sp
//    ),
//    displayMedium = TextStyle(
//        fontFamily = vazirFont,
//        fontWeight = FontWeight.Bold,
//        fontSize = 14.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.5.sp
//    ),
//    displaySmall = TextStyle(
//        fontFamily = vazirFont,
//        fontWeight = FontWeight.Bold,
//        fontSize = 12.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.5.sp
//    )
//)