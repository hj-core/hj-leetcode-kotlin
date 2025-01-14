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
        // The first visit to value i (from either A or B) set the bit at position i to 1,
        // and the second visit reset it to 0.
        var parity = 0L
        var common = 0
        val result = IntArray(A.size) { 0 }
        for (i in A.indices) {
            val bitA = 1L shl A[i]
            parity = parity xor bitA
            if (parity and bitA == 0L) {
                common++
            }

            val bitB = 1L shl B[i]
            parity = parity xor bitB
            if (parity and bitB == 0L) {
                common++
            }

            result[i] = common
        }
        return result
    }
}
