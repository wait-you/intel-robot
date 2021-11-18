package cn.wenhe9.intelrobot.logic.dao

import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import cn.wenhe9.intelrobot.IntelRobotApplication
import cn.wenhe9.intelrobot.logic.model.ChatBean

/**
 *@author DuJinliang
 *2021/11/17
 */
object ChatDao {

    private val dbHelper by lazy {
        ChatDatabase(IntelRobotApplication.context, "robot_chat.db", 1)
    }

    fun getHistory() : List<ChatBean>{
        val db = dbHelper.readableDatabase

        val cursor = db.query("chat_message", null, null, null, null, null, null)

        val dataList = mutableListOf<ChatBean>()
        if (cursor.moveToFirst()){
            do {
                val state = cursor.getInt(cursor.getColumnIndex("state"))
                val message = cursor.getString(cursor.getColumnIndex("message"))

                dataList.add(ChatBean(state, message))
            }while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return dataList
    }

    fun saveNew(dataList : List<ChatBean>){
        val db = dbHelper.writableDatabase

        for (chatBean in dataList) {
            val values = contentValuesOf("state" to chatBean.state, "message" to chatBean.message)

            db.insert("chat_message", null, values)
        }

        db.close()
    }
}