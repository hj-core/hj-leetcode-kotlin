package com.hj.leetcode.kotlin.problem1569

/**
 * LeetCode page: [1569. Number of Ways to Reorder Array to Get Same BST](https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/);
 */
class Solution {
    val modulo = 1_000_000_007

    // Complexity:
    // Time O(N^2) and Space O(N) where N is the length of nums.
    fun numOfWays(nums: IntArray): Int {
        val (facMod, invFacMod) = computeFacModAndInvFacMod(nums.size, modulo)
        return computeTotalWays(nums.toList(), facMod, invFacMod) - 1
    }

    private fun computeTotalWays(
        nums: List<Int>,
        facMod: LongArray,
        invFacMod: LongArray,
    ): Int {
        if (nums.size < 2) {
            return 1
        }

        val root = nums[0]
        val small = mutableListOf<Int>()
        val large = mutableListOf<Int>()
        for (num in nums) {
            if (num < root) {
                small.add(num)
            } else if (num > root) {
                large.add(num)
            }
        }

        val factor = combination(small.size + large.size, small.size, facMod, invFacMod, modulo)
        val smallWays = computeTotalWays(small, facMod, invFacMod).toLong()
        val largeWays = computeTotalWays(large, facMod, invFacMod).toLong()
        return (factor * smallWays % modulo * largeWays).mod(modulo)
    }

    private fun combination(
        n: Int,
        k: Int,
        facMod: LongArray,
        invFacMod: LongArray,
        modulo: Int,
    ): Int = (facMod[n] * invFacMod[k] % modulo * invFacMod[n - k]).mod(modulo)

    private fun computeFacModAndInvFacMod(
        n: Int,
        modulo: Int,
    ): Pair<LongArray, LongArray> {
        // facMod[i]:= i! % m
        val facMod = LongArray(n + 1) { 1L }
        for (i in 2..n) {
            facMod[i] = (i * facMod[i - 1]) % modulo
        }

        // invFacMod[i]:= the modular multiplicative inverse of facMod[i] w.r.t m
        val invFacMod = LongArray(n + 1) { 1L }
        invFacMod[n] = powMod(facMod[n].toInt(), modulo - 2, modulo)
        for (i in n downTo 3) {
            invFacMod[i - 1] = (i * invFacMod[i]) % modulo
        }

        return Pair(facMod, invFacMod)
    }

    // Returns b^p mod m.
    private fun powMod(
        base: Int,
        exp: Int,
        modulo: Int,
    ): Long {
        require(0 < base && 0 <= exp && 0 < modulo)
        var result = 1L
        var b = base.toLong()
        var p = exp
        while (p > 0) {
            if (p and 1 == 1) {
                result = (result * b) % modulo
            }
            b = (b * b) % modulo
            p = p ushr 1
        }
        return result
    }
}
