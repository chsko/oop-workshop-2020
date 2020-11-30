package probability

import comparable.Comparable.Companion.best
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import probability.Probability.Companion.p

// Understands testing Probability
internal class ProbabilityTest {

    @Test
    fun isCertain() {
        assertTrue(p(1.0).isCertain())
        assertFalse(p(0.3).isCertain())
        assertFalse(p(0.7).isCertain())
        assertFalse(p(0.0).isCertain())
    }

    @Test
    fun isNever() {
        assertFalse(p(1.0).isNever())
        assertFalse(p(0.7).isNever())
        assertTrue(p(0.0).isNever())
    }

    @Test
    fun not() {
        assertEquals(p(0.3), !p(0.7))
        assertEquals(p(1.0), !p(0.0))
        assertEquals(p(0.3), !!p(0.3))
        assertNotEquals(p(1.0), !p(1.0))
    }

    @Test
    fun and() {
        assertEquals(p(1.0), p(1.0) and p(1.0))
        assertEquals(p(0.75), p(0.75) and p(1.0))
        assertEquals(p(0.16), p(0.4) and p(0.4))
        assertEquals(p(0.21), p(0.7) and p(0.3))
        assertEquals(p(0.375), p(0.75) and p(0.5))
        assertNotEquals(p(0.7), p(0.7) and p(0.7))
        assertNotEquals(p(0.16), p(0.5) and p(0.4))
    }

    @Test
    fun or() {
        assertEquals(p(1.0), p(1.0) or p(1.0))
        assertEquals(p(1.0), p(0.75) or p(1.0))
        assertEquals(p(0.64), p(0.4) or p(0.4))
        assertEquals(p(0.79), p(0.7) or p(0.3))
        assertEquals(p(0.875), p(0.75) or p(0.5))
        assertNotEquals(p(0.7), p(0.7) or p(0.7))
        assertNotEquals(p(0.16), p(0.5) or p(0.4))
    }

    @Test
    fun `custom boundaries`(){
        assertEquals(percentage(100), !percentage(0))
        assertEquals(percentage(75), !percentage(25))
        assertEquals(percentage(70), !percentage(30))
        assertNotEquals(percentage(100), !percentage(100))

        assertEquals(percentage(25), percentage(50) and percentage(50))
        assertEquals(percentage(0), percentage(100) and percentage(0))
        assertNotEquals(percentage(100), percentage(100) and percentage(0))

        assertEquals(percentage(75), percentage(50) or percentage(50))
        assertEquals(percentage(100), percentage(100) or percentage(0))
        assertNotEquals(percentage(0), percentage(100) or percentage(0))
    }

    @Test
    fun comparable() {
        assertEquals(p(0.1), listOf(p(0.5), p(0.8), p(0.1), p(1.0)).best())
    }

    @Test
    fun equals() {
        val probability = p(1.0)
        assertEquals(p(1.0), p(1.0))
        assertEquals(p(0.3), p(0.3))
        assertNotEquals(p(0.0), p(1.0))
        assertNotEquals(p(0.3), p(0.7))
        assertEquals(probability, probability)
    }

    @Test
    fun hashcode() {
        assertEquals(p(1.0).hashCode(), p(1.0).hashCode())
        assertEquals(p(0.7).hashCode(), p(0.7).hashCode())
        assertEquals(p(0.3).hashCode(), (!!p(0.3)).hashCode())
        assertEquals(p(0.3).hashCode(), (!p(0.7)).hashCode())
    }

    private fun percentage(fraction: Int) = p(fraction.toDouble(), 0.0, 100.0)
}
