package probability

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

// Understands testing Probability
internal class ProbabilityTest {

    @Test
    fun isCertain() {
        assertTrue(Probability(1.0).isCertain())
        assertFalse(Probability(0.3).isCertain())
        assertFalse(Probability(0.7).isCertain())
        assertFalse(Probability(0.0).isCertain())
    }

    @Test
    fun isNever() {
        assertFalse(Probability(1.0).isNever())
        assertFalse(Probability(0.7).isNever())
        assertTrue(Probability(0.0).isNever())
    }

    @Test
    fun not() {
        assertEquals(Probability(0.3), !Probability(0.7))
        assertEquals(Probability(1.0), !Probability(0.0))
        assertEquals(Probability(0.3), !!Probability(0.3))
        assertNotEquals(Probability(1.0), !Probability(1.0))
    }

    @Test
    fun and() {
        assertEquals(Probability(1.0), Probability(1.0) and Probability(1.0))
        assertEquals(Probability(0.75), Probability(0.75) and Probability(1.0))
        assertEquals(Probability(0.16), Probability(0.4) and Probability(0.4))
        assertEquals(Probability(0.21), Probability(0.7) and Probability(0.3))
        assertEquals(Probability(0.375), Probability(0.75) and Probability(0.5))
        assertNotEquals(Probability(0.7), Probability(0.7) and Probability(0.7))
        assertNotEquals(Probability(0.16), Probability(0.5) and Probability(0.4))
    }

    @Test
    fun or() {
        assertEquals(Probability(1.0), Probability(1.0) or Probability(1.0))
        assertEquals(Probability(1.0), Probability(0.75) or Probability(1.0))
        assertEquals(Probability(0.64), Probability(0.4) or Probability(0.4))
        assertEquals(Probability(0.79), Probability(0.7) or Probability(0.3))
        assertEquals(Probability(0.875), Probability(0.75) or Probability(0.5))
        assertNotEquals(Probability(0.7), Probability(0.7) or Probability(0.7))
        assertNotEquals(Probability(0.16), Probability(0.5) or Probability(0.4))
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
    fun equals() {
        val probability = Probability(1.0)
        assertEquals(Probability(1.0), Probability(1.0))
        assertEquals(Probability(0.3), Probability(0.3))
        assertNotEquals(Probability(0.0), Probability(1.0))
        assertNotEquals(Probability(0.3), Probability(0.7))
        assertEquals(probability, probability)
    }

    @Test
    fun hashcode() {
        assertEquals(Probability(1.0).hashCode(), Probability(1.0).hashCode())
        assertEquals(Probability(0.7).hashCode(), Probability(0.7).hashCode())
        assertEquals(Probability(0.3).hashCode(), (!!Probability(0.3)).hashCode())
        assertEquals(Probability(0.3).hashCode(), (!Probability(0.7)).hashCode())
    }

    private fun percentage(fraction: Int) = Probability(fraction.toDouble(), 0.0, 100.0)
}
