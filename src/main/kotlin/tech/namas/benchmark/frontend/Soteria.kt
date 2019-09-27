package tech.namas.benchmark.frontend

import tech.namas.benchmark.frontend.functions.benchmark
import tech.namas.benchmark.frontend.functions.benchmarks

external fun require(module: String): dynamic

val lighthouse = require("lighthouse")
val lighthouseLogger = require("lighthouse-logger")
val chromeLauncher = require("chrome-launcher")

fun main(args: Array<String>) {
    val express = require("express")
    val app = express()

    app.get("/") { _, res ->
        benchmarks(arrayOf("https://blibli.com", "https://tokopedia.com")).then { result ->
            res.type("application/json")
            res.send(result)
        }
    }

    app.listen(3000) {
        println("Listening to port 3000")
    }
}
