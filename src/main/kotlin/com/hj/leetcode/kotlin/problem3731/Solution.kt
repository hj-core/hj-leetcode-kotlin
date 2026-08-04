package com.hj.leetcode.kotlin.problem3731

/**
 * LeetCode page: [3731. Find Missing Elements](https://leetcode.com/problems/find-missing-elements/);
 */
class Solution {
    // Complexity:
    // Time O(N+D) and Space O(D) where N is the length of nums and
    // D is the range of nums.
    fun findMissingElements(nums: IntArray): List<Int> {
        val minElem = nums.min()
        val maxElem = nums.max()
        val visited = BooleanArray(maxElem - minElem + 1)
        for (num in nums) {
            visited[num - minElem] = true
        }

        return (minElem..maxElem).filter { !visited[it - minElem] }
    }
}
