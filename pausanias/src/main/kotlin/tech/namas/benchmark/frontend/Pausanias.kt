package tech.namas.benchmark.frontend

import kotlinx.serialization.UnstableDefault
import tech.namas.benchmark.frontend.functions.benchmark

external fun require(module: String): dynamic

val lighthouse = require("lighthouse")
val lighthouseLogger = require("lighthouse-logger")
val chromeLauncher = require("chrome-launcher")

@UnstableDefault
fun main(args: Array<String>) {
    val express = require("express")
    val app = express()

    app.get("/benchmark") { req, res ->
        val site = req.query.site as String
        benchmark(site).then { result ->
            res.type("application/json")
            res.send(result)
        }
    }

    app.listen(3000) {
        println("Listening to port 3000")
    }
}
