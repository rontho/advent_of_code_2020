import kotlin.math.abs

fun day12Part1(input: MutableList<String>): Int {
    var direction = "E"
    var currentX = 0
    var currentY = 0
    val cardinal = listOf("E", "S", "W", "N")

    println("x = $currentX / y = $currentY / direction = $direction")

    input.forEach {
        var instruction = it.filter { c -> !c.isDigit() }
        val value = it.filter { c -> c.isDigit() }.toInt()
        println("$instruction : $value")

        if (instruction == "F") instruction = direction
        when (instruction) {
            "R" -> {
                val next = (cardinal.indexOf(direction) + (value / 90)) % 4
                direction = cardinal[next]
            }
            "L" -> {
                val next = (cardinal.indexOf(direction) + ((360 - value) / 90)) % 4
                direction = cardinal[next]
            }
            "N" -> {
                currentY += value
            }
            "S" -> {
                currentY -= value
            }
            "E" -> {
                currentX += value
            }
            "W" -> {
                currentX -= value
            }
        }

        println("x = $currentX / y = $currentY / direction = $direction")
    }
    return abs(currentX) + abs(currentY)
}

fun day12Part2(input: MutableList<String>): Int {
    var currentX = 0
    var currentY = 0
    var directionX = "E"
    var directionY = "N"

    var wayPointX = 10
    var wayPointY = 1

    val cardinal = listOf("E", "S", "W", "N")

    println("x = $currentX / y = $currentY")
    println("wpx = $wayPointX / wpy = $wayPointY / direction = $directionX _ $directionY")

    input.forEach {
        val instruction = it.filter { c -> !c.isDigit() }
        val value = it.filter { c -> c.isDigit() }.toInt()
        println("$instruction : $value")

        if (instruction == "F") {
            currentX += wayPointX * value
            currentY += wayPointY * value
        }

        when (instruction) {
            "R" -> {
                if (value == 90) {
                    val tmp = wayPointX
                    wayPointX = wayPointY
                    wayPointY = -tmp
                }
                if (value == 180) {
                    wayPointX = -wayPointX
                    wayPointX = -wayPointX
                }

                if (value == 270) {
                    val tmp = wayPointY
                    wayPointY = wayPointX
                    wayPointX = -tmp
                }
            }
            "L" -> {
                if (value == 90) {
                    val tmp = wayPointY
                    wayPointY = wayPointX
                    wayPointX = -tmp
                }
                if (value == 180) {
                    wayPointX = -wayPointX
                    wayPointX = -wayPointX
                }

                if (value == 270) {
                    val tmp = wayPointX
                    wayPointX = wayPointY
                    wayPointY = -tmp
                }
            }
            "N" -> {
                wayPointY += value
            }
            "S" -> {
                wayPointY -= value
            }
            "E" -> {
                wayPointX += value
            }
            "W" -> {
                currentX -= value
            }
        }
        println("x = $currentX / y = $currentY")
        println("wpx = $wayPointX / wpy = $wayPointY / direction = $directionX _ $directionY")
    }
    return abs(currentX) + abs(currentY)
}