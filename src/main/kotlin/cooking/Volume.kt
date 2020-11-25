package cooking

import kotlin.math.absoluteValue
import kotlin.math.roundToLong

class Volume(private val amount: Double, private val unit: Unit) {

    init {
        require(amount >= 0) { "Must occupy a minimum of 0 unit of space, currently set to $amount" }
    }

    private companion object {
        private const val ERROR_MARGIN = 0.0000001
    }

    class Unit private constructor(private val baseUnitRatio: Double) {
        companion object {
            private const val BASE_UNIT = 1.0
            val teaspoon = Unit(BASE_UNIT * 1.0)
            val tablespoon = Unit(teaspoon.baseUnitRatio * 3.0)
            val ounce = Unit(tablespoon.baseUnitRatio * 2.0)
            val cup = Unit(ounce.baseUnitRatio * 8.0)
            val pint = Unit(cup.baseUnitRatio * 2.0)
            val quart = Unit(pint.baseUnitRatio * 2.0)
            val gallon = Unit(quart.baseUnitRatio * 4.0)
        }

        fun convert(amount: Double) = this.baseUnitRatio * amount
    }

    private fun amountInBaseUnits() = unit.convert(amount)

    private fun equals(other: Volume) = (this.amountInBaseUnits() - other.amountInBaseUnits()).absoluteValue < ERROR_MARGIN

    override fun equals(other: Any?) = this === other || (other is Volume && this.equals(other))

    override fun hashCode() = 31 * (amountInBaseUnits() / ERROR_MARGIN).roundToLong().hashCode()

    override fun toString() = "amount=$amount, byBase=${amountInBaseUnits()}"
}