import kotlin.math.max

fun day5Part1(input: MutableList<String>): Int {
    var highest = 0
    input.forEach {

        var maxRow = 127
        var minRow = 0

        for (i in 0 until 6) {
            if (it[i] == 'F') {
                maxRow = (minRow + maxRow) / 2
            } else {
                minRow = ((minRow + maxRow) / 2) + 1
            }
        }

        val currentRow = if (it[6] == 'F') minRow else maxRow

        var maxCol = 7
        var minCol = 0

        for (i in 7 until 10) {
            if (it[i] == 'L') {
                maxCol = (minCol + maxCol) / 2
            } else {
                minCol = ((minCol + maxCol) / 2) + 1
            }
        }

        val currentCol = if (it[9] == 'L') minCol else maxCol
        highest = max(highest, (currentRow * 8 + currentCol))
    }

    return highest
}

fun day5Part2(input: MutableList<String>): Int {
    val planIds = mutableListOf<Int>()

    input.forEach {

        var maxRow = 127
        var minRow = 0

        for (i in 0 until 6) {
            if (it[i] == 'F') {
                maxRow = (minRow + maxRow) / 2
            } else {
                minRow = ((minRow + maxRow) / 2) + 1
            }
        }

        val currentRow = if (it[6] == 'F') minRow else maxRow

        var maxCol = 7
        var minCol = 0

        for (i in 7 until 10) {
            if (it[i] == 'L') {
                maxCol = (minCol + maxCol) / 2
            } else {
                minCol = ((minCol + maxCol) / 2) + 1
            }
        }

        val currentCol = if (it[9] == 'L') minCol else maxCol
        planIds.add(currentRow * 8 + currentCol)
    }

    val freeSpot = List(889) { i -> i + 1 }.minus(planIds)

    var mySeat = 0
    for (i in freeSpot.indices) {
        if (freeSpot[i] + 1 != freeSpot[i + 1]) {
            mySeat = freeSpot[i + 1]
            break
        }
    }
    return mySeat
}