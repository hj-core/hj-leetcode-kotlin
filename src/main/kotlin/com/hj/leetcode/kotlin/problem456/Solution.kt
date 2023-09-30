package com.hj.leetcode.kotlin.problem456

/**
 * LeetCode page: [456. 132 Pattern](https://leetcode.com/problems/132-pattern/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun find132pattern(nums: IntArray): Boolean {
        val prefixMin = nums.runningReduce { acc, i -> minOf(acc, i) }
        val twoStack = mutableListOf<Int>() // monotonically decreasing

        for (index in nums.lastIndex downTo 1) {
            val three = nums[index]
            val one = prefixMin[index - 1]

            if (three <= one) {
                continue
            }

            while (twoStack.isNotEmpty() && twoStack.last() <= one) {
                twoStack.removeAt(twoStack.lastIndex)
            }

            if (twoStack.isNotEmpty() && twoStack.last() < three) {
                return true
            }
            twoStack.add(three)
        }
        return false
    }
}