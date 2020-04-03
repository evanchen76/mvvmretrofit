package evan.chen.tutorial.mvvmretrofitsample.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import evan.chen.tutorial.mvvmretrofitsample.ServiceApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {

    const val WEB_HOST = "https://www.google.com.tw"

    const val TIME_OUT_CONNECT = 30
    const val TIME_OUT_READ = 30
    const val TIME_OUT_WRITE = 30

    const val productUrl = "https://firebasestorage.googleapis.com/v0/b/phoneauth-e70bb.appspot.com/o/product.json?alt=media&token=c051df05-399a-42af-b60f-b5430643d78e"
    const val buyUrl = "https://firebasestorage.googleapis.com/v0/b/phoneauth-e70bb.appspot.com/o/buy.json?alt=media&token=cad7488d-e1d2-49a9-b881-abdde57cb5da"
}

class NetworkService(interceptor: Interceptor) {

    var serviceAPI: ServiceApi

    init {

        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(ApiConfig.TIME_OUT_CONNECT.toLong(), TimeUnit.SECONDS)
                .readTimeout(ApiConfig.TIME_OUT_READ.toLong(), TimeUnit.SECONDS)
                .writeTimeout(ApiConfig.TIME_OUT_WRITE.toLong(), TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConfig.WEB_HOST)
                .client(client)
                .build()

        serviceAPI = retrofit.create(ServiceApi::class.java)

    }
}
