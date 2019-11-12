[![Build Status](https://travis-ci.org/adsf117/Yelp_AAC.svg?branch=develop)](https://travis-ci.org/adsf117/Yelp_AAC.svg?branch=develop)
[![codecov](https://codecov.io/gh/adsf117/Yelp_AAC/branch/develop/graph/badge.svg)](https://codecov.io/gh/adsf117/Yelp_AAC)
[![CodeFactor](https://www.codefactor.io/repository/github/adsf117/clean_post_aac/badge)](https://www.codefactor.io/repository/github/adsf117/clean_post_aac)
# Yelp Android Architecture Components

Follow :

[Dependency Inversion Principle (DIP)](https://martinfowler.com/articles/dipInTheWild.html)(without frameworks)

[Guide to app architecture](https://developer.android.com/jetpack/docs/guide) 

[Continuous Integration](https://www.martinfowler.com/articles/continuousIntegration.html) [Using Tavis](https://travis-ci.org/)

## How to use this project

You can use Android Studio or Intellij to work with this repository.

First thing you will need to compile this project is to get an [API Key from Yelp](https://www.yelp.com/developers) and replace the constant on file app build.gradle 

```https://github.com/adsf117/Yelp_AAC/blob/develop/app/build.gradle
 buildConfigField "String", "API_KEY_VALUE", '"your-private-apikey"
```

# How it looks
![alt text](https://github.com/adsf117/Yelp_AAC/blob/develop/info/demo_small.gif)

## Architecture

There are three main layers: 
data: in this module has all details Service and Database 
repository : has sync server  
presentation: This module has all android framework using MVVM

![alt text](https://github.com/adsf117/Yelp_AAC/blob/develop/info/Arquitecture.png)

## Libraries Used :

* [Room](https://developer.android.com/jetpack/androidx/releases/room)
* [Workout manager](https://developer.android.com/topic/libraries/architecture/workmanager/advanced/coroutineworker)
* [Navigation Component](https://codelabs.developers.google.com/codelabs/android-navigation/index.html?index=..%2F..index#0)
* [MotionLayout](https://developer.android.com/training/constraint-layout/motionlayout) 
* [Data Binding](https://codelabs.developers.google.com/codelabs/android-databinding/index.html?index=..%2F..index#5) 
* [Retrofit2](https://square.github.io/retrofit/)
* [Coroutines](https://developer.android.com/kotlin/coroutines)

## References:

Udacity
* [Advanced Android with Kotlin](https://classroom.udacity.com/courses/ud940)

Google Codelabs:
* [Kotlin Bootcamp Course](https://codelabs.developers.google.com/kotlin-bootcamp/)
* [Navigation Codelab](https://codelabs.developers.google.com/codelabs/android-navigation/index.html?index=..%2F..index#6)
* [Android Room with a View - Kotlin](https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/index.html?index=..%2F..index#0)
* [Using Kotlin Coroutines in your Android App](https://codelabs.developers.google.com/codelabs/kotlin-coroutines/index.html?index=..%2F..index#6)
* [MotionLayout](https://codelabs.developers.google.com/codelabs/motion-layout/index.html?index=..%2F..index#8)

Raywenderlich
* [Jetpack navigation controlle](https://www.raywenderlich.com/5365-jetpack-navigation-controller)
* [Android Architecture Components: Getting Started](https://www.raywenderlich.com/164-android-architecture-components-getting-started)
* [android architecture components livedata](https://www.raywenderlich.com/4980-android-architecture-components-livedata)
* [android architecture components viewmodel](https://www.raywenderlich.com/5046-android-architecture-components-viewmodel)

Others:
* [How to configure JaCoCo for Kotlin & Java project](http://vgaidarji.me/blog/2017/12/20/how-to-configure-jacoco-for-kotlin-and-java-project/)
* [Mocking Coroutines](https://proandroiddev.com/mocking-coroutines-7024073a8c09)
