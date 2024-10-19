package com.hj.leetcode.kotlin.problem1545

/**
 * LeetCode page: [1545. Find Kth Bit in Nth Binary String](https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(n).
     */
    fun findKthBit(
        n: Int,
        k: Int,
    ): Char = '0' + findBit(n, k - 1)

    private fun findBit(
        n: Int,
        index: Int,
    ): Int {
        val length = (1 shl n) - 1
        require(index in 0..<length)

        if (n == 1) {
            return 0
        }
        val mid = length / 2
        return when {
            index < mid -> findBit(n - 1, index)
            index > mid -> 1 xor findBit(n - 1, mid - (index - mid))
            else -> 1
        }
    }
}
