package graph

import graph.Node.Companion.UNREACHABLE
import graph.Vertex.Companion.cost

// Understands walking from a source to a destination via a set of vertices
open class Path internal constructor(private val costStrategy: (Path) -> Double): Comparable<Path> {
    private val vertices: MutableList<Vertex> = mutableListOf()

    open fun cost() = vertices.cost()

    fun hopCount() = hops().toInt()

    protected open fun hops() = vertices.size.toDouble()

    internal fun prepend(vertex: Vertex) = vertices.add(0, vertex)

    override fun compareTo(other: Path) = costStrategy(this).compareTo(costStrategy(other))

    internal fun goesAllTheWay() = cost().isFinite()

    companion object {

        val minCost = { path: Path -> path.cost() }
        val hopCount = { path: Path -> path.hops() }

        internal val NO_PATH = object : Path(minCost) {
            override fun cost() = UNREACHABLE

            override fun hops() = UNREACHABLE
        }
    }
}