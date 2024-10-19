package com.hj.leetcode.kotlin.problem1545

/**
 * LeetCode page: [1545. Find Kth Bit in Nth Binary String](https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/);
 */
class Solution2 {
    /* Complexity:
     * Time O(n) and Space O(1).
     */
    fun findKthBit(
        n: Int,
        k: Int,
    ): Char {
        var length = (1 shl n) - 1
        require(k in 1..length)
        var index = k - 1
        var flipped = 0

        while (length > 1) {
            val mid = length / 2
            if (index == mid) {
                return '1' - flipped
            }
            if (index > mid) {
                index = mid - (index - mid)
                flipped = flipped xor 1
            }
            length /= 2
        }
        return '0' + flipped
    }
}
