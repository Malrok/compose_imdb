package com.mrk.composeimdb

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import com.mrk.composeimdb.navigation.Navigation
import com.mrk.composeimdb.navigation.Screen
import com.mrk.composeimdb.navigation.navigateTo
import com.mrk.composeimdb.pages.Root
import com.mrk.composeimdb.repositories.TmdbClient
import com.mrk.composeimdb.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val tmdb = TmdbClient().getTmdbService()
        val mainViewModel: MainViewModel by viewModels()

        setContent {
            Root(mainViewModel)
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
