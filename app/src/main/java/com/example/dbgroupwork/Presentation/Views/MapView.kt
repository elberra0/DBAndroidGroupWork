package com.example.dbgroupwork.Presentation.Views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.dbgroupwork.Presentation.ViewModels.GetNearbyGyms
import com.example.dbgroupwork.Presentation.ViewModels.GetUserLocation
import com.example.dbgroupwork.Presentation.ViewModels.Gym
import com.example.dbgroupwork.Presentation.ViewModels.RequestMapPermissions
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

class MapView {
}

@Composable
fun GoogleMapView() {
    var permissionsGranted by remember { mutableStateOf(false) }

    RequestMapPermissions {
        permissionsGranted = true
    }

    if (!permissionsGranted) {
        Text(text = "Solicitando permisos...")
        return
    }

    val userLocation = GetUserLocation()
    val context = LocalContext.current
    var gymLocations by remember { mutableStateOf<List<Gym>>(emptyList()) }

    LaunchedEffect(userLocation) {
        userLocation?.let { location ->
            gymLocations = GetNearbyGyms(location)
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = MapProperties(isMyLocationEnabled = true),
        uiSettings = MapUiSettings(zoomControlsEnabled = false),
        cameraPositionState = rememberCameraPositionState {
            userLocation?.let {
                position = CameraPosition.fromLatLngZoom(it, 16f)
            }
        }
    ) {
        userLocation?.let {
            Marker(
                state = rememberMarkerState(position = it),
                title = "Me!",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
            )
        }

        gymLocations.forEach { gym ->
            Marker(
                state = rememberMarkerState(position = gym.location),
                title = gym.name,
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
            )
        }
    }
}