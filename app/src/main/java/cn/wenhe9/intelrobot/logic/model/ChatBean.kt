package cn.wenhe9.intelrobot.logic.model

/**
 *@author DuJinliang
 *2021/11/16
 */
data class ChatBean(val state : Int, val message : String){
    companion object {
        const val SEND = 1;
        const val RECEIVE = 2;
    }
}
