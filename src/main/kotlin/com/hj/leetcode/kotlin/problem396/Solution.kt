package com.hj.leetcode.kotlin.problem396

/**
 * LeetCode page: [396. Rotate Function](https://leetcode.com/problems/rotate-function/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun maxRotateFunction(nums: IntArray): Int {
        val (sum, f0) =
            nums.foldIndexed(0 to 0) { i, (sum, f0), num ->
                (sum + num) to (f0 + i * num)
            }

        var f = f0
        var maxF = f0
        for (i in nums.lastIndex downTo 1) {
            f += sum - nums.size * nums[i]
            maxF = maxOf(maxF, f)
        }

        return maxF
    }
}
