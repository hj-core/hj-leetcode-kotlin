package com.hj.leetcode.kotlin.problem3343

/**
 * LeetCode page: [3343. Count Number of Balanced Permutations](https://leetcode.com/problems/count-number-of-balanced-permutations/);
 */
class Solution {
    private val module = 1_000_000_007
    private val maxLen = 80

    // combTable[n][i]::= Combination(n+i, n) % module
    private val combTable = Array(1 + maxLen) { IntArray(1 + maxLen) }

    init {
        initCombinationTable()
    }

    private fun initCombinationTable() {
        for (i in combTable[0].indices) {
            combTable[0][i] = 1
        }
        for (n in 1..<combTable.size) {
            combTable[n][0] = 1
            for (i in 1..<combTable[n].size) {
                combTable[n][i] = (combTable[n - 1][i] + combTable[n][i - 1]) % module
            }
        }
    }

    fun countBalancedPermutations(num: String): Int {
        val freq = computeDigitFreq(num)
        val sum = computeDigitSum(freq)
        if (sum and 1 != 0) {
            return 0
        }

        val targetLen = num.length / 2
        val targetSum = sum / 2

        // dp[evenLen][evenSum]@d::= the number of different permutations % module that
        // 1. We use all the digits in num that are less than or equal d.
        // 2. We partition these digits into two groups: odd and even.
        // 3. The even group has a size of evenLen and a sum of evenSum.
        // 4. The value of this dp entry is the different permutations from the odd group times the
        //    different permutations from the even group, and take the module.
        var dp = Array(1 + targetLen) { IntArray(1 + targetSum) }

        // base case where d equals -1
        var totalLen = 0
        var totalSum = 0
        dp[0][0] = 1

        for (d in 0..<10) {
            if (freq[d] == 0) {
                continue
            }

            val newDp = Array(1 + targetLen) { IntArray(1 + targetSum) }

            for (evenLen in dp.size - 1 downTo 0) {
                for (evenSum in dp[evenLen].size - 1 downTo 0) {
                    if (dp[evenLen][evenSum] == 0) {
                        continue
                    }

                    val oldLen = totalLen - evenLen
                    for (f in 0..freq[d]) {
                        val nextEvenLen = evenLen + f
                        val nextEvenSum = evenSum + f * d
                        val nextOddLen = oldLen + freq[d] - f
                        val nextOddSum = totalSum + freq[d] * d - nextEvenSum

                        if (nextEvenLen > targetLen || nextEvenSum > targetSum) {
                            break
                        }
                        if (nextOddLen > num.length - targetLen || nextOddSum > targetSum) {
                            continue
                        }

                        newDp[nextEvenLen][nextEvenSum] +=
                            ((((dp[evenLen][evenSum].toLong() * combTable[evenLen][f]) % module) * combTable[oldLen][freq[d] - f]) % module).toInt()
                        newDp[nextEvenLen][nextEvenSum] %= module
                    }
                }
            }
            totalLen += freq[d]
            totalSum += freq[d] * d
            dp = newDp
        }
        return dp[targetLen][targetSum]
    }

    private fun computeDigitFreq(num: String): IntArray {
        val result = IntArray(10)
        for (c in num) {
            result[c - '0']++
        }
        return result
    }

    private fun computeDigitSum(digitFreq: IntArray): Int =
        digitFreq.foldIndexed(0) { digit, acc, freq ->
            acc + digit * freq
        }
}
