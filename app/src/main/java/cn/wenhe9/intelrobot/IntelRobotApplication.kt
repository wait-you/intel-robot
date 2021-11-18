package cn.wenhe9.intelrobot

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 *@author DuJinliang
 *2021/11/16
 */
class IntelRobotApplication : Application(){
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context : Context
        //图灵机器人的API_KEY
        val API_KEY = ""
        //图灵机器人的账号
        val USER_ID = ""
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}