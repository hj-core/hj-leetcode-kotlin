package com.hj.leetcode.kotlin.problem2425

/**
 * LeetCode page: [2425. Bitwise XOR of All Pairings](https://leetcode.com/problems/bitwise-xor-of-all-pairings/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(1)
     * where N and M are the lengths of nums1 and nums2, respectively.
     */
    fun xorAllNums(
        nums1: IntArray,
        nums2: IntArray,
    ): Int {
        var result = 0

        if (nums2.size % 2 == 1) {
            result = result xor nums1.reduce(Int::xor)
        }
        if (nums1.size % 2 == 1) {
            result = result xor nums2.reduce(Int::xor)
        }
        return result
    }
}
