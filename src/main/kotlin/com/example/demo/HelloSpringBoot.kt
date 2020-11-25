package com.example.demo

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/api")
class HelloSpringBoot {

    @RequestMapping("/hello", method = [RequestMethod.GET])
    fun helloGet(): String {
        return "helloWorld"
    }

    @PostMapping("/get-info")
    fun helloPost(request: HttpServletRequest) {
        try {
            val map = request.contextPath
            println("yqy=$map")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}