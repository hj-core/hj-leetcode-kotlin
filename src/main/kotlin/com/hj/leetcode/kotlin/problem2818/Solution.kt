package com.hj.leetcode.kotlin.problem2818

import kotlin.math.sqrt

/**
 * LeetCode page: [2818. Apply Operations to Maximize Score](https://leetcode.com/problems/apply-operations-to-maximize-score/);
 */
class Solution {
    // Complexity:
    // Time O(sqrt(M)*LogLog(sqrt(M))+N*(LogM+sqrt(M))+NLogN+k) and Space O(N+sqrt(M))
    // where N is the length of nums and M is the maximum element of nums.
    fun maximumScore(
        nums: List<Int>,
        k: Int,
    ): Int {
        val scores = computePrimeScores(nums) // scores[i]::= the prime score of nums[i]

        // minLefts[i] and maxRights[i] are the minimum left and the maximum right that nums[i] remains
        // the first highest prime score element in range (left, i] and [i, right), respectively.
        val minLefts = computeMinLefts(nums, scores)
        val maxRights = computeMaxRights(nums, scores)

        val sortedIndices = nums.indices.sortedByDescending { nums[it] }
        var result = 1L
        var remainOps = k.toLong()
        val mod = 1_000_000_007

        for (i in sortedIndices) {
            // The number of subarrays that has nums[i] as its first highest prime score element
            val count = 1L * (i - minLefts[i]) * (maxRights[i] - i)

            val ops = minOf(remainOps, count)
            remainOps -= ops
            result *= nums[i].toLong().modPow(mod, ops.toInt())
            result %= mod

            if (remainOps == 0L) {
                break
            }
        }
        return result.toInt()
    }

    private fun computePrimeScores(nums: List<Int>): IntArray {
        val maxElem = nums.max()
        val primeList = computePrimeList(sqrt(maxElem.toDouble()).toInt())
        return IntArray(nums.size) { computePrimeScore(nums[it], primeList) }
    }

    // computePrimeList returns the primes up to n in sorted order.
    private fun computePrimeList(n: Int): List<Int> {
        val isPrime = BooleanArray(n + 1) { true }
        val bound = sqrt(n.toDouble()).toInt()

        isPrime[0] = false
        isPrime[1] = false
        for (num in 2..bound) {
            if (isPrime[num]) {
                for (multiple in (num * num)..n step num) {
                    isPrime[multiple] = false
                }
            }
        }
        return isPrime.indices.filter { isPrime[it] }
    }

    // computePrimeScore returns the prime score of x.
    // Clients must ensure that the primeList covers all the primes up to sqrt(x).
    private fun computePrimeScore(
        x: Int,
        primeList: List<Int>,
    ): Int {
        var result = 0
        var remain = x

        for (prime in primeList) {
            if (prime > remain) {
                break
            }
            if (remain % prime == 0) {
                result++
                while (remain % prime == 0) {
                    remain /= prime
                }
            }
        }
        if (remain > 1) {
            result++
        }
        return result
    }

    // computeMinLefts returns the minimum left index for each index i such that nums[i] remains
    // the first highest prime score element in the range (left, i].
    private fun computeMinLefts(
        nums: List<Int>,
        scores: IntArray,
    ): IntArray {
        val result = IntArray(nums.size)
        val monoStack = mutableListOf<Int>()

        for (i in nums.indices) {
            while (monoStack.isNotEmpty() && scores[monoStack.last()] < scores[i]) {
                monoStack.removeLast()
            }
            result[i] = if (monoStack.isNotEmpty()) monoStack.last() else -1
            monoStack.add(i)
        }
        return result
    }

    // computeMaxRights returns the maximum right index for each index i such that nums[i] remains
    // the first highest prime score element in the range [i, right).
    private fun computeMaxRights(
        nums: List<Int>,
        scores: IntArray,
    ): IntArray {
        val result = IntArray(nums.size)
        val monoStack = mutableListOf<Int>()

        for (i in nums.indices.reversed()) {
            while (monoStack.isNotEmpty() && scores[monoStack.last()] <= scores[i]) {
                monoStack.removeLast()
            }
            result[i] = if (monoStack.isNotEmpty()) monoStack.last() else nums.size
            monoStack.add(i)
        }
        return result
    }

    // Long.modPow computes (this^pow) % mod.
    private fun Long.modPow(
        mod: Int,
        pow: Int,
    ): Long {
        if (pow == 1) {
            return this % mod
        }

        val modSquare = (this * this) % mod
        var result = modSquare.modPow(mod, pow / 2)
        if (pow % 2 == 1) {
            result = (result * this) % mod
        }
        return result
    }
}
