package cooking

import cooking.IntervalQuantity.Companion.ERROR_MARGIN
import kotlin.math.roundToLong

//Understands a measure
class Unit private constructor(
    private val baseUnitRatio: Double = 1.0,
    baseUnit: Unit? = null,
    private val offset: Double = 0.0
) {
    private val baseUnit: Unit = baseUnit ?: this

    internal companion object {
        private val teaspoon = Unit()
        private val tablespoon = Unit(teaspoon, 3)
        private val ounce = Unit(tablespoon, 2)
        private val cup = Unit(ounce, 8)
        private val pint = Unit(cup, 2)
        private val quart = Unit(pint, 2)
        private val gallon = Unit(quart, 4)

        internal val Number.teaspoons get() = RatioQuantity(this, teaspoon)
        internal val Number.tablespoons get() = RatioQuantity(this, tablespoon)
        internal val Number.ounces get() = RatioQuantity(this, ounce)
        internal val Number.cups get() = RatioQuantity(this, cup)
        internal val Number.pints get() = RatioQuantity(this, pint)
        internal val Number.quarts get() = RatioQuantity(this, quart)
        internal val Number.gallons get() = RatioQuantity(this, gallon)

        private val inch = Unit()
        private val foot = Unit(inch, 12)
        private val yard = Unit(foot, 3)
        private val chain = Unit(yard, 22)
        private val furlong = Unit(chain, 10)
        private val mile = Unit(furlong, 8)

        internal val Number.inches get() = RatioQuantity(this, inch)
        internal val Number.feet get() = RatioQuantity(this, foot)
        internal val Number.yards get() = RatioQuantity(this, yard)
        internal val Number.chains get() = RatioQuantity(this, chain)
        internal val Number.furlongs get() = RatioQuantity(this, furlong)
        internal val Number.miles get() = RatioQuantity(this, mile)

        private val celsius = Unit()
        private val fahrenheit = Unit(5.0/9.0, celsius, 32.0)

        internal val Number.celsius get() = IntervalQuantity(this, Unit.celsius)
        internal val Number.fahrenheit get() = IntervalQuantity(this, Unit.fahrenheit)
    }

    private constructor(baseUnit: Unit, baseUnitRatio: Number) :
            this(baseUnitRatio.toDouble() * baseUnit.baseUnitRatio, baseUnit.baseUnit)

    internal fun hashCode(amount: Double) = ((amount - offset) * baseUnitRatio / ERROR_MARGIN).roundToLong().hashCode()
    internal fun isCompatible(unit: Unit) = this.baseUnit == unit.baseUnit
    internal fun convert(amount: Double, other: Unit) = ((amount - other.offset) * ratio(other) + this.offset).also {
        require(this.isCompatible(other)) { "Incompatible units for arithmetic" }
    }

    private fun ratio(other: Unit) = other.baseUnitRatio / this.baseUnitRatio
}
