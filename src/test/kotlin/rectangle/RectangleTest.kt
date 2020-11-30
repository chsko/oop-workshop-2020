package rectangle

import comparable.Comparable.Companion.best
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import rectangle.Rectangle.Companion.rectangle
import rectangle.Rectangle.Companion.square

internal class RectangleTest {

    @Test
    fun area() {
        assertEquals(24.0, rectangle(4, 6).area)
        assertEquals(24.0, rectangle(6, 4).area)
        assertEquals(4.0, rectangle(2, 2).area)
        assertEquals(18.0, rectangle(2, 9).area)
        assertEquals(16.0, square(4).area)
        assertEquals(4.0, square(2).area)
    }

    @Test
    fun perimiter() {
        assertEquals(12.0, rectangle(4, 2).perimiter)
        assertEquals(16.0, rectangle(4, 4).perimiter)
        assertEquals(16.0, square(4).perimiter)
        assertEquals(40.0, square(10).perimiter)
    }

    @Test
    fun betterThan() {
        val rectangles = listOf(rectangle(4, 4), rectangle(4, 4), rectangle(2, 4), rectangle(2, 1))
        assertEquals(rectangle(4, 4).area, rectangles.best().area)
    }
}