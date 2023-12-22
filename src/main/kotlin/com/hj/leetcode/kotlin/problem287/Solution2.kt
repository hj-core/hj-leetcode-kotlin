package com.hj.leetcode.kotlin.problem287

/**
 * LeetCode page: [287. Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun findDuplicate(nums: IntArray): Int {
        var slow = nums[0]
        var fast = nums[nums[0]]

        while (slow != fast) {
            slow = nums[slow]
            fast = nums[nums[fast]]
        }

        var result = 0
        while (result != slow) {
            result = nums[result]
            slow = nums[slow]
        }
        return result
    }
}