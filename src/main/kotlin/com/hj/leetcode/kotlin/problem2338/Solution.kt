package com.hj.leetcode.kotlin.problem2338

/**
 * LeetCode page: [2338. Count the Number of Ideal Arrays](https://leetcode.com/problems/count-the-number-of-ideal-arrays/);
 */
class Solution {
    fun idealArrays(
        n: Int,
        maxValue: Int,
    ): Int {
        // The ideas are
        // 1. There are (n+m-1)C(n-1) ways to distribute m apples into n bags.
        // 2. The elements in a good array is determined by its last value.
        // 3. Let k_i be the arr[i]/arr[i-1] and k_0 equals arr[0], the sequence product of k_i
        //    equals to the last value.
        // 4. Distribute the prime factors of last value separately.
        val mod = 1_000_000_007
        val (isPrime, primeList) = computePrimes(maxValue)
        val maxTotalFactors = computeMaxTotalFactors(maxValue)
        val multiplierTable = computeMultiplierTable(n, maxTotalFactors + 1, mod, isPrime, primeList)
        var result = 1 // The initial value include the case where last value equals 1

        for (lastValue in 2..maxValue) {
            if (isPrime[lastValue]) {
                result = (result + n) % mod
                continue
            }

            var subResult = 1L
            for ((_, power) in computeFactorization(lastValue, isPrime, primeList)) {
                subResult = (subResult * multiplierTable[power]) % mod
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

        // To handle the denominator in the combination expression, we split the expression
        // into the product of multiplierL and multiplierR, where multiplierL takes care of the
        // denominator, i.e., the factorial of k, and multiplierR takes care of the remainder.
        val reservedFactors = mutableMapOf<Int, Int>()
        for (num in 2..<length) {
            for ((factor, power) in computeFactorization(num, isPrime, primeList)) {
                reservedFactors[factor] = (reservedFactors[factor] ?: 0) + power
            }
        }

        var multiplierL = 1L
        var multiplierR = 1L

        for (k in 1..<length) {
            var extraL = 1L
            var extraR = n + k - 1

            for ((factor, power) in reservedFactors) {
                var remainingPower = power
                while (remainingPower > 0 && extraR > 1 && extraR % factor == 0) {
                    remainingPower -= 1
                    extraL *= factor
                    extraR /= factor
                }
                reservedFactors[factor] = remainingPower
            }

            multiplierL = multiplierL * extraL / k
            multiplierR = (multiplierR * extraR) % mod
            result[k] = ((multiplierL * multiplierR) % mod).toInt()
        }
        return result
    }

    // computeFactorization returns a list of (factor, power) pairs that represent
    // the factorization of num.
    private fun computeFactorization(
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
            if (0 < power) {
                result.add(prime to power)
            }
            if (remaining < prime) {
                break
            }
        }
        check(remaining == 1)
        return result
    }

    // computePrimes returns a list of primes up to n and an indicator array that
    // can be used to query if a number is prime.
    private fun computePrimes(n: Int): Pair<BooleanArray, List<Int>> {
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
