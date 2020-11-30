package cooking

import kotlin.math.absoluteValue

// Understands the size of something
open class IntervalQuantity(amount: Number, protected val unit: Unit) {
    protected val amount: Double = amount.toDouble()

    internal companion object {
        internal const val ERROR_MARGIN = 0.0000001
    }

    protected fun convert(other: IntervalQuantity) = this.unit.convert(other.amount, other.unit)

    private fun equals(other: IntervalQuantity) = this isCompatible (other) && (this.amount - convert(other)).absoluteValue < ERROR_MARGIN

    override fun equals(other: Any?) = this === other || (other is IntervalQuantity && this.equals(other))

    override fun hashCode() = unit.hashCode(amount)

    override fun toString() = "amount=$amount"

    private infix fun isCompatible(other: IntervalQuantity): Boolean = this.unit.isCompatible(other.unit)
}