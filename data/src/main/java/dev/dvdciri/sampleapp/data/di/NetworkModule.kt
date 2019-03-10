package dev.dvdciri.sampleapp.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.dvdciri.sampleapp.data.source.JsonPlaceholderClient
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        private const val TIMEOUT_IN_SEC: Int = 15
        private const val JSON_PLACEHOLDER_BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @Singleton
    @Provides
    fun provideJsonPlaceholderClient(okHttpClient: OkHttpClient): JsonPlaceholderClient {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(JSON_PLACEHOLDER_BASE_URL)
            .client(okHttpClient)
            .build()
            .create(JsonPlaceholderClient::class.java)
    }

    @Singleton
    @Provides
    internal fun provideOkHttpClient(cache: Cache): OkHttpClient {
        return with(OkHttpClient.Builder()) {
            connectTimeout(TIMEOUT_IN_SEC.toLong(), TimeUnit.SECONDS)
            readTimeout(TIMEOUT_IN_SEC.toLong(), TimeUnit.SECONDS)
            cache(cache)
            build()
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpCache(application: Context): Cache {
        val cacheSize = 20 * 1024 * 1024 // 20 MiB
        return Cache(application.cacheDir, cacheSize.toLong())
    }
}
