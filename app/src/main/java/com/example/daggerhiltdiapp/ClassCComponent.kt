package com.example.daggerhiltdiapp

import dagger.Component

@Component(modules = [ClassABModule::class])     /* @Component This is used over a component interface which acts as a bridge between @Module and @Inject
                                                    (Module class doesn't provide dependency directly to requesting class, it uses component interface) */
interface ClassCComponent {
    fun getClassCInstance () : ClassC
}