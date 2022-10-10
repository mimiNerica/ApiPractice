package com.miminerica.apipractice

import android.content.Intent
import android.os.Bundle
import com.miminerica.apipractice.adapters.TopicAdapter
import com.miminerica.apipractice.datas.Topic
import com.miminerica.apipractice.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    var mTopicList = ArrayList<Topic>()

    lateinit var mTopicAdapter : TopicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEvent()
        setValue()
    }

    override fun setupEvent() {

        topicListView.setOnItemClickListener { parent, view, position, id ->

            val clickedTopic = mTopicList[position]

            val myIntent = Intent(mContext, ViewTopicDetailActivity::class.java)
            myIntent.putExtra("topic", clickedTopic)
            startActivity(myIntent)
        }

    }

    override fun setValue() {
        getTopicListFromServer()

        mTopicAdapter = TopicAdapter(mContext, R.layout.topic_list_item, mTopicList)
        topicListView.adapter = mTopicAdapter




    }

    fun getTopicListFromServer() {
        ServerUtil.getRequestMainInfo(mContext, object : ServerUtil.Companion.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {
                val topicsArr = jsonObj.getJSONObject("data").getJSONArray("topics")

                for (idx in 0 until topicsArr.length()) {
                    val topicObj = topicsArr.getJSONObject(idx)
                    val topicData = Topic.getTopicDataFromJson(topicObj)

                    mTopicList.add(topicData)
                }
                runOnUiThread {
                    mTopicAdapter.notifyDataSetChanged()
                }
            }

        })

    }

}