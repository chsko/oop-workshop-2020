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
        nodeB connectsTo nodeC connectsTo nodeD connectsTo nodeE connectsTo nodeB connectsTo nodeF
        nodeB connectsTo nodeA
        nodeC connectsTo nodeD
        nodeC connectsTo nodeE
        nodeH connectsTo nodeB
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
    fun minCost() {
        assertEquals(3, nodeC minCost nodeF)
        assertEquals(1, nodeB minCost nodeF)
        assertEquals(2, nodeD minCost nodeB)
        assertEquals(0, nodeB minCost nodeB)
    }

    @Test
    fun `weighted vertices`() {
        val nodeA = Node()
        val nodeB = Node()
        val nodeC = Node()
        val nodeD = Node()

        nodeA costs 2 connectsTo nodeB costs 3 connectsTo nodeC connectsTo nodeA

        assertEquals(5, nodeA minCost nodeC)
        assertEquals(1, nodeC minCost nodeA)
        assertEquals(4, nodeB minCost nodeA)
        assertEquals(0, nodeA minCost nodeA)
        assertThrows<IllegalArgumentException> { nodeD minCost nodeA }
    }
}