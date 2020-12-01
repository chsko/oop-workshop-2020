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
        nodeB costs 6.0 connectsTo nodeC costs 1.0 connectsTo nodeD costs 2.0 connectsTo nodeE costs 3.0 connectsTo nodeB costs 4.0 connectsTo nodeF
        nodeB costs 5.0 connectsTo nodeA
        nodeC costs 7.0 connectsTo nodeD
        nodeC costs 8.0 connectsTo nodeE
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
    fun hopCount() {
        assertEquals(3, nodeC hopCount nodeF)
        assertEquals(1, nodeB hopCount nodeF)
        assertEquals(2, nodeD hopCount nodeB)
        assertEquals(0, nodeB hopCount nodeB)
    }

    @Test
    fun pathTo() {
        val path = nodeC pathTo nodeF
        assertEquals(10.0, path.cost())
        assertEquals(4, path.hopCount())
    }

    @Test
    fun noPath() {
        assertThrows<IllegalArgumentException> { nodeB pathTo nodeG }
    }

    @Test
    fun `weighted vertices`() {
        assertEquals(10.0, nodeC minCost nodeF)
        assertEquals(7.0, nodeB minCost nodeD)
        assertEquals(0.0, nodeB minCost nodeB)
        assertEquals(10.0, nodeE minCost nodeD)
    }
}