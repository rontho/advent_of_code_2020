package utils

import org.junit.Test
import kotlin.test.assertEquals

class MatrixTest {
    @Test
    fun `should get corner neighbours`() {
        val input = listOf(
            listOf(0, 1, 2, 3, 4, 5, 6, 7, 8),
            listOf(0, 1, 2, 3, 4, 5, 6, 7, 8),
            listOf(0, 1, 2, 3, 4, 5, 6, 7, 8),
            listOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
        )
        val matrix = Matrix<Int>()

        val neighbours = matrix.findNeighbours(input, 0, 0)
        assertEquals(listOf(1, 0, 1), neighbours)
    }

    @Test
    fun `should get neighbours`() {
        val input = listOf(
            listOf(0, 1, 2, 3, 4, 5, 6, 7, 8),
            listOf(0, 1, 2, 3, 4, 5, 6, 7, 8),
            listOf(0, 1, 2, 3, 4, 5, 6, 7, 8),
            listOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
        )
        val matrix = Matrix<Int>()
        val neighbours = matrix.findNeighbours(input, 2, 5)
        assertEquals(listOf(4, 5, 6, 4, 6, 4, 5, 6), neighbours)
    }
}