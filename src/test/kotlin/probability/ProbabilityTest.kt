package probability

import org.junit.jupiter.api.Assertions.*
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
        assertNotEquals(Probability(0.16), Probability(0.5) and Probability(0.4))
        assertNotEquals(Probability(0.7), Probability(0.7) and Probability(0.7))
        assertEquals(coinToss(1), coinToss(3) and coinToss(2))
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

    private fun coinToss(fraction: Int) = Probability(fraction.toDouble(), 1.0, 6.0)
}
