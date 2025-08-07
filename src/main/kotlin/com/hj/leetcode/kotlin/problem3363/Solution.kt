package com.hj.leetcode.kotlin.problem3363

/**
 * LeetCode page: [3363. Find the Maximum Number of Fruits Collected](https://leetcode.com/problems/find-the-maximum-number-of-fruits-collected/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(N) where N is the height and
    // width of fruits.
    fun maxCollectedFruits(fruits: Array<IntArray>): Int = solveChild1(fruits) + solveChild2n3(fruits)

    // Child 1 starts from (0, 0). He can only move along the
    // diagonal.
    private fun solveChild1(fruits: Array<IntArray>): Int = fruits.indices.sumOf { fruits[it][it] }

    // Child 2 starts from (0, n-1). He can move above or along
    // the diagonal. Since Child 1 has already collected all the
    // fruits in the diagonal, we only consider Child 2 moving
    // above the diagonal, excluding the goal cell as well.
    //
    // Child 3 follows the symmetric version of child 2. Therefore,
    // we handle them together.
    private fun solveChild2n3(fruits: Array<IntArray>): Int {
        val n = fruits.size
        // dp2[j]@i:= the maximum fruits child 2 can collect
        // when he reaches cell(i, n-1-j).
        val dp2 = IntArray(n / 2 + 1)

        // dp3[j]@i:= the maximum fruits child 3 can collect
        // when he reaches cell(n-1-j, i).
        val dp3 = IntArray(n / 2 + 1)

        dp2[0] = fruits[0][n - 1]
        dp3[0] = fruits[n - 1][0]
        for (i in 1..<(n - 1)) {
            val maxJ = minOf(i, n - 2 - i)
            var tmp2 = dp2[0] // Previous dp2[j-1]
            var tmp3 = dp3[0] // Previous dp3[j-1]
            for (j in 0..maxJ) {
                dp2[j] = fruits[i][n - 1 - j] + maxOf(tmp2, dp2[j], dp2[j + 1]).also { tmp2 = dp2[j] }
                dp3[j] = fruits[n - 1 - j][i] + maxOf(tmp3, dp3[j], dp3[j + 1]).also { tmp3 = dp3[j] }
            }
        }
        return dp2[0] + dp3[0]
    }
}
