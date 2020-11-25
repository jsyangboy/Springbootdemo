package com.example.demo.utils

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import javax.net.ssl.SSLContext;


object HttpUtils {
    /*
       Http协议GET请求
      */
    @Throws(Exception::class)
    fun httpGet(url: String?): String? {
        //初始化HttpClient
        val httpClient = HttpClients.createDefault()
        //创建HttpGet
        val httpGet = HttpGet(url)
        //发起请求，获取response对象
        val response = httpClient.execute(httpGet)
        //获取请求状态码
        //response.getStatusLine().getStatusCode();
        //获取返回数据实体对象
        val entity: HttpEntity = response.entity
        //转为字符串
        return EntityUtils.toString(entity, "UTF-8")
    }

    /*
       Http协议Post请求
      */
    @Throws(java.lang.Exception::class)
    fun httpPost(url: String?, json: String?): String? {
        //初始HttpClient
        val httpClient = HttpClients.createDefault()
        //创建Post对象
        val httpPost = HttpPost(url)
        //设置Content-Type
        httpPost.setHeader("Content-Type", "application/json")
        //写入JSON数据
        httpPost.entity = StringEntity(json)
        //发起请求，获取response对象
        val response = httpClient.execute(httpPost)
        //获取请求码
        //response.getStatusLine().getStatusCode();
        //获取返回数据实体对象
        val entity = response.entity
        //转为字符串
        return EntityUtils.toString(entity, "UTF-8")
    }


    @Throws(java.lang.Exception::class)
    fun httpsGet(url: String?): String? {
        val hp = createSSLClientDefault()
        val hg = HttpGet(url)
        val response = hp.execute(hg)
        val entity = response.entity
        val content = EntityUtils.toString(entity, "UTF-8")
        hp.close()
        return content
    }

    @Throws(java.lang.Exception::class)
    fun httpsPost(url: String?, json: String?): String? {
        val hp = createSSLClientDefault()
        val httpPost = HttpPost(url)
        httpPost.setHeader("Content-Type", "application/json")
        httpPost.entity = StringEntity(json)
        val response = hp.execute(httpPost)
        val entity = response.entity
        val content = EntityUtils.toString(entity, "UTF-8")
        hp.close()
        return content
    }


    @Throws(java.lang.Exception::class)
    fun createSSLClientDefault(): CloseableHttpClient {
        val sslContext: SSLContext = SSLContextBuilder().loadTrustMaterial(null) { chain, authType ->
            //信任所有
            true
        }.build()
        val sslsf = SSLConnectionSocketFactory(sslContext)
        return HttpClients.custom().setSSLSocketFactory(sslsf).build()
    }
}