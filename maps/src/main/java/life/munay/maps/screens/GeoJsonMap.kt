package life.munay.maps.screens

import android.app.Activity
import android.content.pm.ActivityInfo
import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.data.geojson.GeoJsonLayer
import life.munay.core.resusables.composables.themes.AppTheme
import org.json.JSONObject

@Composable
fun GeoJsonMap() {
    val context = LocalContext.current
    val activity = context as? Activity

    DisposableEffect(Unit) {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        onDispose {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    val geoJsonLayer = remember { mutableStateOf<GeoJsonLayer?>(null) }

    LaunchedEffect(Unit) {
        val json = context.resources.openRawResource(life.munay.core.R.raw.countries_geojson)
            .bufferedReader().use { it.readText() }
        val geoJson = JSONObject(json)
        geoJsonLayer.value = GeoJsonLayer(null, geoJson)
    }

    geoJsonLayer.value?.let { layer ->
        GeoJsonCanvas(layer)
    }
}

@Composable
fun GeoJsonCanvas(geoJsonLayer: GeoJsonLayer) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height
        val scaleFactor = width / 360f

        for (feature in geoJsonLayer.features) {
            if (feature.geometry is com.google.maps.android.data.geojson.GeoJsonPolygon) {
                val polygon = feature.geometry as com.google.maps.android.data.geojson.GeoJsonPolygon
                val outerRing = polygon.coordinates[0]
                val path = Path()

                val firstPoint = latLngToCanvasPoint(outerRing[0], width, height, scaleFactor)
                path.moveTo(firstPoint.x, firstPoint.y)

                for (coord in outerRing) {
                    val point = latLngToCanvasPoint(coord, width, height, scaleFactor)
                    path.lineTo(point.x, point.y)
                }

                path.close()

                val fillColor = Color(0x7F0000FF)
                val strokeColor = Color(0xFF0000FF)

                drawPath(
                    path = path,
                    color = fillColor,
                    style = androidx.compose.ui.graphics.drawscope.Fill
                )

                drawPath(
                    path = path,
                    color = strokeColor,
                    style = androidx.compose.ui.graphics.drawscope.Stroke(
                        width = 4f,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }
        }
    }
}

// Convert LatLng to canvas coordinates
fun latLngToCanvasPoint(latLng: LatLng, width: Float, height: Float, scaleFactor: Float): PointF {
    // Simplified conversion: X is longitude scaled to canvas width, Y is latitude inverted and scaled
    val x = (latLng.longitude + 180f) * scaleFactor
    val y = height - ((latLng.latitude + 90f) * (height / 180f)) // Invert y to map latitude correctly
    return PointF(x.toFloat(), y.toFloat())
}

@Preview
@Composable
fun PreviewGeoJsonMap() {
    AppTheme {
        GeoJsonMap()
    }
}
