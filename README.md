[![Build Status](https://travis-ci.org/adsf117/Yelp_AAC.svg?branch=develop)](https://travis-ci.org/adsf117/Yelp_AAC)
[![codecov](https://codecov.io/gh/adsf117/Yelp_AAC/branch/develop/graph/badge.svg)](https://codecov.io/gh/adsf117/Yelp_AAC)
[![CodeFactor](https://www.codefactor.io/repository/github/adsf117/clean_post_aac/badge)](https://www.codefactor.io/repository/github/adsf117/clean_post_aac)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io)

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
* [Android X Preference](https://developer.android.com/jetpack/androidx/releases/preference)
* [MotionLayout](https://developer.android.com/training/constraint-layout/motionlayout) 
* [Data Binding](https://codelabs.developers.google.com/codelabs/android-databinding/index.html?index=..%2F..index#5) 
* [Retrofit2](https://square.github.io/retrofit/)
* [Coroutines](https://developer.android.com/kotlin/coroutines)

## References:

Udacity:
* [Build your First app Lesson 10 (Designing for Everyone 27.Add Dark Mode Support)](https://classroom.udacity.com/courses/ud9012/lessons/d6418953-69fb-41ab-acc4-aafc681ccf41/concepts/d83cd0af-5688-4848-9f15-2f6628c634f6)
* [Build your First app Lesson 7 (RecyclerView 11.Improving Data Refresh)](https://classroom.udacity.com/courses/ud9012/lessons/ee5a525f-0ba3-4d25-ba29-1fa1d6c567b8/concepts/7047e569-b5a2-4767-a589-6fb4e8e367d4)
* [Advanced Android with Kotlin Lesson 1. Using Notification](https://classroom.udacity.com/courses/ud940/lessons/66466df1-b797-4844-bcb6-658a1b986e3d/concepts/10708a67-9855-4634-a1f8-67011646d86c)
* [Advanced Android with Kotlin](https://classroom.udacity.com/courses/ud940)



Google Codelabs:
* [Kotlin Bootcamp Course](https://codelabs.developers.google.com/kotlin-bootcamp/)
* [Navigation Codelab](https://codelabs.developers.google.com/codelabs/android-navigation/index.html?index=..%2F..index#6)
* [Android Room with a View - Kotlin](https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/index.html?index=..%2F..index#0)
* [Using Kotlin Coroutines in your Android App](https://codelabs.developers.google.com/codelabs/kotlin-coroutines/index.html?index=..%2F..index#6)
* [MotionLayout](https://codelabs.developers.google.com/codelabs/motion-layout/index.html?index=..%2F..index#8)


Raywenderlich:z
* [Jetpack navigation controlle](https://www.raywenderlich.com/5365-jetpack-navigation-controller)
* [Android Architecture Components: Getting Started](https://www.raywenderlich.com/164-android-architecture-components-getting-started)
* [android architecture components livedata](https://www.raywenderlich.com/4980-android-architecture-components-livedata)
* [android architecture components viewmodel](https://www.raywenderlich.com/5046-android-architecture-components-viewmodel)
* [Supporting Dark Theme](https://www.raywenderlich.com/5697228-supporting-dark-theme)


Others:
* [How to configure JaCoCo for Kotlin & Java project](http://vgaidarji.me/blog/2017/12/20/how-to-configure-jacoco-for-kotlin-and-java-project/)
* [Android, ktlint, and pre-commit Git Hook](https://medium.com/@alistair.cerio/android-ktlint-and-pre-commit-git-hook-5dd606e230a9)
* [Mocking Coroutines](https://proandroiddev.com/mocking-coroutines-7024073a8c09)
* [Schedule Alarms](https://developer.android.com/training/scheduling/alarms)

