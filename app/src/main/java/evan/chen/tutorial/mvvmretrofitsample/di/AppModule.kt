package evan.chen.tutorial.mvvmretrofitsample

import evan.chen.tutorial.mvvmretrofitsample.api.BaseInterceptor
import evan.chen.tutorial.mvvmretrofitsample.api.NetworkService
import evan.chen.tutorial.mvvmretrofitsample.api.ProductAPI
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        val networkServiceApi = NetworkService(BaseInterceptor())
        val productRepository = ProductRepository(networkServiceApi.serviceAPI)

        ProductViewModel(productRepository)
    }
}
