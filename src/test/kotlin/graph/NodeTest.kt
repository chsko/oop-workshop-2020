package graph

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

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
        nodeB to nodeC to nodeD to nodeE to nodeB to nodeF
        nodeB to nodeA
        nodeC to nodeD
        nodeC to nodeE
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
}