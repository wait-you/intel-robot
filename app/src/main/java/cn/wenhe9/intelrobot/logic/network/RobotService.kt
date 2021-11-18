package cn.wenhe9.intelrobot.logic.network

import cn.wenhe9.intelrobot.logic.model.ChatBean
import cn.wenhe9.intelrobot.logic.model.MessageResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 *@author DuJinliang
 *2021/11/16
 */
interface RobotService {
    @POST("/openapi/api/v2")
    @Headers("Content-Type: application/json")
    fun sendMessage(@Body body: RequestBody) : Call<MessageResponse>
}