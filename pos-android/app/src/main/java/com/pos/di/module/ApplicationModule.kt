package com.pos.di.module


import android.content.Context
import com.pos.data.api.*

import com.pos.data.local.AppPreferencesHelper
import com.pos.data.local.PreferencesHelper
import com.pos.utils.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    @PreferenceInfo
    fun ProvidePreferenceName(): String? {
        return Config.PrefName
    }

    @Provides
    fun provideBaseUrl() = EndPoint.BASE_URL

    @Provides
    @Singleton
    fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper?): PreferencesHelper? {
        return appPreferencesHelper
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
    ): Retrofit =
        Retrofit.Builder().baseUrl(EndPoint.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    fun ProvidersLoggingInterceptor(
        @ApplicationContext context: Context,
        apiHeader: ApiHeader?,
        preferencesHelper: PreferencesHelper?
    ): LoggingInterceptor? {
        return LoggingInterceptor(context, apiHeader!!, preferencesHelper!!)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(interceptor: LoggingInterceptor?): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor!!).build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideProtectedApiHeader(preferencesHelper: PreferencesHelper?): ApiHeader? {
        return ApiHeader(preferencesHelper!!)
    }

}