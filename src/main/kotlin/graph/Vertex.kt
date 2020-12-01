package graph

// Understands how to connect to a Node
internal class Vertex(private val target: Node, private val cost: Double = 1.0) {
    fun path(destination: Node, visited: List<Node>, costStrategy: (Path) -> Double)
            = target.path(destination, costStrategy, visited).also { it.prepend(this) }

    internal companion object {
        fun List<Vertex>.cost() = this.sumByDouble { it.cost }
    }
}