package com.hj.leetcode.kotlin.problem622

/**
 * LeetCode page: [622. Design Circular Queue](https://leetcode.com/problems/design-circular-queue/);
 */
class MyCircularQueue(k: Int) {

    private val container = IntArray(k + 1)
    private var readPtr = 0
    private var writePtr = 0

    private fun getNextPosition(ptr: Int) = if (ptr == container.lastIndex) 0 else ptr + 1
    private fun getPrevPosition(ptr: Int) = if (ptr == 0) container.lastIndex else ptr - 1

    fun enQueue(value: Int): Boolean {
        if (isFull()) return false

        container[writePtr] = value
        writePtr = getNextPosition(writePtr)
        return true
    }

    fun deQueue(): Boolean {
        if (isEmpty()) return false

        readPtr = getNextPosition(readPtr)
        return true
    }

    fun Front(): Int {
        return if (isEmpty()) -1 else container[readPtr]
    }

    fun Rear(): Int {
        return if (isEmpty()) -1 else container[getPrevPosition(writePtr)]
    }

    fun isEmpty(): Boolean {
        return readPtr == writePtr
    }

    fun isFull(): Boolean {
        return getNextPosition(writePtr) == readPtr
    }
}