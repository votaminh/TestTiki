package com.msc.tikitest

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.msc.tikitest.remote.banner.BannerService
import com.msc.tikitest.remote.link.LinkService
import com.msc.tikitest.repository.banner.BannerRepository
import com.msc.tikitest.repository.banner.BannerRepositoryImp
import com.msc.tikitest.repository.link.LinkRepository
import com.msc.tikitest.repository.link.LinkRepositoryImp
import com.msc.tikitest.view.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModules = module {
    single {
        createWebService<BannerService>(okHttpClient = createHttpClient(), baseUrl = "https://api.tiki.vn/v2/")
    }
    single {
        createWebService<LinkService>(okHttpClient = createHttpClient(), baseUrl = "https://api.tiki.vn/shopping/v2/")
    }

    factory<BannerRepository> { BannerRepositoryImp(bannerService = get())}
    factory<LinkRepository> { LinkRepositoryImp(linkService = get())}

    viewModel { MainViewModel(bannerRepository = get(), linkRepository = get())}
}


fun createHttpClient(
): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    val client = OkHttpClient.Builder()
    client.addInterceptor(httpLoggingInterceptor)
    client.readTimeout(5 * 60, TimeUnit.SECONDS)

    return client.addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        val request = requestBuilder.method(original.method(), original.body()).build()
        return@addInterceptor it.proceed(request)
    }.build()
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    factory: CallAdapter.Factory = RxJava2CallAdapterFactory.create(),
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            )
        )
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(factory)
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}
