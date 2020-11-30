package probability

import comparable.Comparable
import kotlin.math.absoluteValue
import kotlin.math.roundToLong

// Understands degree of certainty
internal class Probability private constructor(private val likelihood: Double, min: Double, max: Double): Comparable<Probability> {

    private val LOWER_BOUND = min
    private val UPPER_BOUND = max
    private val ERROR_MARGIN = 0.000000001

    init {
        require(likelihood in LOWER_BOUND..UPPER_BOUND)
    }

    companion object {
        fun p(likelihood: Number, min: Number = 0.0, max: Number = 1.0) =
            Probability(likelihood.toDouble(), min.toDouble(), max.toDouble())
    }

    fun isCertain() = likelihood == UPPER_BOUND
    fun isNever() = likelihood == LOWER_BOUND

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Probability) return false
        return (this.likelihood - other.likelihood).absoluteValue < ERROR_MARGIN
    }

    operator fun not() = Probability((UPPER_BOUND.toBigDecimal() - likelihood.toBigDecimal()).toDouble(), LOWER_BOUND, UPPER_BOUND)

    infix fun and(other: Probability) = Probability((this.likelihood * other.likelihood) / UPPER_BOUND, LOWER_BOUND, UPPER_BOUND)

    infix fun or(other: Probability) = !(!other and !this)

    override fun hashCode() = (likelihood / ERROR_MARGIN).roundToLong().hashCode()

    override fun toString(): String = "$likelihood"
    override fun betterThan(other: Probability) = this.likelihood < other.likelihood
}