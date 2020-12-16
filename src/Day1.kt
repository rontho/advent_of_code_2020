fun day1Part1(input: List<String>): Int {
    val map = input.map { it.toInt() }
    (0 until map.size - 1).forEach { i ->
        (1 until map.size).forEach { j ->
            if (map[i] + map[j] == 2020) {
                return map[i] * map[j]
            }
        }
    }
    return 0
}

fun day1Part2(input: List<String>): Int {
    val map = input.map { it.toInt() }
    (0 until map.size - 2).forEach { i ->
        (1 until map.size - 1).forEach { j ->
            (2 until map.size).forEach { k ->
                if (map[i] + map[j] + map[k] == 2020) {
                    return map[i] * map[j] * map[k]
                }
            }
        }
    }
    return 0
}
