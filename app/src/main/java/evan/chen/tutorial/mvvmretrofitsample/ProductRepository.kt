package evan.chen.tutorial.mvvmretrofitsample

import evan.chen.tutorial.mvvmretrofitsample.api.BuyRequest
import evan.chen.tutorial.mvvmretrofitsample.api.BuyResponse
import evan.chen.tutorial.mvvmretrofitsample.api.ProductResponse
import io.reactivex.Single

interface IProductRepository {
    fun getProduct(): Single<ProductResponse>

    fun buy(id: String, items: Int): Single<Boolean>
}

class ProductRepository(private val serviceApi: ServiceApi) : IProductRepository {

    override fun getProduct(): Single<ProductResponse> {

        return serviceApi.getProduct()
            .map {
                it.body()
            }
    }

    override fun buy(id: String, items: Int): Single<Boolean> {

        val buyRequest = BuyRequest()
        buyRequest.id = id
        buyRequest.number = items

        return serviceApi.buy()
            .map {
                it.body()
            }.map(BuyResponse::result)
    }

    companion object {

        private var INSTANCE: ProductRepository? = null

        @JvmStatic fun getInstance(serviceApi: ServiceApi) =
            INSTANCE ?: synchronized(ProductRepository::class.java) {
                INSTANCE ?: ProductRepository(serviceApi)
                    .also { INSTANCE = it }
            }

        @JvmStatic fun destroyInstance() {
            INSTANCE = null
        }
    }

}

