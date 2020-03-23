package com.mrk.composeimdb.ui.ambients

import androidx.compose.ambientOf
import com.mrk.composeimdb.models.Configuration

val TmdbConfigurationAmbient = ambientOf<Configuration> { error("TmdbConfigurationAmbient not initialised") }