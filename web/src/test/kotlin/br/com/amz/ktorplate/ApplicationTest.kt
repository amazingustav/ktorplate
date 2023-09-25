package br.com.amz.ktorplate

import br.com.amz.ktorplate.web.server.configureRouting
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }

        client.get("/health").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("\"status\": \"OK\"", bodyAsText())
        }
    }
}
