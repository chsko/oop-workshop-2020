package rectangle

class Rectangle private constructor(length: Number, width: Number) {
    private val length = length.toDouble()
    private val width = width.toDouble()

    val area get() = length * width
    val perimiter get() = 2 * (length + width)

    infix fun betterThan(other: Rectangle) = this.area > other.area

    companion object {
        fun rectangle(length: Number, width: Number) = Rectangle(length, width)
        fun square(length: Number) = Rectangle(length, length)
        fun List<Rectangle>.best() = this.reduce { champion, challenger -> if (challenger betterThan champion) challenger else champion }
    }
}