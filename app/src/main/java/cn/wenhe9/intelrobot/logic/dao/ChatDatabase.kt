package cn.wenhe9.intelrobot.logic.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 *@author DuJinliang
 *2021/11/17
 */
class ChatDatabase(context : Context, name : String, version : Int) : SQLiteOpenHelper(context, name, null, version) {

    private val createChatMessage = "\tcreate table chat_message(\n" +
            "\t\tid integer primary key autoincrement,\n" +
            "\t\tstate integer,\n" +
            "\t\tmessage varchar(100)\n" +
            "\t);"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createChatMessage)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion <= 1){
            db?.execSQL(createChatMessage)
        }
    }
}