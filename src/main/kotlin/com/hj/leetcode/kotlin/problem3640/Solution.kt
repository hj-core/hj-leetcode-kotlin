package com.hj.leetcode.kotlin.problem3640

/**
 * LeetCode page: [3640. Trionic Array II](https://leetcode.com/problems/trionic-array-ii/);
 */
class Solution {
    fun maxSumTrionic(nums: IntArray): Long {
        var maxSum = -(1L shl 52)
        var midLen = 0
        var midSum = 0L

        var q = 1
        while (q < nums.size - 1) {
            if (nums[q] > nums[q + 1]) {
                midLen++
                midSum += nums[q]
                q++
            } else if (nums[q] == nums[q + 1] || midLen == 0) {
                midLen = 0
                midSum = 0L
                q++
            } else if (nums[q - midLen - 1] >= nums[q - midLen]) {
                midLen = 0
                midSum = 0L
                q++
            } else {
                var l = q - midLen - 1
                var leftSum = 0L
                var maxLeftSum = nums[l].toLong()
                while (l >= 0 && nums[l] < nums[l + 1]) {
                    leftSum += nums[l]
                    if (leftSum > maxLeftSum) {
                        maxLeftSum = leftSum
                    }
                    l--
                }

                var r = q + 1
                var rightSum = nums[q].toLong()
                var maxRightSum = (nums[q] + nums[r]).toLong()
                while (r < nums.size && nums[r - 1] < nums[r]) {
                    rightSum += nums[r]
                    if (rightSum > maxRightSum) {
                        maxRightSum = rightSum
                    }
                    r++
                }
                r--

                maxSum =
                    maxOf(maxSum, midSum + maxLeftSum + maxRightSum)
                q = r
                midLen = 0
                midSum = 0L
            }
        }

        return maxSum
    }
}
