package graph

import graph.Node.Companion.UNREACHABLE
import graph.Node.Vertex.Companion.cost

// Understands walking from a source to a destination via a set of vertices
open class Path internal constructor(): Comparable<Path> {
    private val vertices: MutableList<Node.Vertex> = mutableListOf()

    open fun cost() = vertices.cost()

    open fun hopCount() = vertices.size

    internal fun prepend(vertex: Node.Vertex) = vertices.add(0, vertex)

    override fun compareTo(other: Path) = this.cost().compareTo(other.cost())

    internal fun goesAllTheWay() = cost() != UNREACHABLE

    companion object {
        val NO_PATH = object : Path() {
            override fun cost(): Double {
                return UNREACHABLE
            }

            override fun hopCount(): Int {
                return UNREACHABLE.toInt()
            }
        }
    }
}