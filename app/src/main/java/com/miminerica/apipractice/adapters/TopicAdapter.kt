package com.miminerica.apipractice.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.miminerica.apipractice.R
import com.miminerica.apipractice.datas.Topic

class TopicAdapter(val mContext: Context, val resId: Int, val mList: List<Topic>) : ArrayAdapter<Topic>(mContext, resId, mList) {

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var  tempRow = convertView
        if (tempRow == null) {
            tempRow = mInflater.inflate(resId, null)
        }

        val row = tempRow!!
        val data =  mList[position]
        val topicImg = row.findViewById<ImageView>(R.id.topicImg)
        val topicTitleTxt = row.findViewById<TextView>(R.id.topicTitleTxt)

        Glide.with(mContext).load(data.imageUrl).into(topicImg)
        topicTitleTxt.text = data.title

        return row
    }
}