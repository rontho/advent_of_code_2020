import kotlin.math.pow

fun day14Part1(input: MutableList<String>): Long {
    val memory = mutableMapOf<Long, Long>()
    var mask = ""
    input.forEach {
        var addr = 0L
        var value = ""
        if (it.contains("mask")) {
            mask = it.substringAfter("= ")
        } else {
            addr = it.substring(it.indexOf("[") + 1, it.indexOf("]")).toLong()
            value = it.substringAfter("= ")

            val valueAsByte = value.toLong().toString(2).padStart(36, '0').toCharArray()
            for (i in mask.indices) {
                if (mask[i] != 'X') {
                    valueAsByte[i] = mask[i]
                }
            }
            memory[addr] = valueAsByte.joinToString("").toLong(2)
        }
    }
    return memory.values.sum()
}

fun day14Part2(input: MutableList<String>): Long {
    val memory = mutableMapOf<Long, Long>()
    var mask = ""
    input.forEach {
        var addr = 0L
        if (it.contains("mask")) {
            mask = it.substringAfter("= ")
        } else {
            addr = it.substring(it.indexOf("[") + 1, it.indexOf("]")).toLong()
            val value = it.substringAfter("= ").toLong()

            val numberOfX = mask.filter { x -> x == 'X' }.length
            var addrAsByteArray = addr.toString(2).padStart(36, '0').toCharArray()

            for (i in mask.indices) {
                if (mask[i] != '0') {
                    addrAsByteArray[i] = mask[i]
                }
            }

            val initialAddr = addrAsByteArray.copyOf()
            for (i in 0..2.0.pow(numberOfX.toDouble()).toInt()) {
                var turn = 0
                val current = i.toLong().toString(2).padStart(numberOfX, '0').toCharArray()
                for (indexOfX in mask.indices) {
                    if (mask[indexOfX] == 'X') {
                        addrAsByteArray[indexOfX] = current[turn]
                        turn++
                    }
                }
                memory[addrAsByteArray.joinToString("").toLong(2)] = value
                addrAsByteArray = initialAddr.copyOf()
            }
        }
    }
    return memory.values.sum()
}