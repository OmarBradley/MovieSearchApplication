package omarbradley.com.domain.repository.remote.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import omarbradley.com.domain.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_END_POINT = "https://openapi.naver.com/v1/"

private val retrofit: Retrofit = createNetworkClient(API_END_POINT, BuildConfig.DEBUG)

val movieApi = retrofit.create(MovieApi::class.java)

private fun createNetworkClient(baseUrl: String, debug: Boolean = false) =
    retrofitClient(baseUrl, httpClient(debug))

private fun httpClient(debug: Boolean): OkHttpClient = OkHttpClient.Builder()
    .apply {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        if (debug) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(httpLoggingInterceptor)
        }
    }
    .addInterceptor(createAuthInterceptor())
    .build()

private fun createAuthInterceptor() = Interceptor { chain ->
    val request = chain.request()
        .newBuilder()
        .addHeader("X-Naver-Client-Id", "Ul7rtt7e8q1n_BAi0Rba")
        .addHeader("X-Naver-Client-Secret", "LLFAazWdf4")
        .build()
    chain.proceed(request)
}

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient) =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
