package com.hj.leetcode.kotlin.problem3876

/**
 * LeetCode page: [3876. Construct Uniform Parity Array II](https://leetcode.com/problems/construct-uniform-parity-array-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums1.
    fun uniformArray(nums1: IntArray): Boolean {
        var orNums = nums1[0]
        var minNum = nums1[0]
        for (i in 1..<nums1.size) {
            orNums = orNums or nums1[i]
            minNum = minOf(minNum, nums1[i])
        }

        return orNums xor minNum and 1 == 0 // orNums and 1 == 0 || minNum and 1 == 1
    }
}
