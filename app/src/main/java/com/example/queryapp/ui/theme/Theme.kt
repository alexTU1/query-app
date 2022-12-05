package com.example.queryapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val BeginnerDarkColorPalette = darkColors(
    primary = DarkPurple,
    primaryVariant = MediumPurple,
    secondary = LightPurple,
    onBackground = SubmitBlue
)

private val BeginnerLightColorPalette = lightColors(
    primary = DarkPurple,
    primaryVariant = MediumPurple,
    secondary = LightPurple,
    onBackground = SubmitBlue

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val IntermediateDarkColorPalette = darkColors(
    primary = DarkBlue,
    primaryVariant = MediumBlue,
    secondary = LightBlue
)

private val IntermediateLightColorPalette = lightColors(
    primary = DarkBlue,
    primaryVariant = MediumBlue,
    secondary = LightBlue

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val AdvancedDarkColorPalette = darkColors(
    primary = DarkOrange,
    primaryVariant = MediumOrange,
    secondary = LightOrange
)

private val AdvancedLightColorPalette = lightColors(
    primary = DarkOrange,
    primaryVariant = MediumOrange,
    secondary = LightOrange

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)


@Composable
fun QueryappBeginnerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        BeginnerDarkColorPalette
    } else {
        BeginnerLightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun QueryappAdvancedTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        AdvancedDarkColorPalette
    } else {
        AdvancedLightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun QueryappIntermediateTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        IntermediateDarkColorPalette
    } else {
        IntermediateLightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}