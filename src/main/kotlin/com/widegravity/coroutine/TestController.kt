package com.widegravity.coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.*
import java.sql.Time
import java.time.Clock

@RestController
class TestController(
    val testService: TestService
) {

    @GetMapping("/wait")
    fun wait() : String {

        Thread.sleep(1000);

        return "wait" + Thread.currentThread()
    }

    @GetMapping("/test")
    suspend fun test(): ResponseEntity<Any> {

        val start: Long = System.currentTimeMillis()

        println("con start")
        println(Thread.currentThread())

        val test: Any = testService.test()

        println("con end")
        println(Thread.currentThread())

        println(System.currentTimeMillis() - start)

        return ResponseEntity.ok(test)

    }


}