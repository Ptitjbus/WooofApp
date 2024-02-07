package com.mathisviollet.wooofapp.ui.components

import android.Manifest
import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import com.mathisviollet.wooofapp.R
import com.mathisviollet.wooofapp.managers.LocationService
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class, MapboxExperimental::class)
@Composable
fun MapComponent() {

    val coroutineScope = rememberCoroutineScope()
    val zoomLevel = 19.0
    val context = LocalContext.current
    val state = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    val mapViewportState = rememberMapViewportState {
        setCameraOptions {
            center(Point.fromLngLat(0.0, 0.0))
            zoom(zoomLevel)
            pitch(0.0)
        }
    }
    val pointsToAnnotate = remember { mutableStateListOf<Point>() }

    MapboxMap(
        modifier = Modifier.fillMaxSize(),
        mapViewportState = mapViewportState,
    ){
        pointsToAnnotate.forEach { point ->
            AddPointer(point = point)
        }
    }

    LaunchedEffect(Unit) {
        if(!state.status.isGranted) {
            state.launchPermissionRequest()
        }
    }

    LaunchedEffect(state){
        coroutineScope.launch {
            if(state.status.isGranted) {
                LocationService.getUserLocation(context = context) { currentLocation ->
                    val mapAnimationOptions = MapAnimationOptions.Builder().duration(1500L).build()
                    pointsToAnnotate.add(currentLocation)
                    mapViewportState.flyTo(
                        CameraOptions.Builder()
                            .center(currentLocation)
                            .zoom(zoomLevel)
                            .build(),
                        mapAnimationOptions
                    )
                }

            }
        }
    }
}

@OptIn(MapboxExperimental::class)
@Composable
fun AddPointer(point:Point){
    val context = LocalContext.current
    val drawable = ResourcesCompat.getDrawable(
        context.resources,
        R.drawable.point_map,
        null
    )
    val bitmap = drawable!!.toBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    PointAnnotation(
        iconImageBitmap = bitmap,
        iconSize = 0.5,
        point = point,
        onClick = {
            Toast.makeText(
                context,
                "Clicked on Circle Annotation: $it",
                Toast.LENGTH_SHORT
            ).show()
            true
        }
    )
}