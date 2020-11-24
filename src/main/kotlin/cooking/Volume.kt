package cooking

class Volume(private val amount: Double, private val metric: Metric) {

    class Metric private constructor(private val baseUnitRatio: Double) {
        companion object {
            val teaspoon = Metric(1.0)
            val tablespoon = Metric(teaspoon.baseUnitRatio * 3)
        }

        fun convert(amount: Double) = this.baseUnitRatio * amount
    }
}