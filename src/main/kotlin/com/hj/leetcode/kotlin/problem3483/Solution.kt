package com.hj.leetcode.kotlin.problem3483

/**
 * LeetCode page: [3483. Unique 3-Digit Even Numbers](https://leetcode.com/problems/unique-3-digit-even-numbers/);
 */
class Solution {
    // Complexity:
    // Time O(N+D^3) and Space O(D) where N is the length of digits and
    // D is size of digit set (i.e., 0 to 9).
    fun totalNumbers(digits: IntArray): Int {
        val freq = IntArray(10)
        for (d in digits) {
            freq[d]++
        }

        var count = 0
        for (last in 0..<10 step 2) {
            if (freq[last] == 0) {
                continue
            }

            freq[last]--
            for (first in 1..<10) {
                if (freq[first] == 0) {
                    continue
                }

                freq[first]--
                count += freq.count { it > 0 }
                freq[first]++
            }
            freq[last]++
        }

        return count
    }
}
