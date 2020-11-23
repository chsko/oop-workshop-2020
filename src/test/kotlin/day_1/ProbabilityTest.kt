package day_1

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

// Understands testing day_1.Probability
internal class ProbabilityTest {

    @Test
    fun isCertain() {
        assertTrue(Probability(1.0).isCertain())
        assertFalse(Probability(0.5).isCertain())
        assertFalse(Probability(0.0).isCertain())
    }

    @Test
    fun isNever() {
        assertFalse(Probability(1.0).isNever())
        assertFalse(Probability(0.5).isNever())
        assertTrue(Probability(0.0).isNever())
    }

    @Test
    fun equals() {
        val probability = Probability(1.0)
        assertTrue(Probability(1.0) == Probability(1.0))
        assertFalse(Probability(0.0) == Probability(1.0))
        assertTrue(probability == probability)
    }
}
