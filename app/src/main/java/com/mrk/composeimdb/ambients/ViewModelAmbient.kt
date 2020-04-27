package com.mrk.composeimdb.ambients

import androidx.compose.ambientOf
import com.mrk.composeimdb.viewmodels.MainViewModel

val ViewModelAmbient = ambientOf<MainViewModel> { error("view model has not been set") }