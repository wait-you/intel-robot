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
        const val API_KEY = "8b6ec61f2c6445059fb1917f30182d2e"
        //图灵机器人的账号
        const val USER_ID = "743052"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}