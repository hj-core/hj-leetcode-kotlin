package com.hj.leetcode.kotlin.problem2048

/**
 * LeetCode page: [2048. Next Greater Numerically Balanced Number](https://leetcode.com/problems/next-greater-numerically-balanced-number/);
 */
class Solution {
    private val maxBeautiful = intArrayOf(0, 1, 22, 333, 4444, 55555, 666666, 7777777)
    private val minBeautiful = intArrayOf(0, 1, 22, 122, 1333, 14444, 122333, 1224444)
    private val combinations =
        arrayOf(
            arrayOf(intArrayOf()),
            arrayOf(intArrayOf(1)),
            arrayOf(intArrayOf(2, 2)),
            arrayOf(intArrayOf(1, 2, 2), intArrayOf(3, 3, 3)),
            arrayOf(intArrayOf(1, 3, 3, 3), intArrayOf(4, 4, 4, 4)),
            arrayOf(
                intArrayOf(1, 4, 4, 4, 4),
                intArrayOf(2, 2, 3, 3, 3),
                intArrayOf(5, 5, 5, 5, 5),
            ),
            arrayOf(
                intArrayOf(1, 2, 2, 3, 3, 3),
                intArrayOf(1, 5, 5, 5, 5, 5),
                intArrayOf(2, 2, 4, 4, 4, 4),
                intArrayOf(6, 6, 6, 6, 6, 6),
            ),
        )

    fun nextBeautifulNumber(n: Int): Int {
        if (n == 0) {
            return 1
        }

        val digits = digits(n)
        val length = digits.size

        if (n < minBeautiful[length]) {
            return minBeautiful[length]
        }
        if (n >= maxBeautiful[length]) {
            return minBeautiful[length + 1]
        }

        var result = maxBeautiful[length]
        for (comb in combinations[length]) {
            val permutation = comb.clone()
            if (rearrange(digits, permutation, 0)) {
                result = minOf(result, value(permutation))
            }
        }
        return result
    }

    private fun digits(n: Int): IntArray {
        val digits = mutableListOf<Int>()
        var num = n
        while (num > 0) {
            digits.add(num % 10)
            num /= 10
        }
        return digits.toIntArray().apply { reverse() }
    }

    private fun rearrange(
        digits: IntArray,
        permutation: IntArray,
        i: Int,
    ): Boolean {
        if (i == permutation.size || permutation.last() < digits[i]) {
            return false
        }

        var j = i
        while (permutation[j] < digits[i]) {
            j++
        }

        if (permutation[j] == digits[i]) {
            for (k in j downTo i + 1) {
                permutation.swap(k, k - 1)
            }
            if (rearrange(digits, permutation, i + 1)) {
                return true
            }
            for (k in i..<j) {
                permutation.swap(k, k + 1)
            }
        }

        while (j < permutation.size && permutation[j] <= digits[i]) {
            j++
        }
        if (j == permutation.size) {
            return false
        }
        for (k in j downTo i + 1) {
            permutation.swap(k, k - 1)
        }
        return true
    }

    private fun IntArray.swap(
        i: Int,
        j: Int,
    ) {
        this[i] = this[j].also { this[j] = this[i] }
    }

    private fun value(digits: IntArray): Int {
        var result = 0
        for (d in digits) {
            result = result * 10 + d
        }
        return result
    }
}
