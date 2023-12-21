package com.hj.leetcode.kotlin.problem946

/**
 * LeetCode page: [946. Validate Stack Sequences](https://leetcode.com/problems/validate-stack-sequences/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of pushed;
     */
    fun validateStackSequences(pushed: IntArray, popped: IntArray): Boolean {
        val stack = ArrayDeque<Int>()
        var numPopped = 0
        for (element in pushed) {
            stack.addLast(element)
            while (
                stack.isNotEmpty() &&
                numPopped < popped.size &&
                stack.last() == popped[numPopped]
            ) {
                stack.removeLast()
                numPopped++
            }
        }
        return numPopped == popped.size
    }
}