package com.hj.leetcode.kotlin.problem225

/**
 * LeetCode page: [225. Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/);
 */
class MyStack2() {

    private val queue = ArrayDeque<Int>()

    /* Complexity:
     * Time O(1);
     */
    fun push(x: Int) {
        queue.addLast(x)
    }

    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of queue;
     */
    fun pop(): Int {
        repeat(queue.size - 1) {
            queue.addLast(queue.removeFirst())
        }
        return queue.removeFirst()
    }

    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun top(): Int {
        return queue.last()
    }

    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun empty(): Boolean {
        return queue.isEmpty()
    }
}