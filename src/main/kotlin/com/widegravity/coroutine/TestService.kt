package com.widegravity.coroutine


import kotlinx.coroutines.*
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitBodyOrNull
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class TestService {

    val webClient: WebClient = WebClient.create()
    val url: String = "http://localhost:8080/wait"

    suspend fun test(): TestDto {
        return coroutineScope {

            println("main")
            println(Thread.currentThread())

            val post = async {

                //return@async "test1"

                webClient.get()
                    .uri(url)
                    .retrieve()
                    . let {
                        println(Thread.currentThread())
                        it
                    }
                    .bodyToMono<String>()
                    .awaitSingle()
            }


            val count = async {

                webClient.get()
                    .uri(url)
                    .retrieve()
                    . let {
                        println(Thread.currentThread())
                        it
                    }
                    .awaitBody<String>()
            }



            TestDto(post.await(), count.await())
        }

    }

}