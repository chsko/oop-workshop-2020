package cooking

import cooking.Unit.Companion.celsius
import cooking.Unit.Companion.chains
import cooking.Unit.Companion.cups
import cooking.Unit.Companion.fahrenheit
import cooking.Unit.Companion.feet
import cooking.Unit.Companion.furlongs
import cooking.Unit.Companion.gallons
import cooking.Unit.Companion.inches
import cooking.Unit.Companion.miles
import cooking.Unit.Companion.ounces
import cooking.Unit.Companion.pints
import cooking.Unit.Companion.quarts
import cooking.Unit.Companion.tablespoons
import cooking.Unit.Companion.teaspoons
import cooking.Unit.Companion.yards
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class QuantityTest {

    @Test
    fun teaspoons() {
        assertEquals(0.0013020833.gallons, 1.teaspoons)
    }

    @Test
    fun tablespoons() {
        assertEquals(3.teaspoons, 1.tablespoons)
        assertEquals(2.25.teaspoons, 0.75.tablespoons)
        assertEquals(2.ounces, 4.tablespoons)
        assertEquals(0.375.ounces, 0.75.tablespoons)
        assertEquals(0.25.cups, 4.tablespoons)
        assertEquals(0.03125.pints, 1.tablespoons)
        assertEquals(0.15625.quarts, 10.tablespoons)
        assertEquals(0.05859375.gallons, 15.tablespoons)
    }

    @Test
    fun ounces() {
        assertEquals(2.tablespoons, 1.ounces)
        assertEquals(1.5.tablespoons, 0.75.ounces)
        assertEquals(4.5.teaspoons, 0.75.ounces)
        assertEquals(6.teaspoons, 1.ounces)
        assertEquals(0.625.cups, 5.ounces)
        assertEquals(0.0625.pints, 1.ounces)
    }

    @Test
    fun cups() {
        assertEquals(8.ounces, 1.cups)
        assertEquals(18.ounces, 2.25.cups)
        assertEquals(192.teaspoons, 4.cups)
        assertEquals(48.tablespoons, 3.cups)
        assertEquals(4.tablespoons, 0.25.cups)
        assertEquals(0.5.pints, 1.cups)
    }

    @Test
    fun pints() {
        assertEquals(2.cups, 1.pints)
        assertEquals(0.2.cups, 0.1.pints)
        assertEquals(13.6.ounces, 0.85.pints)
        assertEquals(13.6.ounces, 0.85.pints)
        assertEquals(384.teaspoons, 4.pints)
        assertEquals(1600.tablespoons, 50.pints)
    }

    @Test
    fun quart() {
        assertEquals(2.pints, 1.quarts)
        assertEquals(3.pints, 1.5.quarts)
        assertEquals(1.cups, 0.25.quarts)
        assertEquals(20.cups, 5.quarts)
        assertEquals(32.ounces, 1.quarts)
        assertEquals(32.tablespoons, 0.5.quarts)
        assertEquals(3840.teaspoons, 20.quarts)
    }

    @Test
    fun gallon() {
        assertEquals(4.quarts, 1.gallons)
        assertEquals(0.04.quarts, 0.01.gallons)
        assertEquals(19.44.pints, 2.43.gallons)
        assertEquals(3.44.pints, 0.43.gallons)
        assertEquals(64.cups, 4.gallons)
        assertEquals(64.ounces, 0.5.gallons)
        assertEquals(281.6.tablespoons, 1.1.gallons)
        assertEquals(38400.teaspoons, 50.gallons)
    }

    @Test
    fun notEquals() {
        assertNotEquals(1.tablespoons, 2.teaspoons)
        assertNotEquals(3.ounces, 4.tablespoons)
        assertNotEquals(4.cups, 15.ounces)
        assertNotEquals(1.pints, 0.25.cups)
        assertNotEquals(0.0001.quarts, 0.01.pints)
        assertNotEquals(10.gallons, 400.quarts)
        assertNotEquals(100.tablespoons, 1.gallons)
    }

    @Test
    fun `distance measures`() {
        assertEquals(12.inches, 1.feet)
        assertEquals(3.feet, 1.yards)
        assertEquals(22.yards, 1.chains)
        assertEquals(10.chains, 1.furlongs)
        assertEquals(8.furlongs, 1.miles)
        assertEquals(0.00001578.miles, 1.inches)
    }

    @Test
    fun `different types`() {
        assertNotEquals(1.tablespoons, 1.inches)
    }

    @Test
    fun `same object`() {
        val tablespoon = 1.tablespoons
        assertEquals(tablespoon, tablespoon)
    }

    @Test
    fun `amount is 0`() {
        assertEquals(0.tablespoons, 0.teaspoons)
    }

    @Test
    fun temperatures() {
        assertEquals(0.celsius, 32.fahrenheit)
        assertEquals(10.celsius, 50.fahrenheit)
        assertEquals((-40).celsius, (-40).fahrenheit)
    }

    @Test
    fun arithmetic() {
        assertEquals(2.tablespoons, 1.tablespoons + 3.teaspoons)
        assertEquals(0.tablespoons, 1.tablespoons - 3.teaspoons)
        assertEquals((-1).teaspoons, -1.teaspoons)
        assertEquals((+1).teaspoons, +1.teaspoons)
    }

    @Test
    fun hashcode() {
        assertEquals(1.tablespoons.hashCode(), 1.tablespoons.hashCode())
        assertEquals(1.tablespoons.hashCode(), 3.teaspoons.hashCode())
        assertEquals(0.75.tablespoons.hashCode(), 2.25.teaspoons.hashCode())
        assertEquals(0.0013020833.gallons.hashCode(), 1.teaspoons.hashCode())
        assertNotEquals(0.75.tablespoons.hashCode(), 2.5.teaspoons.hashCode())
    }
}