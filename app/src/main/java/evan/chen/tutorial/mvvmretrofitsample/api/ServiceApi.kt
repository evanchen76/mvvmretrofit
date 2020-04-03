package evan.chen.tutorial.mvvmretrofitsample

import evan.chen.tutorial.mvvmretrofitsample.api.ApiConfig
import evan.chen.tutorial.mvvmretrofitsample.api.BuyResponse
import evan.chen.tutorial.mvvmretrofitsample.api.ProductResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {
    @GET(ApiConfig.productUrl)
    fun getProduct(): Single<Response<ProductResponse>>

    @GET(ApiConfig.buyUrl)
    fun buy(): Single<Response<BuyResponse>>
}
