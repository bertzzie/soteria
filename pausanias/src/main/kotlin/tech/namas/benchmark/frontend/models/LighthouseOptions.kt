package tech.namas.benchmark.frontend.models

data class LightHouseOptions(val onlyCategories: Array<String> = arrayOf("performance"),
                             val logLevel: String = "info",
                             val port: Int = 9277) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class.js != other::class.js) return false

        other as LightHouseOptions

        if (!onlyCategories.contentEquals(other.onlyCategories)) return false
        if (logLevel != other.logLevel) return false
        if (port != other.port) return false

        return true
    }

    override fun hashCode(): Int {
        var result = onlyCategories.contentHashCode()
        result = 31 * result + logLevel.hashCode()
        result = 31 * result + port.hashCode()
        return result
    }

}
