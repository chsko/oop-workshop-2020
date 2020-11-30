package graph

class Node {
    private val vertices = mutableListOf<Vertex>()

    companion object {
        private val UNREACHABLE = Double.POSITIVE_INFINITY
    }

    infix fun canReach(destination: Node) = this.minCost(destination, emptyList()) != UNREACHABLE

    private fun minCost(destination: Node, visited: List<Node>): Double {
        return when {
            destination == this -> 0.0
            this in visited -> UNREACHABLE
            else -> vertices.minOfOrNull { vertex -> vertex.minCost(destination, visited + this) } ?: UNREACHABLE
        }
    }

    infix fun minCost(destination: Node) = minCost(destination, listOf()).also {
        require(it != UNREACHABLE) { "There is no edge to this destination" }
    }.toInt()

    infix fun costs(amount: Number): VertexBuilder {
        return VertexBuilder(this, amount.toDouble())
    }

    infix fun connectsTo(target: Node) = this.costs(1.0).connectsTo(target)

    private fun addVertex(target: Vertex) {
        vertices.add(target)
    }

    // Understands how to connect to a Node
    private class Vertex(private val target: Node, private val cost: Double = 1.0) {
        fun minCost(destination: Node, visited: List<Node>) = target.minCost(destination, visited) + cost
    }

    class VertexBuilder(private val node: Node, private val amount: Double) {
        infix fun connectsTo(target: Node) = target.also { node.addVertex(Vertex(target, amount)) }
    }
}