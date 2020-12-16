fun day4Part1(input: MutableList<String>): Long {
    var current = ""
    val cleanInput = mutableListOf<List<String>>()
    val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    input.forEach {
        if (it.isEmpty()) {
            cleanInput.add(current
                .split(" ")
                .joinToString { value ->
                    value
                        .replaceAfterLast(':', "")
                        .dropLast(1)
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
    cleanInput.forEach { value -> if (value.map { it.trim() }.containsAll(requiredFields)) count++ }
    return count
}

fun day4Part2(input: MutableList<String>): Long {
    var current = "";
    var count = 0L;
    val cleanInput = mutableListOf<List<String>>()

    input.forEach {
        if (it.isEmpty()) {
            cleanInput.add(current.split(" ").dropLast(1))
            current = ""
        } else {
            current += "$it "
        }
    }

    cleanInput.forEach { list -> if (areAllValid(list)) count++ }
    return count
}

fun areAllValid(list: List<String>): Boolean {
    var validity = 0
    list.forEach {
        val (key, value) = it.split(':')
        when (key) {
            "byr" -> if (value.length == 4 && value.toInt() in 1920..2020) ++validity
            "iyr" -> if (value.length == 4 && value.toInt() in 2010..2020) ++validity
            "eyr" -> if (value.length == 4 && value.toInt() in 2020..2030) ++validity
            "hgt" -> {
                if (value.contains("cm")) {
                    if (value.removeSuffix("cm").toInt() in 150..193) ++validity
                } else if (value.contains("in")) {
                    if (value.removeSuffix("in").toInt() in 59..76) ++validity
                }
            }
            "hcl" -> if (value[0] == '#' && value.removeRange(0, 1).length == 6 && value.removeRange(0, 1)
                    .all { char ->
                        listOf(
                            'a', 'b', 'c', 'd', 'e', 'f', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
                        ).contains(char)
                    }
            ) ++validity
            "ecl" -> if (listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value)) ++validity
            "pid" -> if (value.length == 9 && value.all { char -> char.isDigit() }) ++validity
        }
    }
    return validity == 7
}