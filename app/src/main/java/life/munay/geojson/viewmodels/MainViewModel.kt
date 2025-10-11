package life.munay.geojson.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.QuestionMark
import dagger.hilt.android.lifecycle.HiltViewModel
import life.munay.core.base.BaseViewModel
import life.munay.core.navigation.NavigationItem
import life.munay.core.navigation.NavigationRoutes
import life.munay.core.repositories.resource.ResourceRepository
import life.munay.geojson.R
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    resourceRepository: ResourceRepository
) : BaseViewModel(
    resourceRepository = resourceRepository
) {
    fun getNavigationItems(): List<NavigationItem> =
        listOf(
            NavigationItem(getString(R.string.maps), Icons.Default.Map, NavigationRoutes.Maps.route),
            NavigationItem(getString(R.string.about), Icons.Default.QuestionMark, NavigationRoutes.About.route)
        )
}
