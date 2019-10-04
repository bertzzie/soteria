package tech.namas.benchmark.frontend.models

data class ChromeOptions(val chromeFlags: Array<String>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class.js != other::class.js) return false

        other as ChromeOptions

        if (!chromeFlags.contentEquals(other.chromeFlags)) return false

        return true
    }

    override fun hashCode(): Int {
        return chromeFlags.contentHashCode()
    }
}
