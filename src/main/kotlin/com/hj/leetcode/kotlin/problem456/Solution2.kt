package com.hj.leetcode.kotlin.problem456

/**
 * LeetCode page: [456. 132 Pattern](https://leetcode.com/problems/132-pattern/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun find132pattern(nums: IntArray): Boolean {
        val stack = mutableListOf<Int>() // monotonically decreasing
        var two = Int.MIN_VALUE

        for (index in nums.indices.reversed()) {
            val potentialOne = nums[index]

            /* It is guaranteed that there is a three in the stack which is
             * greater than two, so if potentialOne is less than two, we find
             * a valid one-three-two pattern.
             */
            if (potentialOne < two) {
                return true
            }

            /* Push the value of two as large as possible, then add its
             * corresponding three to stack.
             */
            while (stack.isNotEmpty() && stack.last() < potentialOne) {
                two = stack.removeAt(stack.lastIndex)
            }
            stack.add(potentialOne)
        }
        return false
    }
}