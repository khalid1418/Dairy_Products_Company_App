package com.example.dairyproductscompanyapp.setting

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class ThemeApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        val themeapp = ThemProvider(this).getThemeFromPreferences()
        AppCompatDelegate.setDefaultNightMode(themeapp)
    }
}