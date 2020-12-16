fun day9Part1(input: MutableList<String>): Long {
    var invalid = 0L;
    val preamble = 25
    val inputAsInt = input.map { it.toLong() }
    for (i in preamble until inputAsInt.size) {
        if (!findSumPair(inputAsInt[i], inputAsInt.subList(i - preamble, i))) {
            invalid = inputAsInt[i]
        }
    }
    return invalid
}


fun day9Part2(input: MutableList<String>): Long {
    val result = 0L
    val invalidNumber = 1398413738L
    val validInput = input.map { it.toLong() }

    var i = 0
    var j = 1

    while (j < validInput.size) {
        when {
            validInput.subList(i, j + 1).sum() == invalidNumber -> {
                val sorted = validInput.subList(i, j + 1).sorted()
                return sorted[0] + sorted[sorted.size - 1]
            }
            validInput.subList(i, j + 1).sum() < invalidNumber -> j++
            else -> {
                i++
                j = i + 1
            }
        }
    }

    return result
}


fun findSumPair(valueToFind: Long, subList: List<Long>): Boolean {
    for (i in subList.indices) {
        for (j in i until subList.size) {
            if (subList[i] + subList[j] == valueToFind) return true
        }
    }
    return false
}
