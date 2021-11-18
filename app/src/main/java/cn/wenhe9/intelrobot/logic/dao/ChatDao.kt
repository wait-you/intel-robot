package cn.wenhe9.intelrobot.logic.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cn.wenhe9.intelrobot.logic.model.ChatBean

/**
 *@author DuJinliang
 *2021/11/18
 */
@Dao
interface ChatDao {
    @Query("select * from ChatBean")
    fun getHistory() : List<ChatBean>

    @Insert
    fun saveNew(chatBean : ChatBean) : Long
}