import java.io.File

fun main() {
    println("Day 1 : ${day1(getInput("input/1-1.txt").map { it.toInt() })}")
    println("Day 2 : ${day2(getInput("input/2-1.txt"))}")
    println("Day 2-2 : ${day2Part2(getInput("input/2-1.txt"))}")
    println("Day 3-1 : ${day3(getInput("input/3-1.txt"), 3, 1)}")
    println("Day 3-2 : ${day3Part2(getInput("input/3-1.txt"))}")
    println("Day 4-1 : ${day4(getInput("input/4-1.txt"))}")
    println("Day 4-2 : ${day4Part2(getInput("input/4-1.txt"))}")
}

private fun getInput(file: String): MutableList<String> {
    val input = File(file)
    val inputList = mutableListOf<String>()

    input.forEachLine {
        inputList.add(it)
    }
    return inputList
}

fun day1(input: List<Int>): Int {

    for (i in 0 until input.size - 2) {
        for (j in 1 until input.size - 1) {
            for (k in 2 until input.size) {
                if ((input[i] + input[j] + input[k]) == 2020) {
                    return input[i] * input[j] * input[k]
                }
            }
        }
    }
    return 0
}

fun day2(input: List<String>): Int {
    var numberOfValidPwd = 0
    input.forEach {

        val split: List<String> = it.split(" ")
        val range = split[0].split("-")
        val char = split[1].toCharArray()[0]
        val password = split[2]

        val length = password.filter { c -> c == char }.length
        val min = range[0].toInt()
        val max = range[1].toInt()

        if (length in min..max) ++numberOfValidPwd
        //println("$range - $char - $password")
    }
    return numberOfValidPwd
}

fun day2Part2(input: List<String>): Int {
    var numberOfValidPwd = 0
    input.forEach {

        val split: List<String> = it.split(" ")
        val range = split[0].split("-")
        val char = split[1].toCharArray()[0]
        val password = split[2]

        val min = range[0].toInt() - 1
        val max = range[1].toInt() - 1

        if ((password[min] == char).xor(password[max] == char)) ++numberOfValidPwd
        //println("$range - $char - $password")
    }
    return numberOfValidPwd
}

fun day3(input: List<String>, right: Int, down: Int): Long {

    var numberOfTree = 0L;
    var row = 0;
    var col = 0

    while (row < input.size) {
        if (input[row][col % input[0].length] == '#') numberOfTree++
        row += down
        col += right
    }
    return numberOfTree
}

fun day3Part2(inputList: List<String>): Long = listOf((1 to 1), (3 to 1), (5 to 1), (7 to 1), (1 to 2))
    .map { day3(inputList, it.first, it.second) }
    .reduce { sum, i -> i * sum }

fun day4(input: MutableList<String>): Long {
    val cleanInput = mutableListOf<List<String>>()
    val requiredFields = listOf<String>(
        "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"
    )

    var current = ""
    input.map {
        if (it.isEmpty()) {
            cleanInput.add(current
                .split(" ")
                .joinToString { value ->
                    value
                        .replaceAfterLast(':', "")
                        .dropLast(1)
                        .trim()
                }
                .split(',')
                .dropLast(1)
            )
            current = ""
        } else {
            current += "$it "
        }
    }

    var count = 0L
    cleanInput.forEach {
        println(it)
        if(it.map { it.trim() }.containsAll(requiredFields)) count++
    }

    return count
}

fun day4Part2(input: MutableList<String>): Long {
    val cleanInput = mutableListOf<List<String>>()

    var current = ""
    input.map {
        if (it.isEmpty()) {
            cleanInput.add(current.split(" ").dropLast(1))
            current = ""
        } else {
            current += "$it "
        }
    }

    var count = 0L
    cleanInput.forEach { list ->
        println(list)
        if(areAllValid(list)) count++
    }
    return count
}

fun areAllValid(list: List<String>): Boolean {
    var validity = 0
    list.forEach {
        val (key, value) = it.split(':')
        when(key) {
            "byr" -> if(value.length == 4 && value.toInt() in 1920 .. 2020) ++validity
            "iyr" -> if(value.length == 4 && value.toInt() in 2010 .. 2020) ++validity
            "eyr" -> if(value.length == 4 && value.toInt() in 2020 .. 2030) ++validity
            "hgt" -> {
                if(value.contains("cm")) {
                    if(value.removeSuffix("cm").toInt() in 150 .. 193) ++validity
                }
                else if(value.contains("in")) {
                    if(value.removeSuffix("in").toInt() in 59 .. 76) ++validity
                }
            }
            "hcl" -> if(value[0] == '#' && value.removeRange(0, 1).length == 6 && value.removeRange(0, 1).all {
                        char -> listOf('a', 'b', 'c', 'd', 'e', 'f', '0', '1','2', '3', '4', '5', '6', '7', '8', '9').contains(char)
                }) ++validity
            "ecl" -> if(listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value)) ++validity
            "pid" -> if(value.length == 9 && value.all { char -> char.isDigit() }) ++validity
        }
    }
    return validity == 7
}
