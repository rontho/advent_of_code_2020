fun day15Part1(): Int {
    var current = 14
    val history = mutableMapOf(1 to 0, 20 to 1, 8 to 2, 12 to 3, 0 to 4)
    val start = history.size

    for (i in start until 30000000) {
        if (!history.containsKey(current)) {
            println("Turn $i -- $current first time so next is 0")
            println("Turn $i -- add $current to history at index $i")
            history[current] = i
            current = 0
        } else {
            val lastIndex = history[current]
            println("Turn $i -- $current exist in history at index $lastIndex")
            println("Turn $i -- add $current to history at index $i next is ${i - lastIndex!!}")
            history[current] = i
            current = i - lastIndex!!
        }
    }

    return current
}

fun day15Part2(input: MutableList<String>): Int = 0