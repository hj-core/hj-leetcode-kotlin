package com.hj.leetcode.kotlin.problem2762

/**
 * LeetCode page: [2762. Continuous Subarrays](https://leetcode.com/problems/continuous-subarrays/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(K)
     * where N is the length of nums and K is the allowed value difference.
     */
    fun continuousSubarrays(nums: IntArray): Long {
        // Using two monotonic dequeues to control the lower bound and upper bound of values.
        // They store the indices of nums with strictly increasing/decreasing values.
        // nums[preStart+1..=end] >= nums[incDeque.first()]
        // nums[preStart+1..=end] <= nums[decDeque.first()]
        val incDeque = ArrayDeque<Int>()
        val decDeque = ArrayDeque<Int>()
        var preStart = -1
        val allowedDiff = -2..2
        var result = 0L

        for ((end, num) in nums.withIndex()) {
            while (incDeque.isNotEmpty() && num <= nums[incDeque.last()]) {
                incDeque.removeLast()
            }
            incDeque.addLast(end)

            while (decDeque.isNotEmpty() && nums[decDeque.last()] <= num) {
                decDeque.removeLast()
            }
            decDeque.addLast(end)

            val range = nums[decDeque.first()] - nums[incDeque.first()]
            if (range !in allowedDiff) {
                val toPop = if (incDeque.size == 1) decDeque else incDeque
                while (nums[toPop.first()] - num !in allowedDiff) {
                    preStart = toPop.removeFirst()
                }
            }
            result += end - preStart
        }
        return result
    }
}
