package cn.wenhe9.intelrobot.logic.model

/**
 *@author DuJinliang
 *2021/11/16
 */
data class MessageResponse(val intent : _Intent, val results : List<Result>){
    data class _Intent(val code : Int)
    data class Result(val resultType : String, val values : Values)
    data class Values(val text : String)
}
