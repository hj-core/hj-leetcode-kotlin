package com.hj.leetcode.kotlin.problem2048

/**
 * LeetCode page: [2048. Next Greater Numerically Balanced Number](https://leetcode.com/problems/next-greater-numerically-balanced-number/);
 */
class Solution {
    // Minimum and maximum beautiful numbers by length
    private val minBeautiful = intArrayOf(0, 1, 22, 122, 1333, 14444, 122333, 1224444)
    private val maxBeautiful = intArrayOf(0, 1, 22, 333, 4444, 55555, 666666, 7777777)

    // Combinations of beautiful numbers
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

    // Rearranges the permutation to form the smallest possible
    // beautiful number that is greater than digits and returns
    // whether the attempt was successful.
    //
    // # Invariant
    // permutation[i:] should be in sorted order.
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
            permutation.rotateRight(i..j)
            if (rearrange(digits, permutation, i + 1)) {
                return true
            }
            permutation.rotateLeft(i..j)
        }

        while (j < permutation.size && permutation[j] <= digits[i]) {
            j++
        }
        if (j == permutation.size) {
            return false
        }
        permutation.rotateRight(i..j)
        return true
    }

    private fun IntArray.rotateRight(range: IntRange) {
        val first = range.first
        val last = range.last

        val tmp = this[last]
        for (i in last downTo first + 1) {
            this[i] = this[i - 1]
        }
        this[first] = tmp
    }

    private fun IntArray.rotateLeft(range: IntRange) {
        val first = range.first
        val last = range.last

        val tmp = this[first]
        for (i in first..<last) {
            this[i] = this[i + 1]
        }
        this[last] = tmp
    }

    private fun value(digits: IntArray): Int {
        var result = 0
        for (d in digits) {
            result = result * 10 + d
        }
        return result
    }
}
