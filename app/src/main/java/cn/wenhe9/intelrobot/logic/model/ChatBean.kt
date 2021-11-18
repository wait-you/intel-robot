package cn.wenhe9.intelrobot.logic.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *@author DuJinliang
 *2021/11/16
 */
@Entity
data class ChatBean(val state : Int, val message : String){

    @PrimaryKey(autoGenerate = true)
    var id : Long = 0;

    companion object {
        const val SEND = 1;
        const val RECEIVE = 2;
    }
}
