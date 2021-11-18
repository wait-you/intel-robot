package cn.wenhe9.intelrobot.logic

import androidx.lifecycle.liveData
import cn.wenhe9.intelrobot.logic.network.RobotNetwork
import kotlinx.coroutines.Dispatchers
import okhttp3.RequestBody
import kotlin.coroutines.CoroutineContext

/**
 *@author DuJinliang
 *2021/11/16
 */
object Repository {

    fun sendMessage(body : RequestBody) = fire(Dispatchers.IO){
        val response = RobotNetwork.sendMessage(body)

        if (response.intent.code == 10004){
            Result.success(response.results)
        }else{
            Result.failure(RuntimeException("response status is ${response.intent.code}"))
        }
    }

    private fun <T> fire(context : CoroutineContext, block : suspend () -> Result<T>) = liveData<Result<T>>(context) {
        val result = try {
            block()
        }catch (e : Exception){
            Result.failure<T>(e)
        }

        emit(result)
    }
}