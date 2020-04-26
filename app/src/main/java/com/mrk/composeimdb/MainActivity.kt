package com.mrk.composeimdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import com.mrk.composeimdb.navigation.Navigation
import com.mrk.composeimdb.navigation.Screen
import com.mrk.composeimdb.navigation.navigateTo
import com.mrk.composeimdb.pages.Root
import com.mrk.composeimdb.repositories.TmdbClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tmdb = TmdbClient().getTmdbService()

        setContent {
            Root(tmdb)
        }
    }

    override fun onBackPressed() {
        if (Navigation.currentScreen is Screen.Detail) {
            navigateTo(Screen.Home)
        } else {
            super.onBackPressed()
        }
    }
}
