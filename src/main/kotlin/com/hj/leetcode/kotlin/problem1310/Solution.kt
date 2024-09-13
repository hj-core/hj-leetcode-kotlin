package com.hj.leetcode.kotlin.problem1310

/**
 * LeetCode page: [1310. XOR Queries of a Subarray](https://leetcode.com/problems/xor-queries-of-a-subarraye);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(N) where N is the size of arr and
     * M is the size of queries.
     */
    fun xorQueries(
        arr: IntArray,
        queries: Array<IntArray>,
    ): IntArray {
        val suffixXor = suffixXor(arr)
        return IntArray(queries.size) { i ->
            val (left, right) = queries[i]
            suffixXor[left] xor suffixXor[right + 1]
        }
    }

    private fun suffixXor(arr: IntArray): IntArray {
        val result = IntArray(arr.size + 1)
        for (i in arr.indices.reversed()) {
            result[i] = arr[i] xor result[i + 1]
        }
        return result
    }
}
