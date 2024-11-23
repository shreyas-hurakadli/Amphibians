# Amphibians App

**Author**: Shreyas Hurakadli

The **Amphibians** app is a practice project in Google's **Codelabs** to help developers learn how to build Android applications using modern tools and frameworks. This app retrieves data about amphibians from an online API and displays it on the screen. It uses **Retrofit** to fetch the data and **Coil** to cache images for more efficient loading.

## Key Features
- **Data Fetching**: The app retrieves amphibian data from an API hosted at:
  [https://android-kotlin-fun-mars-server.appspot.com/amphibians](https://android-kotlin-fun-mars-server.appspot.com/amphibians).
- **Image Caching**: The app uses the **Coil** library to cache images and improve performance by reducing redundant network requests.
- **Networking with Retrofit**: **Retrofit** is used to make API calls and parse the JSON response into Kotlin data objects.
  
## Tech Stack
- **Kotlin**: The app is written in Kotlin, the recommended language for Android development.
- **Retrofit**: A type-safe HTTP client for making network requests and parsing JSON responses.
- **Coil**: A lightweight image loading library for Android with built-in caching support.
- **Android Architecture Components**: The app follows modern Android architecture, utilizing components like `MutableState`, `ViewModel`.

## Installation

To run this project locally:

1. Clone the repository:
   ```bash
   git clone https://github.com/shreyas-hurakadli/amphibians.git
