package com.hj.leetcode.kotlin.problem2094

/**
 * LeetCode page: [2094. Finding 3-Digit Even Numbers](https://leetcode.com/problems/finding-3-digit-even-numbers/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of digits.
    fun findEvenNumbers(digits: IntArray): IntArray {
        val digitFreq = IntArray(10)
        for (digit in digits) {
            digitFreq[digit]++
        }

        val result = mutableListOf<Int>()
        for (msd in 1..<10) {
            if (digitFreq[msd] == 0) {
                continue
            }

            var value = msd * 100
            digitFreq[msd]--
            for (mid in 0..<10) {
                if (digitFreq[mid] == 0) {
                    continue
                }

                value += mid * 10
                digitFreq[mid]--
                for (lsd in 0..<10 step 2) {
                    if (digitFreq[lsd] == 0) {
                        continue
                    }
                    result.add(value + lsd)
                }
                digitFreq[mid]++
                value -= mid * 10
            }
            digitFreq[msd]++
        }
        return result.toIntArray()
    }
}
