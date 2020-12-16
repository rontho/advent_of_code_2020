package utils

import java.io.File

fun Int.toInput(): MutableList<String> {
    val input = File("input/$this.txt")
    val inputList = mutableListOf<String>()
    input.forEachLine {
        inputList.add(it)
    }
    return inputList
}