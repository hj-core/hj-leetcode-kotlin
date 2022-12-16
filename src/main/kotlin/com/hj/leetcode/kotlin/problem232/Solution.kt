package com.hj.leetcode.kotlin.problem232

/**
 * LeetCode page: [232. Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/description/);
 */
class MyQueue() {
    private val pushStack = ArrayDeque<Int>()
    private val popStack = ArrayDeque<Int>()

    /* Complexity for N calls:
     * Time O(N) and Space O(N);
     */
    fun push(x: Int) {
        pushStack.addLast(x)
    }

    /* Complexity for N calls:
     * Time O(N) and Space O(1);
     */
    fun pop(): Int {
        transfer()
        return popStack.removeLast()
    }

    /* Complexity:
     * Time O(size) and Space O(1);
     */
    private fun transfer() {
        if (popStack.isEmpty()) {
            while (pushStack.isNotEmpty()) {
                popStack.addLast(pushStack.removeLast())
            }
        }
    }

    /* Complexity:
     * Time amortized O(1) and Space O(1);
     */
    fun peek(): Int {
        transfer()
        return popStack.last()
    }

    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun empty(): Boolean {
        return popStack.isEmpty() && pushStack.isEmpty()
    }
}