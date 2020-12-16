fun day8Part1(input: MutableList<String>): Int {
    val alreadyVisited = mutableSetOf<Int>()
    var accumulator = 0

    var i = 0
    while (i < input.size) {
        if (alreadyVisited.contains(i)) break
        val (operator, value) = input[i].split(" ")
        alreadyVisited.add(i)
        when (operator) {
            "nop" -> i++
            "acc" -> {
                accumulator += value.toInt()
                i++
            }
            "jmp" -> i += value.toInt()
        }
    }
    return accumulator
}

fun day8Part2(input: MutableList<String>): Int {

    var alreadyVisited = mutableSetOf<Int>()
    var accumulator = 0

    for (j in 0 until input.size) {
        var i = 0
        var hasBreak = false
        while (i < input.size) {
            if (alreadyVisited.contains(i)) {
                hasBreak = true
                break
            }
            var (operator, value) = input[i].split(" ")
            if (operator == "jmp" && i == j) operator = "nop"
            alreadyVisited.add(i)
            when (operator) {
                "nop" -> i++
                "acc" -> {
                    accumulator += value.toInt()
                    i++
                }
                "jmp" -> i += value.toInt()
            }
        }

        if (!hasBreak) {
            println("right answer is $accumulator")
        }

        alreadyVisited = mutableSetOf()
        hasBreak = false
        accumulator = 0
    }

    return accumulator
}