package dev.dvdciri.sampleapp.data.di

import dev.dvdciri.sampleapp.data.PostRepositoryImpl
import dev.dvdciri.sampleapp.data.UserRepositoryImpl
import dev.dvdciri.sampleapp.data.mapper.CommentDtoToCommentMapper
import dev.dvdciri.sampleapp.data.mapper.PostDtoToPostMapper
import dev.dvdciri.sampleapp.data.mapper.UserDtoToUserMapper
import dev.dvdciri.sampleapp.data.source.JsonPlaceholderClient
import dev.dvdciri.sampleapp.data.source.JsonPlaceholderNetworkDataSource
import dev.dvdciri.sampleapp.domain.repository.PostRepository
import dev.dvdciri.sampleapp.domain.repository.UserRepository
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT_IN_SEC: Int = 15
private const val JSON_PLACEHOLDER_BASE_URL = "https://jsonplaceholder.typicode.com/"

val networkModule = module {

    single {
        val cacheSize = 20 * 1024 * 1024 // 20 MiB
        Cache(androidApplication().cacheDir, cacheSize.toLong())
    }

    single<OkHttpClient> {
        with(OkHttpClient.Builder()) {
            connectTimeout(TIMEOUT_IN_SEC.toLong(), TimeUnit.SECONDS)
            readTimeout(TIMEOUT_IN_SEC.toLong(), TimeUnit.SECONDS)
            cache(get())
            build()
        }
    }

    single<JsonPlaceholderClient> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(JSON_PLACEHOLDER_BASE_URL)
            .client(get())
            .build()
            .create(JsonPlaceholderClient::class.java)
    }
}

val dataModule = module {
    factory<PostRepository> { PostRepositoryImpl(get(), get(), get()) }
    factory<UserRepository> { UserRepositoryImpl(get(), get()) }
    factory { JsonPlaceholderNetworkDataSource(get()) }
    factory { PostDtoToPostMapper() }
    factory { CommentDtoToCommentMapper() }
    factory { UserDtoToUserMapper() }
}