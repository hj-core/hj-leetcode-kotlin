package com.hj.leetcode.kotlin.problem2657

/**
 * LeetCode page: [2657. Find the Prefix Common Array of Two Arrays](https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of A and B.
     */
    fun findThePrefixCommonArray(
        A: IntArray,
        B: IntArray,
    ): IntArray {
        var parity = 0L
        var common = 0
        val result = IntArray(A.size) { 0 }
        for (i in A.indices) {
            val bitA = 1L shl A[i]
            if (bitA and parity > 0) {
                common++
            }
            parity = parity xor bitA

            val bitB = 1L shl B[i]
            if (bitB and parity > 0) {
                common++
            }
            parity = parity xor bitB

            result[i] = common
        }
        return result
    }
}
