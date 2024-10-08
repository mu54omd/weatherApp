package com.musashi.weatherapp.ui.theme
import android.os.Build
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.musashi.weatherapp.activity.AppTheme

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)
private val yellowScheme = lightColorScheme(
    primary = primaryYellow,
    onPrimary = onPrimaryYellow,
    primaryContainer = primaryContainerYellow,
    onPrimaryContainer = onPrimaryContainerYellow,
    secondary = secondaryYellow,
    onSecondary = onSecondaryYellow,
    secondaryContainer = secondaryContainerYellow,
    onSecondaryContainer = onSecondaryContainerYellow,
    tertiary = tertiaryYellow,
    onTertiary = onTertiaryYellow,
    tertiaryContainer = tertiaryContainerYellow,
    onTertiaryContainer = onTertiaryContainerYellow,
    error = errorYellow,
    onError = onErrorYellow,
    errorContainer = errorContainerYellow,
    onErrorContainer = onErrorContainerYellow,
    background = backgroundYellow,
    onBackground = onBackgroundYellow,
    surface = surfaceYellow,
    onSurface = onSurfaceYellow,
    surfaceVariant = surfaceVariantYellow,
    onSurfaceVariant = onSurfaceVariantYellow,
    outline = outlineYellow,
    outlineVariant = outlineVariantYellow,
    scrim = scrimYellow,
    inverseSurface = inverseSurfaceYellow,
    inverseOnSurface = inverseOnSurfaceYellow,
    inversePrimary = inversePrimaryYellow,
    surfaceDim = surfaceDimYellow,
    surfaceBright = surfaceBrightYellow,
    surfaceContainerLowest = surfaceContainerLowestYellow,
    surfaceContainerLow = surfaceContainerLowYellow,
    surfaceContainer = surfaceContainerYellow,
    surfaceContainerHigh = surfaceContainerHighYellow,
    surfaceContainerHighest = surfaceContainerHighestYellow,
)

private val redScheme = lightColorScheme(
    primary = primaryRed,
    onPrimary = onPrimaryRed,
    primaryContainer = primaryContainerRed,
    onPrimaryContainer = onPrimaryContainerRed,
    secondary = secondaryRed,
    onSecondary = onSecondaryRed,
    secondaryContainer = secondaryContainerRed,
    onSecondaryContainer = onSecondaryContainerRed,
    tertiary = tertiaryRed,
    onTertiary = onTertiaryRed,
    tertiaryContainer = tertiaryContainerRed,
    onTertiaryContainer = onTertiaryContainerRed,
    error = errorRed,
    onError = onErrorRed,
    errorContainer = errorContainerRed,
    onErrorContainer = onErrorContainerRed,
    background = backgroundRed,
    onBackground = onBackgroundRed,
    surface = surfaceRed,
    onSurface = onSurfaceRed,
    surfaceVariant = surfaceVariantRed,
    onSurfaceVariant = onSurfaceVariantRed,
    outline = outlineRed,
    outlineVariant = outlineVariantRed,
    scrim = scrimRed,
    inverseSurface = inverseSurfaceRed,
    inverseOnSurface = inverseOnSurfaceRed,
    inversePrimary = inversePrimaryRed,
    surfaceDim = surfaceDimRed,
    surfaceBright = surfaceBrightRed,
    surfaceContainerLowest = surfaceContainerLowestRed,
    surfaceContainerLow = surfaceContainerLowRed,
    surfaceContainer = surfaceContainerRed,
    surfaceContainerHigh = surfaceContainerHighRed,
    surfaceContainerHighest = surfaceContainerHighestRed,
)
private val blueScheme = lightColorScheme(
    primary = primaryBlue,
    onPrimary = onPrimaryBlue,
    primaryContainer = primaryContainerBlue,
    onPrimaryContainer = onPrimaryContainerBlue,
    secondary = secondaryBlue,
    onSecondary = onSecondaryBlue,
    secondaryContainer = secondaryContainerBlue,
    onSecondaryContainer = onSecondaryContainerBlue,
    tertiary = tertiaryBlue,
    onTertiary = onTertiaryBlue,
    tertiaryContainer = tertiaryContainerBlue,
    onTertiaryContainer = onTertiaryContainerBlue,
    error = errorBlue,
    onError = onErrorBlue,
    errorContainer = errorContainerBlue,
    onErrorContainer = onErrorContainerBlue,
    background = backgroundBlue,
    onBackground = onBackgroundBlue,
    surface = surfaceBlue,
    onSurface = onSurfaceBlue,
    surfaceVariant = surfaceVariantBlue,
    onSurfaceVariant = onSurfaceVariantBlue,
    outline = outlineBlue,
    outlineVariant = outlineVariantBlue,
    scrim = scrimBlue,
    inverseSurface = inverseSurfaceBlue,
    inverseOnSurface = inverseOnSurfaceBlue,
    inversePrimary = inversePrimaryBlue,
    surfaceDim = surfaceDimBlue,
    surfaceBright = surfaceBrightBlue,
    surfaceContainerLowest = surfaceContainerLowestBlue,
    surfaceContainerLow = surfaceContainerLowBlue,
    surfaceContainer = surfaceContainerBlue,
    surfaceContainerHigh = surfaceContainerHighBlue,
    surfaceContainerHighest = surfaceContainerHighestBlue,
)



@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun WeatherAppTheme(
    appTheme: AppTheme = AppTheme.Light,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (appTheme.name == "Dark") dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      isSystemInDarkTheme() || appTheme.name == "Dark" -> darkScheme
      appTheme.name == "Yellow" -> yellowScheme
      appTheme.name == "Red" -> redScheme
      appTheme.name == "Blue" -> blueScheme
      else -> lightScheme
  }


  MaterialTheme(
    colorScheme = colorScheme.switch(),
    typography = Typography,
    content = content
  )
}

@Composable
private fun animateColor(targetValue: Color) = animateColorAsState(
    targetValue = targetValue,
    label = ""
).value

@Composable
fun ColorScheme.switch() = copy(
    primary = animateColor(primary),
    onPrimary = animateColor(onPrimary),
    primaryContainer = animateColor(primaryContainer),
    onPrimaryContainer = animateColor(onPrimaryContainer),
    secondary = animateColor(secondary),
    onSecondary = animateColor(onSecondary),
    secondaryContainer = animateColor(secondaryContainer),
    onSecondaryContainer = animateColor(onSecondaryContainer),
    tertiary = animateColor(tertiary),
    onTertiary = animateColor(onTertiary),
    tertiaryContainer = animateColor(tertiaryContainer),
    onTertiaryContainer = animateColor(onTertiaryContainer),
    error = animateColor(targetValue = error),
    onError = animateColor(targetValue = onError),
    errorContainer = animateColor(targetValue = errorContainer),
    onErrorContainer = animateColor(targetValue = onErrorContainer),
    background = animateColor(targetValue = background),
    onBackground = animateColor(targetValue = onBackground),
    surface = animateColor(targetValue = surface),
    onSurface = animateColor(targetValue = onSurface),
    surfaceVariant = animateColor(targetValue = surfaceVariant),
    onSurfaceVariant = animateColor(targetValue = onSurfaceVariant),
    outline = animateColor(targetValue = outline),
    outlineVariant = animateColor(targetValue = outlineVariant),
    scrim = animateColor(targetValue = scrim),
    inverseSurface = animateColor(targetValue = inverseSurface),
    inverseOnSurface = animateColor(targetValue = inverseOnSurface),
    inversePrimary = animateColor(targetValue = inversePrimary),
    surfaceDim = animateColor(targetValue = surfaceDim),
    surfaceBright = animateColor(targetValue = surfaceBright),
    surfaceContainerLowest = animateColor(targetValue = surfaceContainerLowest),
    surfaceContainerLow = animateColor(targetValue = surfaceContainerLow),
    surfaceContainer = animateColor(targetValue = surfaceContainer),
    surfaceContainerHigh = animateColor(targetValue = surfaceContainerHigh),
    surfaceContainerHighest = animateColor(targetValue = surfaceContainerHighest),


    )

