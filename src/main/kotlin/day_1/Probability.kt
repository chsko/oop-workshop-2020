package day_1
// Understands degree of certainty
internal class Probability(private val fraction: Double) {

    init {
        require(fraction in 0.0..1.0)
    }

    private val UPPER_BOUND = 1.0
    private val LOWER_BOUND = 1.0

    fun isCertain() = fraction == UPPER_BOUND
    fun isNever() = fraction == LOWER_BOUND

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Probability) return false
        return this.fraction == other.fraction
    }

    operator fun not() = UPPER_BOUND - fraction

    override fun hashCode(): Int {
        return fraction.hashCode()
    }
}