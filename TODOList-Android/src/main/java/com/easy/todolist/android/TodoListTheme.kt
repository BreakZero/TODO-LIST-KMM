package com.easy.todolist.android

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

@Composable
fun TodoListTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = Color(0xFFffb3af),
            onPrimary = Color(0xFF68000e),
            primaryContainer = Color(0xFF891b21),
            onPrimaryContainer = Color(0xFFffdad7),
            secondary = Color(0xFFffb4a3),
            onSecondary = Color(0xFF5e1606),
            secondaryContainer = Color(0xFF7d2c1a),
            onSecondaryContainer = Color(0xFFffdad2),
            background = Color(0xFF201a1a),
            onBackground = Color(0xFFede0de),
            surface = Color(0xFF201a1a),
            onSurface = Color(0xFFede0de)
        )
    } else {
        lightColorScheme(
            primary = Color(0xFFAA3336),
            onPrimary = Color(0xFFffffff),
            primaryContainer = Color(0xFFffdad7),
            onPrimaryContainer = Color(0xFF410005),
            secondary = Color(0xFF9c432e),
            onSecondary = Color(0xFFffffff),
            secondaryContainer = Color(0xFFffdad2),
            onSecondaryContainer = Color(0xFF3d0600),
            background = Color(0xFFfffbff),
            onBackground = Color(0xFF201a1a),
            surface = Color(0xFFfffbff),
            onSurface = Color(0xFF201a1a)
        )
    }
    val typography = Typography(
        bodyLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(
                window,
                view
            ).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
