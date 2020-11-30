package comparable

interface Comparable<T> {
    infix fun betterThan(other: T): Boolean

    companion object {
        fun <T: Comparable<T>> List<T>.best() =
            this.reduce { champion, challenger -> if (challenger.betterThan(champion)) challenger else champion }
    }
}