fun day6Part1(input: MutableList<String>): Int {
    var group = ""
    var count = 0
    input.forEach {
        if (it.isEmpty()) {
            count += group.toCharArray().distinct().size
            group = ""
        } else {
            group += it
        }
    }
    return count + group.toCharArray().distinct().size
}

fun day6Part2(input: MutableList<String>): Int {
    var groups = Array(26) { 0 }
    var count = 0
    var groupsSize = 0
    input.forEach {
        if (it.isEmpty()) {
            count += groups.filter { char -> char == groupsSize }.size
            groups = Array(26) { 0 }
            groupsSize = 0
        } else {
            it.toCharArray().forEach { char ->
                groups[char - 'a'] = groups[char - 'a'] + 1
            }
            groupsSize += 1
        }
    }
    return count + groups.filter { char -> char == groupsSize }.size
}