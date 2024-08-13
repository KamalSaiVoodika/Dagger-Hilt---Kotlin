package com.example.daggerhiltdiapp

import android.util.Log
import javax.inject.Inject

// 1 :: Without Dagger
/*
class ClassA {

    fun startClassA(){
        Log.i("TAG 2898", "Class A is Created")
    }
}
*/

// 2 :: With Dagger
class ClassA  @Inject constructor() {  /* @Inject This is used over the fields, constructor, or method and indicate that dependencies are requested. */

    fun startClassA(){
        Log.i("TAG 2898", "Class A is Created")
    }
}