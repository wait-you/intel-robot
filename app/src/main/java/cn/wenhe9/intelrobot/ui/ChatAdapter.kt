package cn.wenhe9.intelrobot.ui

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import cn.wenhe9.intelrobot.R
import cn.wenhe9.intelrobot.logic.model.ChatBean

/**
 *@author DuJinliang
 *2021/11/16
 */
class ChatAdapter(val dataList : List<ChatBean>) : RecyclerView.Adapter<ChatAdapter.ViewHolder>(){
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val leftItem =  view.findViewById<RelativeLayout>(R.id.left)
        val rightItem =  view.findViewById<RelativeLayout>(R.id.right)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chatting_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chatBean = dataList[position]

        if (chatBean.state == ChatBean.RECEIVE){
            holder.leftItem.visibility = View.VISIBLE
            holder.rightItem.visibility = View.GONE

            val content = holder.leftItem.findViewById<TextView>(R.id.tv_chat_content)
            content.text = chatBean.message
        }else{
            holder.leftItem.visibility = View.GONE
            holder.rightItem.visibility = View.VISIBLE

            val content = holder.rightItem.findViewById<TextView>(R.id.tv_chat_content)
            content.text = chatBean.message
        }

    }

    override fun getItemCount() = dataList.size
}