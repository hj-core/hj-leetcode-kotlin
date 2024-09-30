package com.hj.leetcode.kotlin.problem1381

import kotlin.math.min

/**
 * LeetCode page: [1381. Design a Stack With Increment Operation](https://leetcode.com/problems/design-a-stack-with-increment-operation/);
 */
class CustomStack(
    private val maxSize: Int,
) {
    private val elementStack = mutableListOf<Int>()
    private val incrementStack = mutableListOf<Int>()

    /* Complexity for N calls:
     * Time O(N) and Space O(N).
     */
    fun push(x: Int) {
        if (isFull()) {
            return
        }
        elementStack.add(x)
        incrementStack.add(0)
    }

    private fun isFull(): Boolean = elementStack.size == maxSize

    /* Complexity for N calls:
     * Time O(N) and Space O(1).
     */
    fun pop(): Int {
        if (isEmpty()) {
            return -1
        }
        val element = elementStack.removeLast()
        val increment = incrementStack.removeLast()
        if (incrementStack.isNotEmpty()) {
            incrementStack.apply { this[lastIndex] += increment }
        }
        return element + increment
    }

    private fun isEmpty(): Boolean = elementStack.isEmpty()

    /* Complexity for N calls:
     * Time O(N) and Space O(1).
     */
    fun increment(
        k: Int,
        `val`: Int,
    ) {
        if (isEmpty()) {
            return
        }
        val index = min(k - 1, incrementStack.lastIndex)
        incrementStack[index] += `val`
    }
}
