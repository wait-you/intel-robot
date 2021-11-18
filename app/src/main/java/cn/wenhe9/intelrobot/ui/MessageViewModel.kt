package cn.wenhe9.intelrobot.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import cn.wenhe9.intelrobot.logic.Repository
import okhttp3.RequestBody

/**
 *@author DuJinliang
 *2021/11/16
 */
class MessageViewModel : ViewModel() {
    private val bodyLiveData = MutableLiveData<RequestBody>()

    val valueLiveData = Transformations.switchMap(bodyLiveData){ body ->
        Repository.sendMessage(body)
    }

    fun refresh(body : RequestBody){
        bodyLiveData.value = body
    }
}