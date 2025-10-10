package com.hj.leetcode.kotlin.problem611

/**
 * LeetCode page: [611. Valid Triangle Number](https://leetcode.com/problems/valid-triangle-number/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(N) where N is the length of nums.
    fun triangleNumber(nums: IntArray): Int {
        val sortedNums = nums.sortedArray()
        if (sortedNums.last() == 0) {
            return 0
        }

        var result = 0
        var i = sortedNums.indexOfFirst { it > 0 }
        val boundI = sortedNums.last() shr 1

        while (sortedNums[i] <= boundI) {
            var j = i + 1
            var k = i + 2
            val boundJ = sortedNums.last() - sortedNums[i]

            while (sortedNums[j] <= boundJ) {
                val boundK = sortedNums[i] + sortedNums[j]
                while (sortedNums[k] < boundK) {
                    k++
                }
                result += k - j - 1
                j++
            }
            // Any two from the remaining n-j numbers can form
            // a triangle with sortedNums[i].
            val m = sortedNums.size - j
            result += m * (m - 1) / 2
            i++
        }

        // Any three from the remaining n-i numbers can form
        // a triangle.
        val m = sortedNums.size - i
        result += m * (m - 1) * (m - 2) / 6
        return result
    }
}
