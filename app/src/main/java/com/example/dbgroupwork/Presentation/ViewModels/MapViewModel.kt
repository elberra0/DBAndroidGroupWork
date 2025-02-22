package com.example.dbgroupwork.Presentation.ViewModels

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.dbgroupwork.Presentation.Views.GoogleMapView
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class MapViewModel {
}

@Composable
fun GetUserLocation(): LatLng? {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var userLocation by remember { mutableStateOf<LatLng?>(null) }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    userLocation = LatLng(it.latitude, it.longitude)
                }
            }
        }
    }
    return userLocation
}

@Composable
fun RequestMapPermissions(onPermissionsGranted: () -> Unit) {
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineLocationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val coarseLocationGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false

        if (fineLocationGranted || coarseLocationGranted) {
            onPermissionsGranted()
        } else {
            println("Permisos no concedidos")
        }
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}

suspend fun GetNearbyGyms(location: LatLng,radius: Int = 1000):List<LatLng>{
    val apiKey = "AIzaSyB8tgjWDhvHrBZ43AD_qPpaDIWLy7Mtzmo"

    val searchable = "gym"

    val url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
            "?location=${location.latitude},${location.longitude}" +
            "&radius=$radius&type=$searchable&key=$apiKey"

    return withContext(Dispatchers.IO) {
        try {
            val response = URL(url).readText()
            val jsonObject = JSONObject(response)
            val results = jsonObject.getJSONArray("results")
            val gyms = mutableListOf<LatLng>()

            for (i in 0 until results.length()) {
                val result = results.getJSONObject(i)
                val geometry = result.getJSONObject("geometry").getJSONObject("location")
                val lat = geometry.getDouble("lat")
                val lng = geometry.getDouble("lng")
                gyms.add(LatLng(lat, lng))
            }

            gyms
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}