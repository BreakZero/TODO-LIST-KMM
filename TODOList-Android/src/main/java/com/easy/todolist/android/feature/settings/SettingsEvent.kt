package com.easy.todolist.android.feature.settings

sealed interface SettingsEvent {
    data object Logout: SettingsEvent
}