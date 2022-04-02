package io.king.postapi

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivityViewModel {

    lateinit var creatNewUserLiveData: MutableLiveData<UserResponse?>
    init {
        creatNewUserLiveData = MutableLiveData()
    }

    fun getCreateNewObserver(): MutableLiveData<UserResponse?>{
        return creatNewUserLiveData
    }

    fun createNewUser(user: User) {
        val retroService = RetrofitInstance.getRetrofitInstance().create(RetroServiceInterface::class.java)
        val call = retroService.createUser(user)
        call.enqueue(object: Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                creatNewUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful){
                    creatNewUserLiveData.postValue(response.body())
                }else{
                    creatNewUserLiveData.postValue(null)
                }
            }
        })
    }
}