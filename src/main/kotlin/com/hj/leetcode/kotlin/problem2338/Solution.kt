package com.hj.leetcode.kotlin.problem2338

/**
 * LeetCode page: [2338. Count the Number of Ideal Arrays](https://leetcode.com/problems/count-the-number-of-ideal-arrays/);
 */
class Solution {
    fun idealArrays(
        n: Int,
        maxValue: Int,
    ): Int {
        val mod = 1_000_000_007
        val (isPrime, primeList) = computePrimeList(maxValue)
        val maxTotalFactors = computeMaxTotalFactors(maxValue)
        val multiplierTable = computeMultiplierTable(n, maxTotalFactors + 1, mod, isPrime, primeList)
        var result = 1 // Initialize with lastValue=1

        for (lastValue in 2..maxValue) {
            var subResult = 1L
            if (isPrime[lastValue]) {
                subResult = n.toLong()
            } else {
                for ((_, power) in factorize(lastValue, isPrime, primeList)) {
                    subResult = (subResult * multiplierTable[power]) % mod
                }
            }
            result = (result + subResult.toInt()) % mod
        }
        return result
    }

    // computeMaxTotalFactors returns the maximum amount of factors for numbers 1 to maxValue.
    private fun computeMaxTotalFactors(maxValue: Int): Int {
        var result = 0
        var remaining = maxValue
        while (remaining > 1) {
            remaining = remaining shr 1
            result++
        }
        return result
    }

    // computeMultiplierTable returns a length-long array such that each index k has
    // a value of (n+k-1)C(n-1) % mod.
    private fun computeMultiplierTable(
        n: Int,
        length: Int,
        mod: Int,
        isPrime: BooleanArray,
        primeList: List<Int>,
    ): IntArray {
        val result = IntArray(length)
        result[0] = 1

        val pendingFactors = mutableMapOf<Int, Int>()
        for (num in 2..<length) {
            for ((factor, power) in factorize(num, isPrime, primeList)) {
                pendingFactors[factor] = (pendingFactors[factor] ?: 0) + power
            }
        }

        var multiplierL = 1L
        var multiplierR = 1L

        for (k in 1..<length) {
            var extraL = 1L
            var extraR = n + k - 1

            for ((factor, power) in pendingFactors) {
                var remainingPower = power
                while (remainingPower > 0 && extraR > 1 && extraR % factor == 0) {
                    remainingPower -= 1
                    extraL *= factor
                    extraR /= factor
                }
                pendingFactors[factor] = remainingPower
            }

            multiplierL = multiplierL * extraL / k
            multiplierR = (multiplierR * extraR) % mod
            result[k] = ((multiplierL * multiplierR) % mod).toInt()
        }
        return result
    }

    // factorize factorizes the num and returns a list of (factor, power) pairs.
    private fun factorize(
        num: Int,
        isPrime: BooleanArray,
        primeList: List<Int>,
    ): List<Pair<Int, Int>> {
        if (isPrime[num]) {
            return listOf(num to 1)
        }
        val result = mutableListOf<Pair<Int, Int>>()
        var remaining = num
        for (prime in primeList) {
            var power = 0
            while (remaining % prime == 0) {
                remaining /= prime
                power += 1
            }
            if (power != 0) {
                result.add(prime to power)
            }
            if (remaining < prime) {
                break
            }
        }
        check(remaining == 1)
        return result
    }

    // computePrimeList returns an indicator array and a list of primes up to n.
    private fun computePrimeList(n: Int): Pair<BooleanArray, List<Int>> {
        val isPrimes = BooleanArray(n + 1) { true }
        val primeList = mutableListOf<Int>()

        for (i in 2..n) {
            if (isPrimes[i]) {
                primeList.add(i)
                for (j in i * i..n step i) {
                    isPrimes[j] = false
                }
            }
        }
        return isPrimes to primeList
    }
}
