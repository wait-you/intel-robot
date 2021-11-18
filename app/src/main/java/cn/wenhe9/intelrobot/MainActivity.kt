package cn.wenhe9.intelrobot

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.KeyEvent
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cn.wenhe9.intelrobot.databinding.ActivityMainBinding
import cn.wenhe9.intelrobot.logic.Repository
import cn.wenhe9.intelrobot.logic.dao.ChatDao
import cn.wenhe9.intelrobot.logic.model.ChatBean
import cn.wenhe9.intelrobot.logic.model.MessageResponse
import cn.wenhe9.intelrobot.logic.network.RobotService
import cn.wenhe9.intelrobot.logic.network.ServiceCreator
import cn.wenhe9.intelrobot.ui.ChatAdapter
import cn.wenhe9.intelrobot.ui.MessageViewModel
import cn.wenhe9.intelrobot.utils.RequestParseUtil
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val chatList = mutableListOf<ChatBean>()
    private var history = mutableListOf<ChatBean>()
    private val newList = mutableListOf<ChatBean>()
    private lateinit var adapter : ChatAdapter

    private var exitTime = 0L

    private val welcome by lazy {
        resources.getStringArray(R.array.welcome)
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(MessageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //从数据库获取历史记录
        history = ChatDao.getHistory() as MutableList<ChatBean>
        chatList.addAll(history)

        //随机获取欢迎语
        val index = Random().nextInt(welcome.size)
        chatList.add(ChatBean(ChatBean.RECEIVE, welcome[index]))

        //展示历史记录并跳转到最后
        adapter = ChatAdapter(chatList)
        val layoutManager = LinearLayoutManager(this)
        val recyclerView = binding.list
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.scrollToPosition(chatList.size - 1)

        //观察数据，当变化时，更新ui
        viewModel.valueLiveData.observe(this){ message ->
            val msg = message.getOrNull()

            if (msg != null){
                setChatItem(ChatBean(ChatBean.RECEIVE, msg[0].values.text))
            }else{
                Log.i("MainActivity", "没有收到消息")
            }
        }

        binding.btnSend.setOnClickListener {

            val editText = binding.etSendMsg
            val msg = editText.text.toString()

            //判断输入框的内容是否为空，因为isBlank方法内部会判断长度和空格所以只需要这一个判断即可
            if(msg.isBlank()){
                Toast.makeText(this, "您还未输入任何信息哦", Toast.LENGTH_SHORT).show()
            }else{
                setChatItem(ChatBean(ChatBean.SEND, msg))
                editText.setText("")

                val body = RequestParseUtil.parseRequestBody(msg)

                viewModel.refresh(body)
            }

        }
    }

    @Synchronized
    private fun setChatItem(msg : ChatBean){
        chatList.add(msg)
        newList.add(msg)
        adapter.notifyItemInserted(chatList.size -  1)
        binding.list.scrollToPosition(chatList.size - 1)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis() - exitTime > 2000){
                Toast.makeText(this, "再按一次退出智能聊天程序", Toast.LENGTH_SHORT).show()
                exitTime = System.currentTimeMillis()
            }else{
                //保存消息到历史
                ChatDao.saveNew(newList)
                finish()
                exitProcess(0)
            }
            return true
        }

        return super.onKeyDown(keyCode, event)
    }
}