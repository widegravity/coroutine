package com.widegravity.coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity

class CoroutineApplicationTests {

	@Test
	suspend fun contextLoads() {

		val start: Long = System.currentTimeMillis()

		coroutineScope {
			val res1 = async { getRes1() }


			val res2 = async { getRes2() }

			val await1: String? = res1.await()
			val await2: String? = res2.await()

			println(await1)
			println(await2)

			ResponseEntity.ok(await1 + await2)
		}

		println(System.currentTimeMillis() - start)

	}

	private suspend fun getRes1(): String? {

		Thread.sleep(3000)

		return "test1"

//        return   webClient.get()
//            .uri("https://www.baeldung.com/")
//            .retrieve()
//            .awaitBodyOrNull()

	}

	private suspend fun getRes2(): String? {

		Thread.sleep(3000)

		return "test2"

		/*return webClient.get()
            .uri("https://www.baeldung.com/")
            .retrieve()
            .awaitBodyOrNull()*/

	}

}
