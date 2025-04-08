package com.hj.leetcode.kotlin.problem3396

/**
 * LeetCode page: [3396. Minimum Number of Operations to Make Elements in Array Distinct](https://leetcode.com/problems/minimum-number-of-operations-to-make-elements-in-array-distinct/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun minimumOperations(nums: IntArray): Int {
        val visited = mutableSetOf<Int>()

        for (i in nums.indices.reversed()) {
            if (nums[i] in visited) {
                return 1 + i / 3
            }
            visited.add(nums[i])
        }
        return 0
    }
}
