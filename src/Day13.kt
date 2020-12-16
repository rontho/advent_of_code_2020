import kotlin.math.ceil

fun day13Part1(input: MutableList<String>): Int {
    var busIdToTake = 0

    val departureTime = input[0].toInt()
    val busIds = input[1].split(',').filter { it != "x" }.map { it.toDouble() }

    println("Departure at : $departureTime")
    println("Bus ids : $busIds")

    var minDiff = Int.MAX_VALUE

    busIds.forEach {
        val multiply = ceil(departureTime / it)
        val diff = (it * multiply) - departureTime
        if (diff < minDiff) {
            busIdToTake = it.toInt()
            minDiff = diff.toInt()
        }
        println(diff)
    }

    return minDiff * busIdToTake
}

fun day13Part2(input: MutableList<String>): Long {
    val busIds = input[0].split(',')
    println("Bus ids : $busIds")

    var mutiplier = 1L

    for (i in busIds.indices - 1) {
        val current = busIds[i]
        val next = busIds.first { it != "x" }

        val indexInterval = (busIds.indexOf(next) - busIds.indexOf(current)).toLong()

        if (next.toLong() * mutiplier - current.toLong() * mutiplier != indexInterval) {
            mutiplier++
        }
    }

    return 0L
}