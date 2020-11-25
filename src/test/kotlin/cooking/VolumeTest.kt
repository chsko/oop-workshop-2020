package cooking

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

internal class VolumeTest {

    @Test
    @DisplayName("Volume must be >= 0")
    fun init() {
        assertThrows<IllegalArgumentException>{ (-1).teaspoon }
        assertDoesNotThrow { 0.teaspoon }
    }

    @Test
    fun teaspoons() {
        assertEquals(0.0013020833.gallon, 1.teaspoon)
    }

    @Test
    fun tablespoons() {
        assertEquals(3.teaspoon, 1.tablespoon)
        assertEquals(2.25.teaspoon, 0.75.tablespoon)
        assertEquals(2.ounce, 4.tablespoon)
        assertEquals(0.375.ounce, 0.75.tablespoon)
        assertEquals(0.25.cup, 4.tablespoon)
        assertEquals(0.03125.pint, 1.tablespoon)
        assertEquals(0.15625.quart, 10.tablespoon)
        assertEquals(0.05859375.gallon, 15.tablespoon)
    }

    @Test
    fun ounces() {
        assertEquals(2.tablespoon, 1.ounce)
        assertEquals(1.5.tablespoon, 0.75.ounce)
        assertEquals(4.5.teaspoon, 0.75.ounce)
        assertEquals(6.teaspoon, 1.ounce)
        assertEquals(0.625.cup, 5.ounce)
        assertEquals(0.0625.pint, 1.ounce)
    }

    @Test
    fun cups() {
        assertEquals(8.ounce, 1.cup)
        assertEquals(18.ounce, 2.25.cup)
        assertEquals(192.teaspoon, 4.cup)
        assertEquals(48.tablespoon, 3.cup)
        assertEquals(4.tablespoon, 0.25.cup)
        assertEquals(0.5.pint, 1.cup)
    }

    @Test
    fun pints() {
        assertEquals(2.cup, 1.pint)
        assertEquals(0.2.cup, 0.1.pint)
        assertEquals(13.6.ounce, 0.85.pint)
        assertEquals(13.6.ounce, 0.85.pint)
        assertEquals(384.teaspoon, 4.pint)
        assertEquals(1600.tablespoon, 50.pint)
    }

    @Test
    fun quart() {
        assertEquals(2.pint, 1.quart)
        assertEquals(3.pint, 1.5.quart)
        assertEquals(1.cup, 0.25.quart)
        assertEquals(20.cup, 5.quart)
        assertEquals(32.ounce, 1.quart)
        assertEquals(32.tablespoon, 0.5.quart)
        assertEquals(3840.teaspoon, 20.quart)
    }

    @Test
    fun gallon() {
        assertEquals(4.quart, 1.gallon)
        assertEquals(0.04.quart, 0.01.gallon)
        assertEquals(19.44.pint, 2.43.gallon)
        assertEquals(3.44.pint, 0.43.gallon)
        assertEquals(64.cup, 4.gallon)
        assertEquals(64.ounce, 0.5.gallon)
        assertEquals(281.6.tablespoon, 1.1.gallon)
        assertEquals(38400.teaspoon, 50.gallon)
    }

    @Test
    fun notEquals() {
        assertNotEquals(1.tablespoon, 2.teaspoon)
        assertNotEquals(3.ounce, 4.tablespoon)
        assertNotEquals(4.cup, 15.ounce)
        assertNotEquals(1.pint, 0.25.cup)
        assertNotEquals(0.0001.quart, 0.01.pint)
        assertNotEquals(10.gallon, 400.quart)
        assertNotEquals(100.tablespoon, 1.gallon)
    }

    @Test
    fun equals() {
        val tablespoon = Volume(1.0, Volume.Unit.teaspoon)
        assertEquals(tablespoon, tablespoon)
    }

    @Test
    fun hashcode() {
        assertEquals(1.tablespoon.hashCode(), 1.tablespoon.hashCode())
        assertEquals(1.tablespoon.hashCode(), 3.teaspoon.hashCode())
        assertEquals(0.75.tablespoon.hashCode(), 2.25.teaspoon.hashCode())
        assertEquals(0.0013020833.gallon.hashCode(), 1.teaspoon.hashCode())
        assertNotEquals(0.75.tablespoon.hashCode(), 2.5.teaspoon.hashCode())

    }

    private val Double.teaspoon get() = Volume(this, Volume.Unit.teaspoon)
    private val Int.teaspoon get() = this.toDouble().teaspoon

    private val Double.tablespoon get() = Volume(this, Volume.Unit.tablespoon)
    private val Int.tablespoon get() = this.toDouble().tablespoon

    private val Double.ounce get() = Volume(this, Volume.Unit.ounce)
    private val Int.ounce get() = this.toDouble().ounce

    private val Double.cup get() = Volume(this, Volume.Unit.cup)
    private val Int.cup get() = this.toDouble().cup

    private val Double.pint get() = Volume(this, Volume.Unit.pint)
    private val Int.pint get() = this.toDouble().pint

    private val Double.quart get() = Volume(this, Volume.Unit.quart)
    private val Int.quart get() = this.toDouble().quart

    private val Double.gallon get() = Volume(this, Volume.Unit.gallon)
    private val Int.gallon get() = this.toDouble().gallon
}