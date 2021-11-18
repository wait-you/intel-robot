package cn.wenhe9.intelrobot.logic.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cn.wenhe9.intelrobot.logic.model.ChatBean

/**
 *@author DuJinliang
 *2021/11/17
 */
@Database(version = 1, entities = [ChatBean::class])
abstract class ChatDatabase : RoomDatabase(){
    abstract fun chatDao() : ChatDao

    companion object {
        private var instance : ChatDatabase? = null

        @Synchronized
        fun getDatabase(context: Context) : ChatDatabase {
            instance?.let {
                return it
            }

            return Room.databaseBuilder(context.applicationContext, ChatDatabase::class.java, "robot_chat")
                .build().apply {
                    instance = this
                }
        }
    }
}