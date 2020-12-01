package graph

import graph.Node.Vertex.Companion.hopCountStrategy
import graph.Node.Vertex.Companion.cost
import java.lang.IllegalArgumentException

class Node {
    private val vertices = mutableListOf<Vertex>()

    companion object {
        private val UNREACHABLE = Double.POSITIVE_INFINITY
    }

    infix fun canReach(destination: Node) = this.cost(destination, emptyList(), hopCountStrategy) != UNREACHABLE

    infix fun costs(amount: Number) = VertexBuilder(this, amount.toDouble())

    infix fun connectsTo(target: Node) = this.costs(1.0).connectsTo(target)

    infix fun hopCount(destination: Node) = cost(destination, hopCountStrategy).toInt()

    infix fun minCost(destination: Node) = pathTo(destination).cost()

    infix fun pathTo(destination: Node) = path(destination, emptyList()) ?: throw IllegalArgumentException("Destination is unreachable")

    private fun cost(destination: Node, accumulateStrategy: (Double) -> Double) = cost(destination, emptyList(), accumulateStrategy).also {
        require(it != UNREACHABLE) { "There is no edge to this destination" }
    }

    private fun cost(destination: Node, visited: List<Node>, costStrategy: (Double) -> Double): Double = when {
        destination == this -> 0.0
        this in visited -> UNREACHABLE
        else -> vertices.minOfOrNull { vertex -> vertex.cost(destination, visited + this, costStrategy) }
            ?: UNREACHABLE
    }

    private fun path(destination: Node, visited: List<Node>): Path? {
        if (destination == this) return Path()
        if (this in visited) return null
        return vertices.mapNotNull { vertex -> vertex.path(destination, visited + this) }.minOrNull()
    }

    // Understands walking from a source to a destination via a set of vertices
    class Path internal constructor(): Comparable<Path> {
        private val vertices: MutableList<Vertex> = mutableListOf()

        fun cost() = vertices.cost()

        fun hopCount() = vertices.size

        internal fun prepend(vertex: Vertex) = vertices.add(0, vertex)

        override fun compareTo(other: Path) = this.cost().compareTo(other.cost())
    }

    // Understands how to connect to a Node
    internal class Vertex(private val target: Node, private val cost: Double = 1.0) {
        fun cost(destination: Node, visited: List<Node>, costStrategy: (Double) -> Double)
        = target.cost(destination, visited, costStrategy) + costStrategy(cost)

        fun path(destination: Node, visited: List<Node>) = target.path(destination, visited)?.also { it.prepend(this) }

        companion object {
            val hopCountStrategy = { _: Double -> 1.0 }
            fun List<Vertex>.cost() = this.sumByDouble { it.cost }
        }
    }

    class VertexBuilder(private val node: Node, private val amount: Double) {
        infix fun connectsTo(target: Node) = target.also { node.vertices.add(Vertex(target, amount)) }
    }
}