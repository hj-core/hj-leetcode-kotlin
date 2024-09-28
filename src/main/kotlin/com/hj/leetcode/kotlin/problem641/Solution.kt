package com.hj.leetcode.kotlin.problem641

import java.util.*

/**
 * LeetCode page: [641. Design Circular Deque](https://leetcode.com/problems/design-circular-deque/);
 */
class MyCircularDeque(
    k: Int,
) {
    private val capacity = k
    private val buffer = Array(k) { -1 }
    private var size = 0
    private var start = 0 // Index of front
    private var endInclusive = k - 1 // Index of rear

    // For test only
    internal constructor(k: Int, buffer: Array<Int>, size: Int, start: Int, endInclusive: Int) : this(k) {
        Arrays.setAll(this.buffer) { index -> buffer[index] }
        this.size = size
        this.start = start
        this.endInclusive = endInclusive
    }

    fun insertFront(value: Int): Boolean {
        if (isFull()) {
            return false
        }
        start = (start - 1).mod(capacity)
        buffer[start] = value
        size += 1
        return true
    }

    fun insertLast(value: Int): Boolean {
        if (isFull()) {
            return false
        }
        endInclusive = (endInclusive + 1).mod(capacity)
        buffer[endInclusive] = value
        size += 1
        return true
    }

    fun deleteFront(): Boolean {
        if (isEmpty()) {
            return false
        }
        start = (start + 1).mod(capacity)
        size -= 1
        return true
    }

    fun deleteLast(): Boolean {
        if (isEmpty()) {
            return false
        }
        endInclusive = (endInclusive - 1).mod(capacity)
        size -= 1
        return true
    }

    fun getFront(): Int {
        if (size == 0) {
            return -1
        }
        return buffer[start]
    }

    fun getRear(): Int {
        if (size == 0) {
            return -1
        }
        return buffer[endInclusive]
    }

    fun isEmpty(): Boolean = size == 0

    fun isFull(): Boolean = size == capacity
}
