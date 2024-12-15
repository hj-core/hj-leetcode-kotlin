package com.hj.leetcode.kotlin.problem2762

import java.util.*

/**
 * LeetCode page: [2762. Continuous Subarrays](https://leetcode.com/problems/continuous-subarrays/);
 */
class Solution2 {
    /* Complexity:
     * Time O(NLogK) and Space O(K)
     * where N is the length of nums and K is the allowed value difference.
     */
    fun continuousSubarrays(nums: IntArray): Long {
        val numFreq = TreeMap<Int, Int>()
        var result = 0L
        var left = 0
        val allowedDiff = 2

        for ((right, num) in nums.withIndex()) {
            numFreq[num] = (numFreq[num] ?: 0) + 1

            while (numFreq.lastKey() - numFreq.firstKey() > allowedDiff) {
                numFreq.computeIfPresent(nums[left]) { _, freq ->
                    if (freq == 1) null else freq - 1
                }
                left++
            }
            result += right - left + 1
        }
        return result
    }
}
