package com.example.daggerhiltdiapp

import android.util.Log
import javax.inject.Inject

// 1 :: Without Dagger
/*
class ClassB {

    fun startClassB(){
        Log.i("TAG 2898", "Class B is Created")
    }
}
*/

// 2 :: With Dagger
class ClassB @Inject constructor() {  /* @Inject This is used over the fields, constructor, or method and indicate that dependencies are requested. */

    fun startClassB(){
        Log.i("TAG 2898", "Class B is Created")
    }
}