package com.hj.leetcode.kotlin.problem611

/**
 * LeetCode page: [611. Valid Triangle Number](https://leetcode.com/problems/valid-triangle-number/);
 */
class Solution2 {
    // Complexity:
    // Time O(N^2+M) and Space O(N+M) where N is the length of
    // nums and M is the maximum value in nums.
    fun triangleNumber(nums: IntArray): Int {
        val maxNum = nums.max()
        if (maxNum == 0) {
            return 0
        }

        var freqs = IntArray(maxNum + 2)
        for (num in nums) {
            freqs[num]++
        }

        // positives:= sortedSet(e for e in nums if e > 0)
        val positives = mutableListOf<Int>()
        for (num in 1..maxNum) {
            if (freqs[num] > 0) {
                positives.add(num)
            }
        }

        // suffixFreqs[num]:= count(e for e in nums if e >= num)
        val suffixFreqs = freqs.also { freqs = intArrayOf() }
        for (num in maxNum downTo 0) {
            suffixFreqs[num] += suffixFreqs[num + 1]
        }

        var result = 0
        var i = 0
        val boundI = maxNum shr 1

        while (positives[i] <= boundI) {
            val num1 = positives[i]
            val f1 = suffixFreqs[num1] - suffixFreqs[num1 + 1]

            result += nC3(f1)
            result += nC2(f1) * (suffixFreqs[num1 + 1] - suffixFreqs[minOf(num1 * 2, maxNum + 1)])

            var j = i + 1
            val boundJ = maxNum - num1

            while (positives[j] <= boundJ) {
                val num2 = positives[j]
                val f2 = suffixFreqs[num2] - suffixFreqs[num2 + 1]

                result += f1 * nC2(f2)
                result += f1 * f2 * (suffixFreqs[num2 + 1] - suffixFreqs[minOf(num1 + num2, maxNum + 1)])
                j++
            }

            result += f1 * nC2(suffixFreqs[positives[j]])
            i++
        }

        result += nC3(suffixFreqs[positives[i]])
        return result
    }

    private fun nC2(n: Int): Int = n * (n - 1) / 2

    private fun nC3(n: Int): Int = n * (n - 1) * (n - 2) / 6
}
