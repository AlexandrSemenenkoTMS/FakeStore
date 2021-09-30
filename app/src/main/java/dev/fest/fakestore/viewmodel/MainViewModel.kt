package dev.fest.fakestore.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.fest.fakestore.ProductApp
import dev.fest.fakestore.db.ProductDao
import dev.fest.fakestore.dto.Product
import dev.fest.fakestore.remote.FakeStoreApiClient
import dev.fest.fakestore.remote.FakeStoreApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _productListLiveData = MutableLiveData<List<Product>>()
    val productListLiveData: LiveData<List<Product>>
        get() = _productListLiveData

    private val _errorCodeLiveData = MutableLiveData<Int>()
    val errorCodeLiveData: LiveData<Int>
        get() = _errorCodeLiveData

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    private val compositeDisposable = CompositeDisposable()
    private val productDao by lazy { getApplication<ProductApp>().database.getProductDao() }
    private val productsList = mutableListOf<Product>()

    init {
        loadProducts()
    }

    fun loadProducts() {
        uiScope.launch {
            val task = ioScope.async {
                FakeStoreApiClient.fakeStoreApiService.getAllProducts().execute()
            }
            val products = task.await()
            if (products.isSuccessful) {
                _productListLiveData.postValue(products.body())
            } else {
                _errorCodeLiveData.postValue(products.code())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}

//start rxjava
/*   fun loadProducts() {
       compositeDisposable.add(
           productDao.getAllProducts()
               .flatMap {
                   if (it.isEmpty()) {
                       val products = FakeStoreApiClient.fakeStoreApiService
                           .getAllProducts()
//                            .execute()
                       productDao.insertAllProducts(products)
                       Observable.just(it)
                   } else {
                       Observable.just(it)
                   }
               }
               .subscribe({
                   productsList.clear()
                   productsList.addAll(it)
                   _productListLiveData.value = productsList
               }, { Log.d("MainViewModel", "${it.message}") })
       )
   }


   override fun onCleared() {
       super.onCleared()
       compositeDisposable.dispose()
   }
}
 */

//end rxjava
