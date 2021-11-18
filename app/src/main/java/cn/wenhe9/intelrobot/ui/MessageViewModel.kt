package cn.wenhe9.intelrobot.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import cn.wenhe9.intelrobot.logic.Repository
import cn.wenhe9.intelrobot.logic.model.ChatBean
import okhttp3.RequestBody

/**
 *@author DuJinliang
 *2021/11/16
 */
class MessageViewModel : ViewModel() {

    private var flagLiveData = true

    var exitTime = 0L

    private val bodyLiveData = MutableLiveData<RequestBody>()

    val dataList = mutableListOf<ChatBean>()

    val valueLiveData = Transformations.switchMap(bodyLiveData){ body ->
        Repository.sendMessage(body)
    }

    fun sendMessage(body : RequestBody){
        bodyLiveData.value = body
    }

    fun refresh(msg : ChatBean){
        dataList.add(msg)
    }

    fun loadHistory(){
        flagLiveData = false
        dataList.addAll(Repository.loadHistory())
    }

    fun isLoad() : Boolean{
        return flagLiveData
    }
}