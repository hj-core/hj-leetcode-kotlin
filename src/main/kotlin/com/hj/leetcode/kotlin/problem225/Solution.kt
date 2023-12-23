package com.hj.leetcode.kotlin.problem225

/**
 * LeetCode page: [225. Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/);
 */
class MyStack() {

    private val queue = ArrayDeque<Int>()

    /* Complexity:
     * Time O(N) where N is the size of queue;
     */
    fun push(x: Int) {
        queue.addLast(x)
        repeat(queue.size - 1) {
            queue.addLast(queue.removeFirst())
        }
    }

    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun pop(): Int {
        return queue.removeFirst()
    }

    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun top(): Int {
        return queue.first()
    }

    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun empty(): Boolean {
        return queue.isEmpty()
    }
}