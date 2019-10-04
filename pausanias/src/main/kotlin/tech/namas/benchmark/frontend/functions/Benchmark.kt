package tech.namas.benchmark.frontend.functions

import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import tech.namas.benchmark.frontend.chromeLauncher
import tech.namas.benchmark.frontend.lighthouse
import tech.namas.benchmark.frontend.lighthouseLogger
import tech.namas.benchmark.frontend.models.ChromeOptions
import tech.namas.benchmark.frontend.models.LightHouseOptions
import tech.namas.benchmark.frontend.models.LighthouseResult
import kotlin.js.Promise

@UnstableDefault
fun benchmarks(urls: Array<String>): Promise<Array<out LighthouseResult>> {
    val firstResult = benchmark(urls.first())
        .then { result -> arrayOf(result) }

    if (urls.size <= 1) {
        return firstResult
    }

    return urls.drop(1).fold(firstResult) { promise, url ->
        Promise { resolve, reject ->
            promise.then { results ->
                benchmark(url).then { newResult -> results + newResult }
                    .then(resolve, reject)
            }
        }
    }
}

@UnstableDefault
fun benchmark(url: String): Promise<LighthouseResult> {
    val chromeOptions = ChromeOptions(chromeFlags = arrayOf("--headless"))

    return chromeLauncher.launch(chromeOptions)
        .then { chrome ->
            val lhOptions = LightHouseOptions(
                port = chrome.port as Int
            )

            lighthouseLogger.setLevel(lhOptions.logLevel)
            lighthouse(url, lhOptions).then { results ->
                chrome.kill().then { _ -> results.report }
            }
            .then { result ->
                val json = Json.nonstrict
                json.parse(LighthouseResult.serializer(), result as String)
            }
        } as Promise<LighthouseResult>
}
