package com.hj.leetcode.kotlin.problem239

/**
 * LeetCode page: [239. Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun maxSlidingWindow(
        nums: IntArray,
        k: Int,
    ): IntArray {
        // A deque stores the indices of numbers and has strictly
        // decreasing nums[indices].
        val monoDeque = FixOpsIntDeque(nums.size)
        for (i in 0..<k - 1) {
            while (monoDeque.isNotEmpty() && nums[monoDeque.last()] <= nums[i]) {
                monoDeque.removeLast()
            }
            monoDeque.addLast(i)
        }

        val result = IntArray(nums.size - k + 1)
        for (i in k - 1..<nums.size) {
            while (monoDeque.isNotEmpty() && nums[monoDeque.last()] <= nums[i]) {
                monoDeque.removeLast()
            }
            monoDeque.addLast(i)

            while (monoDeque.first() <= i - k) {
                monoDeque.removeFirst()
            }
            result[i - k + 1] = nums[monoDeque.first()]
        }
        return result
    }
}

// An integer deque that supports up to maxOps add/remove
// operations.
private class FixOpsIntDeque(
    maxOps: Int,
) {
    private val deque = IntArray(maxOps)
    private var start = 0
    private var end = 0

    fun isEmpty(): Boolean = start == end

    fun isNotEmpty(): Boolean = !isEmpty()

    fun first(): Int {
        if (isEmpty()) {
            throw NoSuchElementException()
        }
        return deque[start]
    }

    fun removeFirst(): Int {
        if (isEmpty()) {
            throw NoSuchElementException()
        }
        start++
        return deque[start - 1]
    }

    fun last(): Int {
        if (isEmpty()) {
            throw NoSuchElementException()
        }
        return deque[end - 1]
    }

    fun addLast(value: Int) {
        deque[end] = value
        end++
    }

    fun removeLast(): Int {
        if (isEmpty()) {
            throw NoSuchElementException()
        }
        end--
        return deque[end]
    }
}
