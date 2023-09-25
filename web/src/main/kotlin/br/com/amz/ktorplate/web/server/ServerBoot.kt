package br.com.amz.ktorplate.web.server

import io.ktor.server.netty.EngineMain

object ServerBoot {

    fun boot(args: Array<String>) {
        EngineMain.main(args)
    }
}
