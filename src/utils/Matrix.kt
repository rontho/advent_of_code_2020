package utils

import kotlin.math.max
import kotlin.math.min

class Matrix<T> {

    fun findNeighbours(matrix: List<List<T>>, row: Int, col: Int): List<T> {

        val neighbors = mutableListOf<T>()
        val rowStart = max(row - 1, 0)
        val rowFinish = min(row + 1, matrix.size - 1)
        val colStart = max(col - 1, 0)
        val colFinish = min(col + 1, matrix[0].size - 1)

        for (curRow in rowStart..rowFinish) {
            for (curCol in colStart..colFinish) {
                println("$curRow -- $curCol")
                if(row == curRow && col == curCol) continue
                neighbors.add(matrix[curRow][curCol])
            }
        }

        return neighbors
    }
}