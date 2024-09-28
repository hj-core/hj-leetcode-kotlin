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
    private var frontIndex = 0
    private var rearIndex = k - 1

    fun insertFront(value: Int): Boolean {
        if (isFull()) {
            return false
        }
        frontIndex = (frontIndex - 1).mod(capacity)
        buffer[frontIndex] = value
        size += 1
        return true
    }

    fun insertLast(value: Int): Boolean {
        if (isFull()) {
            return false
        }
        rearIndex = (rearIndex + 1).mod(capacity)
        buffer[rearIndex] = value
        size += 1
        return true
    }

    fun deleteFront(): Boolean {
        if (isEmpty()) {
            return false
        }
        frontIndex = (frontIndex + 1).mod(capacity)
        size -= 1
        return true
    }

    fun deleteLast(): Boolean {
        if (isEmpty()) {
            return false
        }
        rearIndex = (rearIndex - 1).mod(capacity)
        size -= 1
        return true
    }

    fun getFront(): Int {
        if (size == 0) {
            return -1
        }
        return buffer[frontIndex]
    }

    fun getRear(): Int {
        if (size == 0) {
            return -1
        }
        return buffer[rearIndex]
    }

    fun isEmpty(): Boolean = size == 0

    fun isFull(): Boolean = size == capacity
}
