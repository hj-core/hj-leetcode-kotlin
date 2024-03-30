package com.hj.leetcode.kotlin.problem992

/**
 * LeetCode page: [992. Subarrays with K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(k) where N is the size of nums;
     */
    fun subarraysWithKDistinct(nums: IntArray, k: Int): Int {
        return subarraysAtLeastKDistinct(nums, k) - subarraysAtLeastKDistinct(nums, k + 1)
    }

    private fun subarraysAtLeastKDistinct(nums: IntArray, k: Int): Int {
        var result = 0
        var start = 0
        val counter = hashMapOf<Int, Int>() // num to its count

        // For each end, find the latest start that fulfills k distinct
        for (end in nums.indices) {
            counter.compute(nums[end]) { _, v ->
                if (v == null) 1 else 1 + v
            }

            while (counter.size > k || checkNotNull(counter[nums[start]]) > 1) {
                counter.compute(nums[start]) { _, v ->
                    if (v == 1) null else checkNotNull(v) - 1
                }
                start += 1
            }

            if (counter.size == k) {
                result += start + 1
            }
        }
        return result
    }
}