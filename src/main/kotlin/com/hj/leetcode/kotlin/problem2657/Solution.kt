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
        var visitedA = 0L
        var visitedB = 0L
        var common = 0
        val result = IntArray(A.size)
        for (i in A.indices) {
            val bitA = 1L shl A[i]
            if (bitA and visitedB != 0L) {
                common++
            }
            visitedA = visitedA or bitA

            val bitB = 1L shl B[i]
            if (bitB and visitedA != 0L) {
                common++
            }
            visitedB = visitedB or bitB

            result[i] = common
        }
        return result
    }
}
