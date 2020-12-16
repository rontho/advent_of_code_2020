fun day3Part1(input: List<String>, right: Int, down: Int): Long {

    var numberOfTree = 0L
    var row = 0
    var col = 0

    while (row < input.size) {
        if (input[row][col % input[0].length] == '#') numberOfTree++
        row += down
        col += right
    }
    return numberOfTree
}

fun day3Part2(inputList: List<String>): Long = listOf((1 to 1), (3 to 1), (5 to 1), (7 to 1), (1 to 2))
    .map { day3Part1(inputList, it.first, it.second) }
    .reduce { sum, i -> i * sum }
