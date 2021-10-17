package com.darklabs.route

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import java.io.File

fun Routing.fileRoute() {

    get("/open") {

        val file = File("file/lion.jpeg")

        call.response.header(
            HttpHeaders.ContentDisposition,
            ContentDisposition.Inline.withParameter(ContentDisposition.Parameters.FileName, file.name).toString()
        )

        call.respondFile(file)
    }

    get("/download") {

        val file = File("file/lion.jpeg")

        call.response.header(
            HttpHeaders.ContentDisposition,
            ContentDisposition.File.withParameter(ContentDisposition.Parameters.FileName, file.name).toString()
        )

        call.respondFile(file)
    }

}