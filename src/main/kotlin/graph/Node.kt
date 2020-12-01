package graph

import graph.Node.Vertex.Companion.hopCountStrategy
import graph.Path.Companion.NO_PATH

class Node {
    private val vertices = mutableListOf<Vertex>()

    companion object {
        internal const val UNREACHABLE = Double.POSITIVE_INFINITY
    }

    infix fun connectsTo(target: Node) = this.costs(1.0).connectsTo(target)

    infix fun costs(amount: Number) = VertexBuilder(this, amount.toDouble())

    infix fun canReach(destination: Node) = path(destination).goesAllTheWay()

    infix fun hopCount(destination: Node) = cost(destination, hopCountStrategy).toInt()

    infix fun minCost(destination: Node) = pathTo(destination).cost()

    infix fun pathTo(destination: Node) = path(destination).also { require(it.goesAllTheWay()) { "The destination cannot be reached" } }

    private fun cost(destination: Node, accumulateStrategy: (Double) -> Double) = cost(destination, emptyList(), accumulateStrategy).also {
        require(it != UNREACHABLE) { "There is no edge to this destination" }
    }

    private fun cost(destination: Node, visited: List<Node>, costStrategy: (Double) -> Double): Double = when {
        destination == this -> 0.0
        this in visited -> UNREACHABLE
        else -> vertices.minOfOrNull { vertex -> vertex.cost(destination, visited + this, costStrategy) }
            ?: UNREACHABLE
    }

    private fun path(destination: Node, visited: List<Node> = emptyList()): Path {
        if (destination == this) return Path()
        if (this in visited) return NO_PATH
        return vertices.map { vertex -> vertex.path(destination, visited + this) }.minOrNull() ?: NO_PATH
    }

    // Understands how to connect to a Node
    internal class Vertex(private val target: Node, private val cost: Double = 1.0) {
        fun cost(destination: Node, visited: List<Node>, costStrategy: (Double) -> Double)
        = target.cost(destination, visited, costStrategy) + costStrategy(cost)

        fun path(destination: Node, visited: List<Node>) = target.path(destination, visited).also { it.prepend(this) }

        companion object {
            val hopCountStrategy = { _: Double -> 1.0 }
            fun List<Vertex>.cost() = this.sumByDouble { it.cost }
        }
    }

    class VertexBuilder(private val node: Node, private val amount: Double) {
        infix fun connectsTo(target: Node) = target.also { node.vertices.add(Vertex(target, amount)) }
    }
}