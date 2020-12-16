fun day16Part1(rules: MutableList<String>, myTicket: MutableList<String>, values: MutableList<String>): Int {

    val validNumbers = mutableSetOf<Int>()
    for (i in rules.indices) {
        if (rules[i] == "") break
        val substring = rules[i].substring(rules[i].indexOf(":") + 2)
        val split = substring.split(" or ")
        val first = split[0].split("-").map { it.toInt() }
        val second = split[1].split("-").map { it.toInt() }

        for (j in first[0]..first[1]) {
            validNumbers.add(j)
        }

        for (j in second[0]..second[1]) {
            validNumbers.add(j)
        }
    }
    val sorted = validNumbers.sorted()
    println(sorted)

    val errors = mutableListOf<Int>()

    values.forEach {
        val numbers = it.split(",").map { n -> n.toInt() }
        println(numbers)

        numbers.forEach { num ->
            if (!validNumbers.contains(num)) {
                errors.add(num)
            }
        }
    }

    return errors.sum()
}

fun day16Part2(rules: MutableList<String>, myTicket: MutableList<String>, values: MutableList<String>): Long {

    val validNumbersForRules = mutableMapOf<String, Set<Int>>()
    for (i in rules.indices) {
        if (rules[i] == "") break
        val rule = rules[i].substringBefore(":")
        val substring = rules[i].substring(rules[i].indexOf(":") + 2)
        val split = substring.split(" or ")
        val first = split[0].split("-").map { it.toInt() }
        val second = split[1].split("-").map { it.toInt() }
        val validNumbers = mutableSetOf<Int>()
        for (j in first[0]..first[1]) {
            validNumbers.add(j)
        }
        for (j in second[0]..second[1]) {
            validNumbers.add(j)
        }
        validNumbersForRules[rule] = validNumbers
    }
    println(validNumbersForRules)

    val map = values.map { it.split(",").map { n -> n.trim().toInt() } }
    println(map)

    val validInput = mutableListOf<List<Int>>()
    val validNumbers: Set<Int> = validNumbersForRules.values.flatten().toIntArray().toSet()
    map.forEach { if (it.all { v -> validNumbers.contains(v) }) validInput.add(it) }
    println(validInput)

    val inverseMatrice = mutableListOf<List<Int>>()
    for (i in validInput[0].indices) {
        val list = mutableListOf<Int>()
        validInput.forEach {
            list.add(it[i])
        }
        inverseMatrice.add(list)
    }
    println(inverseMatrice)

    val potentialIndex = mutableMapOf<String, Set<Int>>()
    validNumbersForRules.forEach {
        for (k in inverseMatrice.indices) {
            if (it.value.containsAll(inverseMatrice[k])) {
                println("index $k is ${it.key}")
                if (potentialIndex[it.key] == null) {
                    potentialIndex[it.key] = setOf(k)
                } else {
                    val list = potentialIndex[it.key]?.toMutableSet()
                    list?.add(k)
                    potentialIndex[it.key] = list?.toSet()!!
                }
            }
        }
    }

    potentialIndex.forEach { println(it) }

    val result = mutableMapOf<String, Int>()

    val size = validNumbersForRules.size
    while (result.size < size) {
        potentialIndex.forEach {
            val size1 = it.value.filter { v -> !result.values.contains(v) }.size
            println("{it.key} filtered size is $size1")
            if (size1 == 1) {
                val elementAt = it.value.filter { v -> !result.values.contains(v) }[0]
                result[it.key] = elementAt
                println("add ${it.key} at index $elementAt")
            }
        }
    }
    result.forEach { println(it) }

    val dep = result.filter { it.key.contains("departure") }
    val ticket = myTicket[0].split(',').map { it.toLong() }
    var res = 1L

    dep.forEach {
        res *= ticket[it.value]
    }

    return res
}