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

    //Understands a measure
    class Unit private constructor(private val baseUnitRatio: Double = 1.0, baseUnit: Unit? = null) {
        private val baseUnit: Unit = baseUnit ?: this

        internal companion object {
            private val teaspoon = Unit()
            private val tablespoon = Unit(teaspoon, 3)
            private val ounce = Unit(tablespoon, 2)
            private val cup = Unit(ounce, 8)
            private val pint = Unit(cup, 2)
            private val quart = Unit(pint, 2)
            private val gallon = Unit(quart, 4)

            internal val Number.teaspoon get() = Volume(this, Volume.Unit.teaspoon)
            internal val Number.tablespoon get() = Volume(this, Volume.Unit.tablespoon)
            internal val Number.ounce get() = Volume(this, Volume.Unit.ounce)
            internal val Number.cup get() = Volume(this, Volume.Unit.cup)
            internal val Number.pint get() = Volume(this, Volume.Unit.pint)
            internal val Number.quart get() = Volume(this, Volume.Unit.quart)
            internal val Number.gallon get() = Volume(this, Volume.Unit.gallon)

            private val inch = Unit()
            private val foot = Unit(inch, 12)
            private val yard = Unit(foot, 3)
            private val chain = Unit(yard, 22)
            private val furlong = Unit(chain, 10)
            private val mile = Unit(furlong, 8)

            internal val Number.inch get() = Volume(this, Volume.Unit.inch)
            internal val Number.foot get() = Volume(this, Volume.Unit.foot)
            internal val Number.yard get() = Volume(this, Volume.Unit.yard)
            internal val Number.chain get() = Volume(this, Volume.Unit.chain)
            internal val Number.furlong get() = Volume(this, Volume.Unit.furlong)
            internal val Number.mile get() = Volume(this, Volume.Unit.mile)
        }

        private constructor(baseUnit: Unit, baseUnitRatio: Number) :
                this(baseUnitRatio.toDouble() * baseUnit.baseUnitRatio, baseUnit.baseUnit)

        private fun convert(amount: Double) = this.baseUnitRatio * amount
        internal fun ratio(other: Unit) = other.baseUnitRatio / this.baseUnitRatio
        internal fun hashCode(amount: Double) = (convert(amount) / ERROR_MARGIN).roundToLong().hashCode()
        internal fun isCompatible(unit: Unit) = this.baseUnit == unit.baseUnit
    }

    private fun convert(other: Volume) = other.amount * this.unit.ratio(other.unit)

    private fun equals(other: Volume) = this isCompatible (other) && (this.amount - convert(other)).absoluteValue < ERROR_MARGIN

    override fun equals(other: Any?) = this === other || (other is Volume && this.equals(other))

    override fun hashCode() = 31 * (unit.hashCode(amount)).hashCode()

    override fun toString() = "amount=$amount"

    private infix fun isCompatible(other: Volume): Boolean = this.unit.isCompatible(other.unit)
}