package com.hj.leetcode.kotlin.problem2438

/**
 * LeetCode page: [2438. Range Product Queries of Powers](https://leetcode.com/problems/range-product-queries-of-powers/);
 */
class Solution {
    private val modulo = 1_000_000_007

    // power2Mods[i]:= 2^i % modulo.
    // 408 is the maximum sum(set bit positions) for n <= 10^9.
    private val power2Mods = IntArray(409)

    init {
        power2Mods[0] = 1
        for (i in 1..<power2Mods.size) {
            power2Mods[i] = (power2Mods[i - 1] shl 1) % modulo
        }
    }

    // Complexity:
    // Time O(M+Log n) and Space O(Log n) where M is the length of
    // queries. This excludes the overhead required to compute and
    // store the power2Modes.
    fun productQueries(
        n: Int,
        queries: Array<IntArray>,
    ): IntArray {
        // Collect the set bit positions in n and compute the prefix
        // sum (exclusive right bound).
        val prefixSum = mutableListOf(0)
        var pos = 0
        while (n shr pos > 0) {
            if ((n shr pos) and 1 > 0) {
                prefixSum.add(pos + prefixSum.last())
            }
            pos++
        }

        // Map the queries to their precomputed power2Mods
        val result = IntArray(queries.size)
        for (i in queries.indices) {
            val (left, right) = queries[i]
            val pow = prefixSum[right + 1] - prefixSum[left]
            result[i] = power2Mods[pow]
        }
        return result
    }
}
