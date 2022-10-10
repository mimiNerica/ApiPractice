package com.miminerica.apipractice.datas

import org.json.JSONObject

class Topic : java.io.Serializable {
    var id = 0
    var title = ""
    var imageUrl = ""

    companion object {

        fun getTopicDataFromJson(jsonObj : JSONObject) : Topic {
            val resultTopic = Topic()

            resultTopic.id = jsonObj.getInt("id")
            resultTopic.title = jsonObj.getString("title")
            resultTopic.imageUrl = jsonObj.getString("img_url")

            return resultTopic
        }
    }
}