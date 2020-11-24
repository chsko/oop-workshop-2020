package cooking

import kotlin.math.absoluteValue
import kotlin.math.roundToLong

class Volume(private val amount: Double, private val metric: Metric) {

    private companion object {
        private const val ERROR_MARGIN = 0.01
    }

    class Metric private constructor(private val baseUnitRatio: Double) {
        companion object {
            val teaspoon = Metric(1.0)
            val tablespoon = Metric(teaspoon.baseUnitRatio * 3.0)
        }

        fun convert(amount: Double) = this.baseUnitRatio * amount
    }

    private fun amountInBaseUnits() = metric.convert(amount)

    private fun equals(other: Volume) = (this.amountInBaseUnits() - other.amountInBaseUnits()).absoluteValue < ERROR_MARGIN

    override fun equals(other: Any?) = this === other || (other is Volume && this.equals(other))

    override fun hashCode() = 31 * (amountInBaseUnits() / ERROR_MARGIN).roundToLong().hashCode()

    override fun toString() = "amount=$amount, byBase=${amountInBaseUnits()}"
}