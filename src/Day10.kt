fun day10Part1(input: MutableList<String>): Int {
    var differenceOf1 = 0
    var differenceOf3 = 1

    val map = input.map { it.toInt() }
    val sorted = map.sorted()

    var current = 0
    for (i in sorted.indices) {
        val diff = sorted[i] - current
        if (diff == 1) differenceOf1++
        else differenceOf3++
        current = sorted[i]
    }

    println(sorted)
    return differenceOf1 * differenceOf3
}

fun day10Part2(input: MutableList<String>): Long {
    val map = input.map { it.toLong() }
    val sorted = map.sorted()
    return countWays(sorted, mutableListOf(0), 0, 0)
}

private val memo = mutableMapOf<Long, Long>()
private fun countWays(sortedAdapters: List<Long>, currentAdapters: List<Long>, index: Int, current: Long): Long {
    var count = 0L
    if (memo[current] != null) return memo[current]!!
    for (i in index until index + 3) {
        if (i >= sortedAdapters.size) break
        if (sortedAdapters[i] - current <= 3) {
            count += countWays(sortedAdapters, currentAdapters + sortedAdapters[i], i + 1, sortedAdapters[i])
        }
    }
    if (sortedAdapters[sortedAdapters.size - 1] - currentAdapters[currentAdapters.size - 1] <= 0) count++
    memo[current] = count
    return count //2644613988352
}
