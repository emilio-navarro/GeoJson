# GeoJson - Android GeoJSON Visualization Demo

A modern Android application demonstrating GeoJSON data visualization with interactive maps using Jetpack Compose, Material 3 design. This project showcases clean architecture patterns, multi-module design, and production-ready Android development practices.

## Project Overview

**NOTE**: This repository exists as a read-only public portfolio piece to showcase the architecture and technical depth of modern Android development with GeoJSON visualization. Contributions, Pull Requests, and Issues are not accepted for this specific project. Contact me if you want to be a contributor.

GeoJson Android Demo features:

- **Modern UI**: Jetpack Compose with Material 3 design system
- **Interactive Maps**: Custom canvas-based GeoJSON rendering with coordinate transformation
- **Clean Architecture**: Multi-module structure with proper separation of concerns
- **Comprehensive Testing**: 100% Mockito-based unit tests with GIVEN-WHEN-THEN format
- **Production Features**: Error handling, performance monitoring, and structured logging

## Architecture

### Android Multi-Module Architecture

- **UI Layer**: Jetpack Compose with Material 3 Design System and custom theming
- **Navigation**: Compose Navigation with type-safe routing and bottom bar integration
- **State Management**: Single Source of Truth pattern with StateFlow and ViewModels
- **Dependency Injection**: Hilt for clean architecture and ViewModel injection
- **Map Rendering**: Custom canvas-based GeoJSON visualization with coordinate conversion
- **Testing**: Comprehensive unit testing with Mockito (no Robolectric dependencies)
- **Modular Design**: Clean separation with app/core/about/maps modules

### Module Structure

```
Android/
├── app/                    # Main application module
│   ├── di/                # Dependency injection (Hilt modules)
│   ├── viewmodels/        # Application ViewModels
│   ├── base/              # Application class and base components
│   └── ui/                # Main UI screens and navigation
├── core/                   # Shared utilities and base classes
│   ├── base/              # BaseViewModel and core abstractions
│   ├── navigation/        # Navigation routes and models
│   ├── repositories/      # Repository patterns and implementations
│   ├── utilities/         # Performance monitoring and helpers
│   └── extensions/        # Kotlin extension functions
├── about/                  # About module with version information
│   └── viewmodels/        # About-specific ViewModels
└── maps/                   # GeoJSON mapping and visualization
    └── screens/           # Map rendering and coordinate conversion
```

## Features

### Core Application Features

- **GeoJSON Visualization** with custom canvas rendering and coordinate transformation
- **Interactive Maps** with real-time coordinate conversion (LatLng to Canvas)
- **Modern Compose UI** with Material 3 Design System and extended color schemes
- **Type-safe Navigation** with Compose Navigation Graph and bottom bar
- **Multi-module Architecture** with clean separation of concerns
- **Performance Monitoring** with coroutine-based measurement tools
- **Comprehensive Testing** with 100% Mockito-based unit tests
- **Dark/Light Theme Support** with extended Material 3 color schemes
- **About Screen** with application version information and metadata
- **Resource Management** with abstracted repository pattern
- **Error Handling** with structured exception management

### Testing Features

- **GIVEN-WHEN-THEN Format** for all unit tests following BDD principles
- **Mockito-Only Testing** with no Robolectric dependencies for faster execution
- **Comprehensive Coverage** across all modules (core, about, maps, app)
- **Coordinate Conversion Testing** with geographic edge cases and real-world locations
- **ViewModel Testing** with proper coroutine and StateFlow handling
- **Repository Testing** with Android Context mocking patterns
- **Dependency Injection Testing** for Hilt modules and providers

## Prerequisites

### Android Development

- **Android Studio**: 2024.2.1 (Ladybug) or newer
- **JDK**: 18 or higher
- **Android SDK**: API 33+ (minSdk: 33, targetSdk: 34)
- **Gradle**: 8.10+
- **Kotlin**: 2.0.20+

## Installation & Setup

### 1. Clone Repository

```bash
git clone https://github.com/emilio-navarro/munay-demos.git
cd munay-demos/GeoJson/Android
```

### 2. Android Application Setup

#### Step-by-Step Installation:

1. **Open in Android Studio**:
   ```bash
   # Navigate to Android directory
   cd Android
   
   # Open in Android Studio
   studio .
   # Or drag the Android folder into Android Studio
   ```

2. **Sync Gradle dependencies**:
   - Android Studio will prompt to sync
   - Or manually: File → Sync Project with Gradle Files

3. **Build the project**:
   ```bash
   ./gradlew build
   ```

4. **Run unit tests**:
   ```bash
   # Run all unit tests
   ./gradlew test
   
   # Run specific module tests
   ./gradlew :core:testDebugUnitTest
   ./gradlew :about:testDebugUnitTest
   ./gradlew :maps:testDebugUnitTest
   ./gradlew :app:testDebugUnitTest
   ```

5. **Run on device/emulator**:
   - Connect Android device or start emulator
   - Click Run in Android Studio

## Compose Architecture & UI Components

### Modern Compose Implementation

The GeoJson app demonstrates modern Android development using Jetpack Compose with clean architecture following Material 3 design principles.

#### Navigation Graph Structure

```kotlin
// Type-safe navigation with Compose Navigation
sealed class NavigationRoutes(val route: String) {
    data object Maps : NavigationRoutes("maps")
    data object About : NavigationRoutes("about")
}

// NavGraph.kt - Centralized navigation with Hilt ViewModel injection
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = NavigationRoutes.Maps.route
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = NavigationRoutes.Maps.route) {
            GeoJsonMap()
        }
        composable(route = NavigationRoutes.About.route) {
            val aboutViewModel: AboutViewModel = hiltViewModel()
            AboutScreen(viewModel = aboutViewModel)
        }
    }
}
```

#### Custom GeoJSON Visualization Components

##### 1. GeoJsonMap - Interactive Canvas Rendering

```kotlin
@Composable
fun GeoJsonMap() {
    // Real-time GeoJSON rendering with coordinate conversion
    Canvas(modifier = Modifier.fillMaxSize()) {
        // Custom drawing logic for GeoJSON features
        // Coordinate transformation from LatLng to canvas points
    }
}

// Coordinate conversion function with comprehensive testing
fun latLngToCanvasPoint(
    latLng: LatLng, 
    width: Float, 
    height: Float, 
    scaleFactor: Float
): PointF {
    val x = (latLng.longitude + 180f) * scaleFactor
    val y = height - ((latLng.latitude + 90f) * (height / 180f))
    return PointF(x.toFloat(), y.toFloat())
}
```

**Features**:
- **Custom Canvas Rendering** with efficient GeoJSON visualization
- **Coordinate Transformation** from geographic to screen coordinates
- **Material 3 Integration** with themed colors and styling
- **Responsive Design** that adapts to different screen sizes
- **Performance Optimized** rendering for smooth user experience

##### 2. AboutScreen - Application Information Display

```kotlin
@Composable
fun AboutScreen(viewModel: AboutViewModel) {
    val versionInfo by viewModel.applicationVersionInfo.collectAsState()
    
    // Material 3 design with proper theming
    ScrollablePage {
        ApplicationInfoCard(versionInfo = versionInfo)
    }
}
```

**Features**:
- **Version Information** with build details and metadata
- **Material 3 Cards** with proper elevation and theming
- **Scrollable Layout** with proper content organization
- **StateFlow Integration** with reactive UI updates

#### Material 3 Theme System

##### Extended Color Scheme

```kotlin
// Custom Material 3 extended colors
val MaterialTheme.extendedColorScheme: ExtendedColorScheme
    @Composable @ReadOnlyComposable
    get() = LocalExtendedColorScheme.current

// Usage in Composables
Card(
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.extendedColorScheme.backgroundContent
    )
) {
    // Content with themed colors
}
```

##### Dynamic Theming

```kotlin
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    // Theme configuration with extended color support
    // Material 3 dynamic colors for Android 12+
    // Custom typography and extended color palette
}
```

**Features**:
- **Dark/Light Mode** with system preference detection
- **Extended Color Palette** beyond Material 3 defaults
- **Dynamic Colors** (Android 12+ Material You support)
- **Custom Typography** with consistent text styling

#### Bottom Navigation Integration

```kotlin
@Composable
fun BottomBar(
    navController: NavController,
    navigationItems: List<NavigationItem>
) {
    NavigationBar {
        navigationItems.forEach { item ->
            NavigationBarItem(
                selected = isCurrentRoute(item.route),
                onClick = { navController.navigate(item.route) },
                icon = { Icon(item.image, contentDescription = item.title) },
                label = { Text(item.title) }
            )
        }
    }
}
```

## Dependencies

### Android Dependencies (Latest 2024 Versions)

#### Jetpack Compose & Modern UI

```kotlin
// Compose BOM - Manages all Compose library versions
implementation(platform("androidx.compose:compose-bom:2024.12.00"))

// Core Compose UI
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.ui:ui-graphics")
implementation("androidx.compose.ui:ui-tooling-preview")

// Material 3 Design System
implementation("androidx.compose.material3:material3")
implementation("androidx.compose.material:material-icons-core")
implementation("androidx.compose.material:material-icons-extended")

// Compose Integration
implementation("androidx.activity:activity-compose:1.9.3")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
```

#### Navigation & Architecture

```kotlin
// Compose Navigation with type safety
implementation("androidx.navigation:navigation-compose:2.8.4")

// Dependency Injection with Hilt
implementation("com.google.dagger:hilt-android:2.52")
implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
kapt("com.google.dagger:hilt-android-compiler:2.52")

// AndroidX Lifecycle
implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
```

#### Core Android Libraries

```kotlin
// AndroidX Core
implementation("androidx.core:core-ktx:1.15.0")
implementation("androidx.multidex:multidex:2.0.1")

// Google Maps for coordinate handling
implementation("com.google.android.gms:play-services-maps:19.0.0")
```

#### Testing Framework (Comprehensive Mockito-Only)

```kotlin
// Unit Testing
testImplementation("junit:junit:4.13.2")
testImplementation("org.mockito:mockito-core:5.14.2")
testImplementation("org.mockito.kotlin:mockito-kotlin:5.4.0")

// Coroutines Testing
testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")

// Architecture Testing
testImplementation("androidx.arch.core:core-testing:2.2.0")

// No Robolectric - Pure Mockito-based testing
// Faster execution, focused on business logic
```

## Usage Guide

### Starting the Application

1. **Build and Install**:
   ```bash
   ./gradlew installDebug
   ```

2. **Launch Application**:
   - Open the GeoJson app on your device
   - The app starts with the Maps screen showing GeoJSON visualization

3. **Navigate Between Screens**:
   - Use the bottom navigation bar to switch between Maps and About screens
   - Maps screen displays interactive GeoJSON rendering
   - About screen shows application version and build information

### GeoJSON Visualization Features

The Maps screen demonstrates:
- **Interactive Canvas**: Custom-drawn GeoJSON features
- **Coordinate Conversion**: Real-time transformation from geographic to screen coordinates
- **Responsive Design**: Adapts to different screen sizes and orientations
- **Performance Optimized**: Smooth rendering with efficient drawing operations

## Key Architecture Patterns

### Single Source of Truth Pattern

```kotlin
// ViewModel owns and manages all StateFlows
class AboutViewModel @Inject constructor(
    resourceRepository: ResourceRepository
) : BaseViewModel(resourceRepository) {
    
    private val _applicationVersionInfo = MutableStateFlow("")
    val applicationVersionInfo: StateFlow<String> = _applicationVersionInfo.asStateFlow()
    
    fun getApplicationVersionInfo(): String {
        return try {
            // Business logic for version info retrieval
            "Version ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})"
        } catch (exception: Exception) {
            handleError(exception)
            "Version information unavailable"
        }
    }
}
```

### Reactive UI with Compose

```kotlin
@Composable
fun AboutScreen(viewModel: AboutViewModel) {
    // Observe StateFlows in Composables
    val versionInfo by viewModel.applicationVersionInfo.collectAsState()
    
    // UI automatically recomposes when state changes
    LazyColumn {
        item {
            Text(
                text = versionInfo,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
```

### Multi-Module Dependency Management

```kotlin
// Module Dependencies
// app → depends on core + about + maps
// about → depends on core (shared utilities)
// maps → depends on core (shared utilities)
// core → standalone (base module)

// Clean separation with proper abstraction layers
interface ResourceRepository {
    fun getString(resId: Int): String
}

class ResourceRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceRepository {
    override fun getString(resId: Int): String = context.getString(resId)
}
```

## Configuration

### Customizing the Application

#### Update App Theme

```kotlin
// In core/src/main/java/life/munay/core/resusables/composables/themes/
// Modify colors in ExtendedColorScheme for custom theming
val extendedColorLight = ExtendedColorScheme(
    primary = Color(0xFF1976D2),
    backgroundScreen = Color(0xFFFAFAFA),
    // Customize other colors
)
```

#### Modify GeoJSON Data

```kotlin
// In maps module, update the GeoJSON resource
// Replace core/src/main/res/raw/countries_geojson.json
// with your custom GeoJSON data
```

## Troubleshooting

### Common Issues

#### Build Failures

- **Gradle Sync**: Ensure Android Studio syncs successfully
- **JDK Version**: Verify JDK 18+ is configured
- **SDK Versions**: Check compileSdk 34 and targetSdk 34
- **Dependencies**: Verify all dependencies are resolved

#### Test Failures

- **Mockito Setup**: Ensure MockitoAnnotations.openMocks() is called
- **Coroutine Testing**: Use StandardTestDispatcher for async tests
- **Context Mocking**: Properly mock Android Context dependencies
- **Type Safety**: Check Float/Double type conversions in coordinate tests

#### UI Issues

- **Compose Preview**: Verify previews render correctly
- **Theme Application**: Check Material 3 theme is applied
- **Navigation**: Ensure proper navigation graph setup
- **State Management**: Verify StateFlow observation patterns

### Debug Commands

#### Android Debugging

```bash
# View app logs
adb logcat | grep "GeoJson"

# Check app installation
adb shell pm list packages | grep munay

# Monitor performance
adb shell dumpsys activity | grep munay
```

## API Reference

### Core Interfaces

#### ResourceRepository

```kotlin
interface ResourceRepository {
    fun getString(resId: Int): String
}
```

#### BaseViewModel

```kotlin
open class BaseViewModel(
    protected val resourceRepository: ResourceRepository
) : ViewModel() {
    protected fun getString(resId: Int): String = resourceRepository.getString(resId)
}
```

### Navigation Models

#### NavigationItem

```kotlin
data class NavigationItem(
    val title: String,
    val image: ImageVector,
    val route: String
)
```

#### NavigationRoutes

```kotlin
sealed class NavigationRoutes(val route: String) {
    data object Maps : NavigationRoutes("maps")
    data object About : NavigationRoutes("about")
}
```

### Maps API

#### Coordinate Conversion

```kotlin
fun latLngToCanvasPoint(
    latLng: LatLng,
    width: Float,
    height: Float,
    scaleFactor: Float
): PointF
```

## Roadmap & Future Development

### Current Status: Demo Complete v1.0

GeoJson Android Demo is a fully functional application showcasing modern Android development practices with GeoJSON visualization. The current implementation provides a solid foundation for mapping applications.

### Planned Enhancements & Features

#### Phase 1: Enhanced Mapping Features

**Interactive GeoJSON Features**
- Touch interaction with map features
- Feature selection and highlighting  
- Information popups for selected features
- Zoom and pan gesture support

**Advanced Visualization**
- Multiple layer support
- Custom styling for different feature types
- Animated transitions between features
- Performance optimization for large datasets

#### Phase 2: Advanced Capabilities

**Data Integration**
- Remote GeoJSON loading from APIs
- Caching mechanisms for offline support
- Real-time data updates
- Multiple data source integration

**User Experience**
- Search functionality for locations
- Bookmarking favorite locations
- Custom map styles and themes
- Accessibility improvements

#### Phase 3: Production Features

**Performance & Monitoring**
- Advanced performance metrics
- Memory usage optimization
- Battery usage monitoring
- Crash reporting integration

**Testing & Quality**
- UI testing with Compose testing framework
- Integration testing across modules
- Performance testing benchmarks
- Accessibility testing automation

## Contributing

### Exploration Welcome

Anyone is welcome to:

- Star the repository to show support
- Explore the codebase and learn from the implementation
- Read the documentation and understand the architecture
- Report bugs through GitHub Issues
- Suggest features and improvements
- Share feedback on the implementation

### Selective Contribution Process

This project maintains high code quality and architectural integrity. To ensure the best possible contributions:

#### Step 1: Request Access 

Before cloning or submitting PRs:

1. Email the maintainer: [y2k_eclipse@hotmail.com](mailto:y2k_eclipse@hotmail.com)
2. Include in your request:
   - Your GitHub username
   - Brief description of your background/experience
   - Specific feature/area you'd like to contribute to
   - Expected timeline for your contribution

#### Step 2: Approval Process 

Maintainer will evaluate based on:

- Technical expertise alignment with project needs
- Quality of proposed contribution
- Commitment to project standards
- Available capacity for code review and mentorship

#### Step 3: Authorized Development 

Once approved, you'll receive:

- Repository access permissions
- Detailed contribution guidelines
- Code review process documentation
- Direct communication channel with maintainer

### Development Workflow (Approved Contributors)

1. Fork the repository (after approval)
2. Create feature branch: `git checkout -b feature/amazing-feature`
3. Follow coding standards: Ktlint, proper documentation
4. Include comprehensive tests: Unit tests with Mockito
5. Submit PR with detailed description and screenshots
6. Code review: Collaborative review with maintainer
7. Merge: After approval and CI/CD validation

### Contribution Standards

#### Code Quality Requirements

- **Kotlin**: Official conventions + ktlint compliance
- **Compose**: Material 3 best practices + performance optimization
- **Testing**: GIVEN-WHEN-THEN format with Mockito-only approach
- **Documentation**: Clear comments for complex mapping logic
- **Architecture**: Follow established multi-module patterns

#### Testing Guidelines

- **Unit Tests**: 100% Mockito-based with no Robolectric
- **Coverage**: Comprehensive test coverage for new features
- **Format**: GIVEN-WHEN-THEN BDD format for all tests
- **Edge Cases**: Include error scenarios and boundary conditions
- **Performance**: Consider test execution speed and reliability

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- **Google Maps**: Coordinate handling and geographic utilities (`com.google.android.gms:play-services-maps`)
- **Jetpack Compose**: Modern declarative UI toolkit for Android
- **Material Design 3**: Beautiful and accessible design system  
- **Hilt**: Powerful dependency injection framework
- **Kotlin Coroutines**: Excellent async programming support
- **Mockito**: Comprehensive mocking framework for unit testing

## Support

### Getting Help
- **GitHub Issues**: [GitHub Issues](https://github.com/emilio-navarro/GeminiMQTT/issues)
- **Documentation**: Check this README for common solutions
- **Direct Contact**: Reach out to **Emilio Navarro** at [y2k_eclipse@hotmail.com](mailto:y2k_eclipse@hotmail.com)
- **LinkedIn**: Connect with **Emilio Navarro** on [LinkedIn](https://www.linkedin.com/in/emilionavarro/)

### Reporting Issues

When reporting issues, please include:

- **Environment**: Android version, device model, Android Studio version
- **Steps to reproduce**: Detailed reproduction steps
- **Expected behavior**: What should happen
- **Actual behavior**: What actually happens
- **Logs**: Relevant Android logcat output
- **Test Results**: Unit test execution results if applicable

---

**Built with ❤️ for modern Android development and GeoJSON visualization**
