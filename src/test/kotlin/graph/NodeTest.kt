package graph

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

internal class NodeTest {
    companion object {
        val nodeA = Node()
        val nodeB = Node()
        val nodeC = Node()
        val nodeD = Node()
        val nodeE = Node()
        val nodeF = Node()
        val nodeG = Node()
        val nodeH = Node()
    }

    init {
        nodeB costs 6.0 to nodeC costs 1.0 to nodeD costs 2.0 to nodeE costs 3.0 to nodeB costs 4.0 to nodeF
        nodeB costs 5.0 to nodeA
        nodeC costs 7.0 to nodeD
        nodeC costs 8.0 to nodeE
        nodeH to nodeB
    }

    @Test
    fun `can reach directly`() {
        assertTrue(nodeB canReach nodeA)
        assertTrue(nodeB canReach nodeF)
        assertTrue(nodeH canReach nodeB)
        assertTrue(nodeG canReach nodeG)
        assertFalse(nodeA canReach nodeF)
        assertFalse(nodeF canReach nodeB)
        assertFalse(nodeB canReach nodeG)
    }

    @Test
    fun `can reach recursively`() {
        assertTrue(nodeB canReach nodeE)
        assertTrue(nodeE canReach nodeC)
        assertTrue(nodeC canReach nodeA)
        assertTrue(nodeD canReach nodeC)
        assertFalse(nodeB canReach nodeH)
        assertFalse(nodeC canReach nodeH)
        assertFalse(nodeD canReach nodeG)
    }

    @Test
    fun hopCount() {
        assertThrows<IllegalArgumentException> { nodeA leastHopsTo nodeF }
        assertEquals(3, nodeC leastHopsTo nodeF)
        assertEquals(1, nodeB leastHopsTo nodeF)
        assertEquals(2, nodeD leastHopsTo nodeB)
        assertEquals(0, nodeB leastHopsTo nodeB)
    }

    @Test fun cost() {
        assertEquals(0.0, nodeB leastCostTo nodeB)
        assertEquals(5.0, nodeB leastCostTo nodeA)
        assertEquals(4.0, nodeB leastCostTo nodeF)
        assertEquals(7.0, nodeB leastCostTo nodeD)
        assertEquals(10.0, nodeC leastCostTo nodeF)
        assertEquals(10.0, nodeE leastCostTo nodeD)
        assertThrows<IllegalArgumentException> { nodeG leastCostTo nodeB }
        assertThrows<IllegalArgumentException> { nodeA leastCostTo nodeB }
        assertThrows<IllegalArgumentException> { nodeB leastCostTo nodeG }
    }

    @Test fun path() {
        assertPath(nodeA, nodeA, 0, 0)
        assertPath(nodeB, nodeA, 1, 5)
        assertPath(nodeB, nodeF, 1, 4)
        assertPath(nodeB, nodeD, 2, 7)
        assertPath(nodeC, nodeF, 4, 10)
        assertThrows<IllegalArgumentException> { nodeG cheapestPathTo nodeB }
        assertThrows<IllegalArgumentException> { nodeA cheapestPathTo nodeB }
        assertThrows<IllegalArgumentException> { nodeB cheapestPathTo nodeG }
    }

    private fun assertPath(source: Node, destination: Node, expectedHopCount: Int, expectedCost: Number) {
        val p = source cheapestPathTo destination
        assertEquals(expectedHopCount, p.hopCount())
        assertEquals(expectedCost.toDouble(), p.cost())
    }
}