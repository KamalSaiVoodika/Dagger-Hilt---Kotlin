package com.example.daggerhiltdiapp

import android.util.Log
import javax.inject.Inject

/*
class ClassC {        // 1 :: Without DI
    private val classA = ClassA()
    private val classB = ClassB()

    fun startClassC(){
        classA.startClassA()
        classB.startClassB()
        Log.i("TAG 2898", "Class C is Created")
    }
}*/


/*class ClassC {        // 2:: Data Injection
    lateinit var  classA : ClassA
   lateinit var classB : ClassB

    fun startClassC(){
        classA.startClassA()
        classB.startClassB()
        Log.i("TAG 2898", "Class C is Created")
    }
}*/

// 3:: Constructor Injection
/*class ClassC (private val classA: ClassA, private val classB: ClassB){
    fun startClassC(){
        classA.startClassA()
        classB.startClassB()
        Log.i("TAG 2898", "Class C is Created")
    }
}*/


// 4:: Dagger
class ClassC @Inject constructor ( val classA: ClassA,  val classB: ClassB){  /* @Inject This is used over the fields, constructor, or method and indicate that dependencies are requested. */
    fun startClassC(){
        classA.startClassA()
        classB.startClassB()
        Log.i("TAG 2898", "Class C is Created")
    }
}
