package graph

import graph.Path.Companion.NO_PATH
import graph.Path.Companion.hopCount
import graph.Path.Companion.minCost

class Node {
    private val vertices = mutableListOf<Vertex>()

    companion object {
        internal const val UNREACHABLE = Double.POSITIVE_INFINITY
    }

    infix fun to(target: Node) = this.costs(1.0) to target

    infix fun costs(amount: Number) = VertexBuilder(this, amount.toDouble())

    infix fun canReach(destination: Node) = path(destination, hopCount, emptyList()).goesAllTheWay()

    infix fun leastHopsTo(destination: Node) = path(destination, hopCount).hopCount()

    infix fun leastCostTo(destination: Node) = path(destination, minCost).cost()

    infix fun cheapestPathTo(destination: Node) = path(destination, minCost)

    private fun path(destination: Node, costStrategy: (Path) -> Double) =
        path(destination, costStrategy, emptyList())
            .also { require(it.goesAllTheWay()) { "The destination cannot be reached" } }

    internal fun path(destination: Node, costStrategy: (Path) -> Double, visited: List<Node>): Path {
        if (destination == this) return Path(costStrategy)
        if (this in visited) return NO_PATH
        return vertices.map { vertex -> vertex.path(destination, visited + this, costStrategy) }.minOrNull() ?: NO_PATH
    }

    class VertexBuilder(private val node: Node, private val amount: Double) {
        infix fun to(target: Node) = target.also { node.vertices.add(Vertex(target, amount)) }
    }
}