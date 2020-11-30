package cooking

class RatioQuantity(amount: Number, unit: Unit): IntervalQuantity(amount, unit) {
    operator fun unaryPlus() = this
    operator fun unaryMinus() = RatioQuantity(-amount, this.unit)
    operator fun plus(other: RatioQuantity) = RatioQuantity(this.amount + this.convert(other), this.unit)
    operator fun minus(other: RatioQuantity) = this + -other
}