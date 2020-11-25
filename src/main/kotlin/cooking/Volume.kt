package cooking

import kotlin.math.absoluteValue
import kotlin.math.roundToLong

// Understands the size of something
class Volume(amount: Number, private val unit: Unit) {
    private val amount: Double = amount.toDouble()

    init {
        require(this.amount >= 0.0) { "Must occupy a minimum of 0 unit of space, currently set to $amount" }
    }

    private companion object {
        private const val ERROR_MARGIN = 0.0000001
    }

    //Understands
    class Unit private constructor(private val baseUnitRatio: Double) {
        companion object {
            private const val BASE_UNIT = 1.0
            private val teaspoon = Unit(BASE_UNIT * 1.0)
            private val tablespoon = Unit(teaspoon.baseUnitRatio * 3.0)
            private val ounce = Unit(tablespoon.baseUnitRatio * 2.0)
            private val cup = Unit(ounce.baseUnitRatio * 8.0)
            private val pint = Unit(cup.baseUnitRatio * 2.0)
            private val quart = Unit(pint.baseUnitRatio * 2.0)
            private val gallon = Unit(quart.baseUnitRatio * 4.0)

            internal val Number.teaspoon get() = Volume(this, Volume.Unit.teaspoon)
            internal val Number.tablespoon get() = Volume(this, Volume.Unit.tablespoon)
            internal val Number.ounce get() = Volume(this, Volume.Unit.ounce)
            internal val Number.cup get() = Volume(this, Volume.Unit.cup)
            internal val Number.pint get() = Volume(this, Volume.Unit.pint)
            internal val Number.quart get() = Volume(this, Volume.Unit.quart)
            internal val Number.gallon get() = Volume(this, Volume.Unit.gallon)
        }

        private fun convert(amount: Double) = this.baseUnitRatio * amount
        fun ratio(other: Unit) = other.baseUnitRatio / this.baseUnitRatio
        fun hashCode(amount: Double) = (convert(amount) / ERROR_MARGIN).roundToLong().hashCode()
    }

    private fun convert(other: Volume) = other.amount * this.unit.ratio(other.unit)

    private fun equals(other: Volume) = (this.amount - convert(other)).absoluteValue < ERROR_MARGIN

    override fun equals(other: Any?) = this === other || (other is Volume && this.equals(other))

    override fun hashCode() = 31 * (unit.hashCode(amount)).hashCode()

    override fun toString() = "amount=$amount"
}