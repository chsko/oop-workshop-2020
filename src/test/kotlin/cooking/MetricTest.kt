package cooking

import cooking.Volume.Metric.Companion.tablespoon
import cooking.Volume.Metric.Companion.teaspoon
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MetricTest {

    @Test
    @DisplayName("convert tablespoon (3 base unit)")
    fun convertTablespoon() {
        assertEquals(30.0, tablespoon.convert(10.0))
        assertEquals(9.0, tablespoon.convert(3.0))
        assertEquals(3.0, tablespoon.convert(1.0))
        assertEquals(2.25, tablespoon.convert(0.75))
    }

    @Test
    @DisplayName("convert teaspoon (1 base unit)")
    fun convertTeaspoon() {
        assertEquals(10.0, teaspoon.convert(10.0))
        assertEquals(3.0, teaspoon.convert(3.0))
        assertEquals(1.0, teaspoon.convert(1.0))
        assertEquals(0.75, teaspoon.convert(0.75))
    }
}