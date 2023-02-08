package com.hj.leetcode.kotlin.problem45

/**
 * LeetCode page: [45. Jump Game II](https://leetcode.com/problems/jump-game-ii/);
 */
class Solution2 {
    /* Algorithm: (By Dynamic Programming)
     * 1. SubProblem:
     *    let X(i) be the minimum jumps if start at index i. Use array Dp to store the result
     *    of each sub problem, i.e. Dp[i] = X(i);
     * 2. Relation:
     *    X(i) = 1 + min { Dp[k] | k = i+1, i+2, .., i+nums[i] } for i = 0, 1, .., nums.lastIndex - 1;
     * 3. Topological Order:
     *    Solve X(i) from last to first index;
     * 4. Base case:
     *    X(nums.lastIndex) = 0
     * 5. Original Problem:
     *    X(0)
     * 6. Time complexity:
     *    There are |nums| sub problems, each has complexity O(K) where K is the maximum possible jump length,
     *    thus O(|nums| * K);
     */

    /* Complexity:
     * Time O(|nums| * K) and Space O(|nums|) where K is the maximum possible jump length;
     */
    fun jump(nums: IntArray): Int {
        val dp = IntArray(nums.size)
        val valueWhenCannotReach = nums.size // Special value if the last index can not be reached

        for (i in dp.lastIndex - 1 downTo 0) {
            val step = nums[i]
            if (step == 0) {
                dp[i] = valueWhenCannotReach
                continue
            }

            val furthest = (i + step).coerceAtMost(nums.lastIndex)
            val subResult = 1 + dp.min(i + 1..furthest)
            dp[i] = subResult
        }

        check(dp[0] < nums.size) {
            "Cannot reach last index although is guaranteed in the problem statement."
        }
        return dp[0]
    }

    private fun IntArray.min(indexRange: IntRange): Int {
        var min = this[indexRange.first]
        for (index in indexRange) {
            if (this[index] < min) {
                min = this[index]
            }
        }
        return min
    }
}