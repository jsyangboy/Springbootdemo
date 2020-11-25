package com.example.demo

import com.example.demo.utils.HttpUtils
import net.minidev.json.JSONObject
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.IOException
import java.util.*

@SpringBootTest
class DemoApplicationTests {

    @Test
    fun contextLoads() {
    }

    @Test
    fun testPostData() {
        // int
        val pInt = 0
        // String
        val pString = "String"
        // String []
        val pStrings = arrayOf("String [0]", "String [1]")
        // List
        val pLists: List<String> = listOf("list[0]", "list[1]")
        // 。。
        val params: MutableMap<String, Any> = HashMap()
        params["p-int"] = pInt
        params["p-string"] = pString
        params["p-strings"] = pStrings
        params["p-list"] = pLists
        val url = "http://localhost:8080/api/get-info"
        try {
            val rs: String? = HttpUtils.httpPost(url, JSONObject.toJSONString(params))
            println(rs)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
