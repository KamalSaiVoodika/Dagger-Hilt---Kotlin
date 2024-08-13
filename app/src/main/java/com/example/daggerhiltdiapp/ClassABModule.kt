package com.example.daggerhiltdiapp

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module    /* @Module This annotation is used over the class which is used to construct objects and provide the dependencies. */
@InstallIn(SingletonComponent::class)
object ClassABModule {

    @Provides  /* @Provides This is used over the method in the module class that will return the object. */
    fun providesClassA() : ClassA{
        return  ClassA()
    }

    @Provides
    fun providesClassB() : ClassB{
        return  ClassB()
    }

}