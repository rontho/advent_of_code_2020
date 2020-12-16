fun day7Part1(input: List<String>): Int {
    val rules = extractRules(input)
    val canContainGold = mutableSetOf<String>()

    rules.forEach { (key, values) ->
        if (values.contains("shiny gold")) canContainGold.add(key)
    }

    var currentSize = 0
    while (canContainGold.size > currentSize) {
        currentSize = canContainGold.size
        rules.forEach { (key, values) ->
            if (values.any { canContainGold.contains(it) }) canContainGold.add(key)
        }
    }
    return canContainGold.size
}

fun day7Part2(input: List<String>): Int {
    val rules = extractRulesWithValue(input)
    return countRules(rules, "shiny gold")
}

private fun extractRules(input: List<String>): MutableMap<String, List<String>> {
    val regex = Regex("[^A-Za-z ]")
    val rules = mutableMapOf<String, List<String>>()
    input.forEach {
        val color = it.substringBefore("bags").trim()
        val listOfContent = it
            .substringAfter("contain")
            .split(',')
            .map { colorString ->
                regex.replace(colorString, "")
                    .replace("bags", "")
                    .replace("bag", "")
                    .trim()
            }

        rules[color] = listOfContent
    }
    println(rules)
    return rules
}
private fun countRules(input: MutableMap<String, Map<String, Int>>, start: String): Int {
    val current = input[start] ?: return 0
    if (current.size == 1 && current.containsKey("no other")) return 0
    var currentCount = 0
    current.forEach { (key, _) ->
        val currentVal: Int = current[key] ?: 0
        currentCount += currentVal + currentVal * countRules(input, key)
    }
    return currentCount
}
private fun extractRulesWithValue(input: List<String>): MutableMap<String, Map<String, Int>> {
    val rules = mutableMapOf<String, Map<String, Int>>()
    input.forEach {
        val color = it.substringBefore("bags").trim()
        val listOfContent = it
            .substringAfter("contain")
            .split(',')

        val map = mutableMapOf<String, Int>()

        listOfContent.forEach { current ->

            current.apply {
                val toto = this.replace(".", "").replace("bags", "").replace("bag", "").trim()
                map[toto.filter { c -> !c.isDigit() }.trim()] =
                    (toto.filter { c -> c.isDigit() }.takeIf { it.isNotEmpty() } ?: "0").toInt()
            }
        }

        rules[color] = map
    }

    println(rules)
    return rules
}

