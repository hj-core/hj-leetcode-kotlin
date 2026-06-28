package com.hj.leetcode.kotlin.problem3020

/**
 * LeetCode page: [3020. Find the Maximum Number of Elements in Subset](https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset/);
 */
class Solution {
    // Complexity:
    // Time O(N * LogLog M) and Space O(N) where N is the length of nums and M is the
    // maximum value in nums.
    fun maximumLength(nums: IntArray): Int {
        val numFreq = nums.asIterable().groupingBy(Int::toLong).eachCountTo(hashMapOf())
        var maxLen = 1
        numFreq[1]?.let {
            maxLen = if (it and 1 == 0) it - 1 else it
            numFreq.remove(1)
        }

        for (num in numFreq.keys) {
            var x = num
            var k = 1
            while (numFreq[x]?.let { it >= 2 } == true) {
                x *= x
                k += 1
            }
            val len = if (x in numFreq) 2 * k - 1 else 2 * k - 3
            maxLen = maxOf(maxLen, len)
        }

        return maxLen
    }
}
