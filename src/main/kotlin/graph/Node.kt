package graph

class Node {
    private val neighbors = mutableListOf<Node>()

    private fun canReach(destination: Node, visited: MutableList<Node>): Boolean =
        when {
            destination in (neighbors + this) -> true
            this in visited -> false
            else -> visited.add(this) && neighbors.any { it.canReach(destination, visited) }
        }

    infix fun canReach(destination: Node) = this.canReach(destination, mutableListOf())

    infix fun to(destination: Node) = neighbors.add(destination).let { destination }
}