package evan.chen.tutorial.mvvmretrofitsample

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import evan.chen.tutorial.mvvmretrofitsample.api.ProductResponse


class ProductViewModel(private val productRepository: IProductRepository) : ViewModel(){
    var productId: MutableLiveData<String> = MutableLiveData()
    var productName: MutableLiveData<String> = MutableLiveData()
    var productDesc: MutableLiveData<String> = MutableLiveData()
    var productPrice: MutableLiveData<Int> = MutableLiveData()
    var productItems: MutableLiveData<String> = MutableLiveData()

    var alertText: MutableLiveData<Event<String>> = MutableLiveData()
    var buySuccessText: MutableLiveData<Event<String>> = MutableLiveData()

    fun getProduct(productId: String) {
        this.productId.value = productId

        productRepository.getProduct()
            .subscribeOn(SchedulerProvider.io())
            .observeOn(SchedulerProvider.mainThread())
            .subscribe({ data ->
                productName.value = data.name
                productDesc.value = data.desc
                productPrice.value = data.price
            },
                { throwable ->
                    println(throwable)
                })
    }

    fun buy(){
        val productId = productId.value ?: ""
        val numbers = (productItems.value ?: "0").toInt()

        productRepository.buy(productId, numbers)
            .subscribeOn(SchedulerProvider.io())
            .observeOn(SchedulerProvider.mainThread())
            .subscribe({ data ->
                if (data) { //購買成功
                    buySuccessText.value = Event("購買成功")
                } else {
                    //購買失敗
                    alertText.value = Event("購買失敗")
                }
            },
                { throwable ->
                    println(throwable)
                })
    }
}


