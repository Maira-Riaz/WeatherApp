<div align="center">

# Weather App

**A modern Android weather application built with Kotlin and Jetpack Compose.**

Live current conditions and a weekly forecast for your location, powered by the Open-Meteo API.

<br />

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat-square&labelColor=1f2328)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=flat-square&labelColor=1f2328)
![MVVM](https://img.shields.io/badge/Architecture-MVVM-0B7285?style=flat-square&labelColor=1f2328)
![Coroutines](https://img.shields.io/badge/Kotlin-Coroutines-7F52FF?style=flat-square&labelColor=1f2328)
![Retrofit](https://img.shields.io/badge/Networking-Retrofit-2D3748?style=flat-square&labelColor=1f2328)
![Open-Meteo](https://img.shields.io/badge/Data-Open--Meteo-0B7285?style=flat-square&labelColor=1f2328)

<br />

[Overview](#overview) &nbsp;|&nbsp;
[Screenshots](#screenshots) &nbsp;|&nbsp;
[Features](#features) &nbsp;|&nbsp;
[Architecture](#architecture) &nbsp;|&nbsp;
[API](#api-integration) &nbsp;|&nbsp;
[Getting Started](#getting-started) &nbsp;|&nbsp;
[Contributing](#contributing)

</div>

---

## Overview

Weather App is a native Android application that fetches live weather data for the user's current location and presents it in a clean, responsive interface. It shows current conditions alongside a seven-day outlook, and treats loading, success, and error as first-class UI states so the screen always reflects what the app is actually doing.

The codebase follows **MVVM** with a **unidirectional data flow**: the UI observes immutable state exposed by a ViewModel, and the ViewModel retrieves data through a repository that wraps a Retrofit service. The app needs **no API key** to run.

### Highlights

- Declarative UI with Jetpack Compose
- Clear separation of presentation, state, and data layers
- Explicit loading, success, and error handling
- Correct runtime permission flow for location access
- Asynchronous networking with Kotlin Coroutines and Retrofit

## Screenshots

<div align="center">
<table>
  <tr>
     <td align="center" width="33%">
      <img src="Screenshots/loading-weather.jpeg" alt="Weather loading screen" width="100%" />
    </td>
    <td align="center" width="33%">
      <img src="Screenshots/current-weather.jpeg" alt="Current weather screen" width="100%" />
    </td>
    <td align="center" width="33%">
      <img src="Screenshots/weekly-weather.jpeg" alt="Weekly weather forecast" width="100%" />
    </td>
  </tr>
  <tr>
     <td align="center"><b>Loading state</b><br /><sub>Clear feedback while data is fetched</sub></td>
    <td align="center"><b>Current weather</b><br /><sub>Conditions and temperature at a glance</sub></td>
    <td align="center"><b>Weekly forecast</b><br /><sub>The week ahead in a scannable layout</sub></td>
  </tr>
</table>
</div>

## Features

| Feature | Description |
| :-- | :-- |
| **Current weather** | Present conditions and temperature for the device location. |
| **Weekly forecast** | Predictions for the upcoming week in an easy-to-scan layout. |
| **Location-based lookup** | Resolves the device location and fetches weather automatically. |
| **Runtime permissions** | Requests location access at runtime and handles grant and denial gracefully. |
| **Reactive UI state** | Loading, success, and error states are modeled explicitly and rendered accordingly. |
| **REST integration** | Retrieves data from the Open-Meteo API through Retrofit. |

## Technology Stack

| Category | Technology |
| :-- | :-- |
| Language | Kotlin |
| UI framework | Jetpack Compose |
| Architecture | MVVM, unidirectional data flow |
| State management | ViewModel, StateFlow |
| Concurrency | Kotlin Coroutines |
| Networking | Retrofit |
| Weather data | Open-Meteo API |
| Location | Google Play Services Location |
| IDE | Android Studio |

## Architecture

Each layer has a single responsibility and depends only on the layer beneath it.

```text
┌──────────────────────────┐
│       Compose UI         │
└────────────┬─────────────┘
             │  observes state / sends events
┌────────────▼─────────────┐
│        ViewModel         │
│    UI state and logic    │
└────────────┬─────────────┘
             │
┌────────────▼─────────────┐
│        Repository        │
│      Data retrieval      │
└────────────┬─────────────┘
             │
┌────────────▼─────────────┐
│   Retrofit API Service   │
└────────────┬─────────────┘
             │  HTTPS
┌────────────▼─────────────┐
│      Open-Meteo API      │
└──────────────────────────┘
```

| Layer | Responsibility |
| :-- | :-- |
| **Presentation** | Jetpack Compose renders weather data and recomposes when state changes. |
| **ViewModel** | Coordinates application logic and exposes UI state through `StateFlow`. |
| **Repository** | Single point of access to weather data; keeps data access out of the UI layer. |
| **API service** | Retrofit interface that communicates with Open-Meteo. |
| **Location** | Retrieves the device location through Google Play Services once permission is granted. |

### Request lifecycle

1. The app requests location permission at runtime.
2. When granted, the device location is retrieved via Google Play Services Location.
3. The ViewModel asks the repository for weather data using the coordinates.
4. The repository calls the Retrofit service, which queries Open-Meteo on a coroutine.
5. The response is mapped to a UI state (loading, success, or error) and emitted through `StateFlow`.
6. Compose collects the state and renders the matching screen.

### UI states

| State | Behavior |
| :-- | :-- |
| **Loading** | Shown while location and weather data are being retrieved. |
| **Success** | Current conditions and the weekly forecast are displayed. |
| **Error** | A failure (network, permission, or response) is surfaced to the user. |

## API Integration

Weather data comes from [Open-Meteo](https://open-meteo.com/), which requires no API key for standard non-commercial use.

A representative request looks like this:

```http
GET https://api.open-meteo.com/v1/forecast
    ?latitude=31.52
    &longitude=74.36
    &current=temperature_2m,weather_code
    &daily=weather_code,temperature_2m_max,temperature_2m_min
    &timezone=auto
```

> [!NOTE]
> The request above is illustrative. Refer to the [Open-Meteo documentation](https://open-meteo.com/en/docs) for the full list of supported parameters and response fields.

Weather data by [Open-Meteo.com](https://open-meteo.com/), licensed under [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/). Review the Open-Meteo terms before any commercial use.

## Permissions

The app uses the device location to determine which forecast to request. Location access is requested at runtime and the app handles a denied request without crashing.

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

## Getting Started

### Prerequisites

- Android Studio (current stable release recommended)
- A compatible Android SDK
- An Android emulator or a physical device
- An active internet connection
- Location permission enabled on the device

### Installation

```bash
# 1. Clone the repository
git clone https://github.com/Maira-Riaz/WeatherApp.git

# 2. Move into the project directory
cd WeatherApp
```

3. Open the project in Android Studio.
4. Wait for Gradle to sync and download dependencies.
5. Select an emulator or connect a device, then choose **Run**.

> [!TIP]
> On an emulator, set a location from the extended controls (the three-dot menu, then **Location**) so location-based weather can resolve.

### Build from the command line

```bash
./gradlew assembleDebug
```

The debug APK is generated under `app/build/outputs/apk/debug/`.

## Troubleshooting

<details>
<summary><b>The app shows an error instead of weather data</b></summary>
<br />

Confirm that the device has an internet connection and that location permission has been granted. On an emulator, make sure a location has been set.

</details>

<details>
<summary><b>Gradle sync fails</b></summary>
<br />

Verify that a compatible Android SDK is installed, then use **File, Sync Project with Gradle Files**. If the problem persists, run **Build, Clean Project** and sync again.

</details>

## Roadmap

- [ ] Search and save multiple locations
- [ ] Hourly forecast view
- [ ] Unit preferences (Celsius and Fahrenheit)
- [ ] Offline caching of the last successful response
- [ ] Unit tests for the ViewModel and repository

## Key Learnings

- Building native Android interfaces with Kotlin and Jetpack Compose
- Structuring an application with MVVM and clear layer boundaries
- Managing reactive UI state with ViewModel and StateFlow
- Integrating a REST API with Retrofit
- Performing asynchronous work with Kotlin Coroutines
- Accessing device location and handling runtime permissions
- Designing for loading, success, and error states rather than only the happy path

## Contributing

Contributions, issues, and suggestions are welcome.

1. Fork the repository.
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit your changes with a clear message: `git commit -m "Add your feature"`
4. Push the branch: `git push origin feature/your-feature`
5. Open a pull request describing what changed and why.

## License

Add a `LICENSE` file to the repository and name it here (for example, MIT).

## Author

<table>
  <tr>
    <td>
      <b>Maira Riaz</b><br />
      Software Engineer | Android Development | Kotlin and Jetpack Compose<br /><br />
      <a href="https://github.com/Maira-Riaz">GitHub</a> &nbsp;|&nbsp;
      <a href="https://mairariaz-portfolio.vercel.app">Portfolio</a>
    </td>
  </tr>
</table>

---

<div align="center">
  <sub>Built as a practical project to explore modern Android development, API integration, and reactive UI design.</sub>
</div>
