package cooking

import cooking.Volume.Metric.Companion.tablespoon
import cooking.Volume.Metric.Companion.teaspoon
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class VolumeTest {

    @Test
    fun equals() {
        assertEquals(Volume(1.0, tablespoon), Volume(3.0, teaspoon))
        assertEquals(Volume(3.0, tablespoon), Volume(9.0, teaspoon))
        assertEquals(Volume(1.5, tablespoon), Volume(4.5, teaspoon))
        assertEquals(Volume(0.75, tablespoon), Volume(2.25, teaspoon))
        assertEquals(Volume(10.0, tablespoon), Volume(30.0, teaspoon))
        assertEquals(Volume(40.0, teaspoon), Volume(13.33, tablespoon))
        assertNotEquals(Volume(1.0, tablespoon), Volume(1.0, teaspoon))
    }

    @Test
    fun hashcode() {
        assertEquals(Volume(1.0, tablespoon).hashCode(), Volume(1.0, tablespoon).hashCode())
        assertEquals(Volume(1.0, tablespoon).hashCode(), Volume(3.0, teaspoon).hashCode())
        assertEquals(Volume(0.75, tablespoon).hashCode(), Volume(2.25, teaspoon).hashCode())
        assertNotEquals(Volume(0.75, tablespoon).hashCode(), Volume(2.5, teaspoon).hashCode())
    }
}