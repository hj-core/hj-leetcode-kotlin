package com.hj.leetcode.kotlin.problem2818

import kotlin.math.sqrt

/**
 * LeetCode page: [2818. Apply Operations to Maximize Score](https://leetcode.com/problems/apply-operations-to-maximize-score/);
 */
class Solution2 {
    // Complexity:
    // Time O(sqrt(M)*LogLog(sqrt(M))+N*(LogM+sqrt(M))+NLogN+k) and Space O(N+sqrt(M))
    // where N is the length of nums and M is the maximum element of nums.
    fun maximumScore(
        nums: List<Int>,
        k: Int,
    ): Int {
        val countSubarrays = countEachIndexAsDominant(nums)
        val sortedIndices = nums.indices.sortedByDescending { nums[it] }
        var result = 1L
        var remainOps = k.toLong()
        val mod = 1_000_000_007

        for (i in sortedIndices) {
            val ops = minOf(remainOps, countSubarrays[i])
            remainOps -= ops
            result *= nums[i].toLong().modPow(mod, ops.toInt())
            result %= mod

            if (remainOps == 0L) {
                break
            }
        }
        return result.toInt()
    }

    // countEachIndexAsDominant computes the number of subarrays that have nums[i] as the first highest
    // prime score element for each index i.
    private fun countEachIndexAsDominant(nums: List<Int>): LongArray {
        val scores = computePrimeScores(nums) // scores[i]::= the prime score of nums[i]
        val result = LongArray(scores.size)
        val monoStack = mutableListOf(-1)

        for (i in scores.indices) {
            while (monoStack.size > 1 && scores[i] > scores[monoStack.last()]) {
                /* [-1, ..., prev] ---    mid    ---     i
                 *     (stack)          (popped)     (current)
                 *
                 * nums[mid] is the first highest prime score element in the range (prev, i)
                 */
                val mid = monoStack.removeLast()
                result[mid] = 1L * (i - mid) * (mid - monoStack.last())
            }
            monoStack.add(i)
        }

        while (monoStack.size > 1) {
            val mid = monoStack.removeLast()
            result[mid] = 1L * (scores.size - mid) * (mid - monoStack.last())
        }
        return result
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
