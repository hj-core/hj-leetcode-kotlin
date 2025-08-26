/**
 * LeetCode page: [3000. Maximum Area of Longest Diagonal Rectangle](https://leetcode.com/problems/maximum-area-of-longest-diagonal-rectangle/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of dimensions.
    fun areaOfMaxDiagonal(dimensions: Array<IntArray>): Int =
        dimensions
            .maxWith(compareBy(::calcDiagonalSquare).thenBy(::calcArea))
            .let(::calcArea)

    private fun calcDiagonalSquare(rectangle: IntArray): Int = rectangle[0] * rectangle[0] + rectangle[1] * rectangle[1]

    private fun calcArea(rectangle: IntArray): Int = rectangle[0] * rectangle[1]
}
