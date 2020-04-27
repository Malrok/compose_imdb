package com.mrk.composeimdb.pages.home

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.layout.padding
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Favorite
import androidx.ui.material.icons.filled.Home
import androidx.ui.material.icons.filled.Search
import androidx.ui.res.stringResource
import androidx.ui.unit.dp
import com.mrk.composeimdb.R
import com.mrk.composeimdb.pages.recent.RecentMovies
import com.mrk.composeimdb.pages.search.SearchMovie

@Composable
fun Home() {
    var selectedIndex by state { 0 }

    Scaffold(
        topAppBar = {
            TopAppBar(
                title = {
                    Text(
                        color = MaterialTheme.colors.secondary,
                        text = stringResource(id = R.string.app_name)
                    )
                }
            )
        },
        bodyContent = {
            Box(
                modifier = Modifier.padding(8.dp)
            ) {
                when (selectedIndex) {
                    0 -> RecentMovies()
                    1 -> SearchMovie()
                    2 -> {
                        // TODO
                        Box()
                    }
                }
            }
        },
        bottomAppBar = {
            BottomAppBar {
                BottomNavigation {
                    BottomNavigationItem(
                        icon = {
                            Icon(asset = Icons.Filled.Home)
                        },
                        selected = selectedIndex == 0,
                        onSelected = {
                            selectedIndex = 0
                        },
                        activeColor = MaterialTheme.colors.secondary
                    )
                    BottomNavigationItem(
                        icon = {
                            Icon(asset = Icons.Filled.Search)
                        },
                        selected = selectedIndex == 1,
                        onSelected = {
                            selectedIndex = 1
                        },
                        activeColor = MaterialTheme.colors.secondary
                    )
                    BottomNavigationItem(
                        icon = {
                            Icon(asset = Icons.Filled.Favorite)
                        },
                        selected = selectedIndex == 2,
                        onSelected = {
                            selectedIndex = 2
                        },
                        activeColor = MaterialTheme.colors.secondary
                    )
                }
            }
        }
    )
}