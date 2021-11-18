package cn.wenhe9.intelrobot.utils

import cn.wenhe9.intelrobot.IntelRobotApplication
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 *@author DuJinliang
 *2021/11/17
 */
object RequestParseUtil {
    fun parseRequestBody(msg : String) : RequestBody{
        val message = "{\n" +
                "\t\"reqType\":0,\n" +
                "    \"perception\": {\n" +
                "        \"inputText\": {\n" +
                "            \"text\": \"$msg\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"userInfo\": {\n" +
                "        \"apiKey\": \"${IntelRobotApplication.API_KEY}\",\n" +
                "        \"userId\": \"${IntelRobotApplication.USER_ID}\"\n" +
                "    }\n" +
                "}"

        return RequestBody.create(MediaType.parse("application/json"), message)
    }
}