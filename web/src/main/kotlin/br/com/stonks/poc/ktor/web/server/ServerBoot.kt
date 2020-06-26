package br.com.stonks.poc.ktor.web.server

import io.ktor.server.netty.EngineMain

object ServerBoot {

    fun boot(args: Array<String>){
        EngineMain.main(args)
    }
}