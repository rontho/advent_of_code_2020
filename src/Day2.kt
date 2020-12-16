fun day2Part1(input: List<String>): Int {
    var numberOfValidPwd = 0
    input.forEach {

        val split: List<String> = it.split(" ")
        val range: List<String> = split[0].split("-")
        val char: Char = split[1].toCharArray()[0]
        val password: String = split[2]

        val length = password.filter { c -> c == char }.length
        val min = range[0].toInt()
        val max = range[1].toInt()

        if (length in min..max) ++numberOfValidPwd
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

        if ((password[min] == char).xor(password[max] == char)) {
            ++numberOfValidPwd
        }
    }
    return numberOfValidPwd
}
