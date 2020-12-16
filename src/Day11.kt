class Day11 {
    fun day11Part1(input: MutableList<String>): Int {
        println(input)
        val charInput = input.map { it.toCharArray() }.toMutableList()

        while (true) {
            val toOccupied = mutableListOf<Pair<Int, Int>>()
            val toFree = mutableListOf<Pair<Int, Int>>()

            for (row in charInput.indices) {
                for (col in charInput[0].indices) {

                    if (charInput[row][col] == 'L') {
                        if (adjacentSeatAreFree(charInput, row, col, 8)) {
                            toOccupied.add(row to col)
                        }
                    }

                    if (charInput[row][col] == '#') {
                        if (adjacentSeatAreOccupied(charInput, row, col, 4)) {
                            toFree.add(row to col)
                        }
                    }
                }
            }

            if (toOccupied.size == 0 && toFree.size == 0) break

            for (row in charInput.indices) {
                for (col in charInput[0].indices) {
                    if (toFree.contains(row to col)) charInput[row][col] = 'L'
                    if (toOccupied.contains(row to col)) charInput[row][col] = '#'
                }
            }
        }

        charInput.forEach { println(it) }
        return charInput.map { it.filter { char -> char == '#' }.size }.sum()
    }

    private fun adjacentSeatAreFree(input: List<CharArray>, row: Int, col: Int, validation: Int): Boolean {
        val boolean = mutableListOf<Boolean>()
        boolean.add(if (row - 1 < 0) true else input[row - 1][col] != '#')
        boolean.add(if (row + 1 >= input.size) true else input[row + 1][col] != '#')
        boolean.add(if (col - 1 < 0) true else input[row][col - 1] != '#')
        boolean.add(if (col + 1 >= input[row].size) true else input[row][col + 1] != '#')
        boolean.add(if (row + 1 >= input.size || col - 1 < 0) true else input[row + 1][col - 1] != '#')
        boolean.add(if (row + 1 >= input.size || col + 1 >= input[row].size) true else input[row + 1][col + 1] != '#')
        boolean.add(if (row - 1 < 0 || col + 1 >= input[row].size) true else input[row - 1][col + 1] != '#')
        boolean.add(if (row - 1 < 0 || col - 1 < 0) true else input[row - 1][col - 1] != '#')
        return boolean.filter { it }.size >= validation
    }

    private fun adjacentSeatAreOccupied(input: List<CharArray>, row: Int, col: Int, validation: Int): Boolean {
        val boolean = mutableListOf<Boolean>()
        boolean.add(if (row - 1 < 0) false else input[row - 1][col] == '#')
        boolean.add(if (row + 1 >= input.size) false else input[row + 1][col] == '#')
        boolean.add(if (col - 1 < 0) false else input[row][col - 1] == '#')
        boolean.add(if (col + 1 >= input[row].size) false else input[row][col + 1] == '#')
        boolean.add(if (row + 1 >= input.size || col - 1 < 0) false else input[row + 1][col - 1] == '#')
        boolean.add(if (row + 1 >= input.size || col + 1 >= input[row].size) false else input[row + 1][col + 1] == '#')
        boolean.add(if (row - 1 < 0 || col + 1 >= input[row].size) false else input[row - 1][col + 1] == '#')
        boolean.add(if (row - 1 < 0 || col - 1 < 0) false else input[row - 1][col - 1] == '#')
        return boolean.filter { it }.size >= validation
    }

    fun day11Part2(input: MutableList<String>): Int {
        println(input)
        val charInput = input.map { it.toCharArray() }.toMutableList()

        while (true) {
            val toOccupied = mutableListOf<Pair<Int, Int>>()
            val toFree = mutableListOf<Pair<Int, Int>>()

            for (row in charInput.indices) {
                for (col in charInput[0].indices) {

                    if (charInput[row][col] == 'L') {
                        if (adjacentSeatAreFree2(charInput, row, col, 8)) {
                            toOccupied.add(row to col)
                        }
                    }

                    if (charInput[row][col] == '#') {
                        if (adjacentSeatAreOccupied2(charInput, row, col, 5)) {
                            toFree.add(row to col)
                        }
                    }
                }
            }

            if (toOccupied.size == 0 && toFree.size == 0) break

            charInput.forEach { println(it) }
            println('*')
            println('*')
            println('*')
            for (row in charInput.indices) {
                for (col in charInput[0].indices) {
                    if (toFree.contains(row to col)) charInput[row][col] = 'L'
                    if (toOccupied.contains(row to col)) charInput[row][col] = '#'
                }
            }
        }

        charInput.forEach { println(it) }
        return charInput.map { it.filter { char -> char == '#' }.size }.sum()
    }

    fun adjacentSeatAreFree2(input: List<CharArray>, row: Int, col: Int, validation: Int): Boolean {
        val boolean = mutableListOf<Boolean>(true, true, true, true, true, true, true, true)
        for (i in row - 1 downTo 0) {
            if (input[i][col] != '.') {
                boolean.removeAt(0)
                boolean.add(0, input[i][col] == 'L')
                break
            }
        }
        for (i in row + 1 until input.size) {
            if (input[i][col] != '.') {
                boolean.removeAt(1)
                boolean.add(1, input[i][col] == 'L')
                break
            }
        }
        for (i in col - 1 downTo 0) {
            if (input[row][i] != '.') {
                boolean.removeAt(2)
                boolean.add(2, input[row][i] == 'L')
                break
            }
        }
        for (i in col + 1 until input[row].size) {
            if (input[row][i] != '.') {
                boolean.removeAt(3)
                boolean.add(3, input[row][i] == 'L')
                break
            }
        }
        for (i in row + 1 until input.size) {
            for (j in col - 1 downTo 0) {
                if (input[i][j] != '.') {
                    boolean.removeAt(4)
                    boolean.add(4, input[i][j] == 'L')
                    break
                }
            }
        }
        for (i in row + 1 until input.size) {
            for (j in col + 1 until input[row].size) {
                if (input[i][j] != '.') {
                    boolean.removeAt(5)
                    boolean.add(5, input[i][j] == 'L')
                    break
                }
            }
        }
        for (i in row - 1 downTo 0) {
            for (j in col + 1 until input[row].size) {
                if (input[i][j] != '.') {
                    boolean.removeAt(6)
                    boolean.add(6, input[i][j] == 'L')
                }
            }
        }
        for (i in row - 1 downTo 0) {
            for (j in col - 1 downTo 0) {
                if (input[i][j] != '.') {
                    boolean.removeAt(7)
                    boolean.add(7, input[i][j] == 'L')
                }
            }
        }

        return boolean.filter { it }.size >= validation
    }

    fun adjacentSeatAreOccupied2(input: List<CharArray>, row: Int, col: Int, validation: Int): Boolean {
        val boolean = mutableListOf<Boolean>(false, false, false, false, false, false, false, false)
        for (i in row - 1 downTo 0) {
            if (input[i][col] != '.') {
                boolean.removeAt(0)
                boolean.add(0, input[i][col] == '#')
                break
            }
        }
        for (i in row + 1 until input.size) {
            if (input[i][col] != '.') {
                boolean.removeAt(1)
                boolean.add(1, input[i][col] == '#')
                break
            }
        }
        for (i in col - 1 downTo 0) {
            if (input[row][i] != '.') {
                boolean.removeAt(2)
                boolean.add(2, input[row][i] == '#')
                break
            }
        }
        for (i in col + 1 until input[row].size) {
            if (input[row][i] != '.') {
                boolean.removeAt(3)
                boolean.add(3, input[row][i] == '#')
                break
            }
        }
        for (i in row + 1 until input.size) {
            for (j in col - 1 downTo 0) {
                if (input[i][j] != '.') {
                    boolean.removeAt(4)
                    boolean.add(4, input[i][j] == '#')
                    break
                }
            }
        }
        for (i in row + 1 until input.size) {
            for (j in col + 1 until input[row].size) {
                if (input[i][j] != '.') {
                    boolean.removeAt(5)
                    boolean.add(5, input[i][j] == '#')
                    break
                }
            }
        }
        for (i in row - 1 downTo 0) {
            for (j in col + 1 until input[row].size) {
                if (input[i][j] != '.') {
                    boolean.removeAt(6)
                    boolean.add(6, input[i][j] == '#')
                }
            }
        }
        for (i in row - 1 downTo 0) {
            for (j in col - 1 downTo 0) {
                if (input[i][j] != '.') {
                    boolean.removeAt(7)
                    boolean.add(7, input[i][j] == '#')
                }
            }
        }
        return boolean.filter { it }.size >= validation
    }
}