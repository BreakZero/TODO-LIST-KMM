package com.easy.todolist.android.feature.settings

sealed interface SettingsEvent {
    object Logout: SettingsEvent
}