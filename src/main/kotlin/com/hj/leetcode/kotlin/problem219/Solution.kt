package com.hj.leetcode.kotlin.problem219

/**
 * LeetCode page: [219. Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/);
 */
class Solution {
    /* Complexity:
     * Time (N) and Space O(K) where N is the size of nums and K equals k;
     */
    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        if (k == 0) return false

        val prevKNumbers = hashSetOf<Int>()

        for (index in nums.indices) {
            if (nums[index] in prevKNumbers) return true

            prevKNumbers.add(nums[index])
            if (prevKNumbers.size > k) prevKNumbers.remove(nums[index - k])
        }
        return false
    }
}