package com.hj.leetcode.kotlin.problem138

/**
 * LeetCode page: [138. Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in node;
     */
    fun copyRandomList(node: Node?): Node? {
        return copy(node, hashMapOf())
    }

    private fun copy(
        node: Node?,
        memoization: MutableMap<Node, Node>, // entry=(original, copied)
    ): Node? {
        if (node == null) {
            return null
        }

        if (node in memoization) {
            return memoization[node]
        }

        val copied = Node(node.`val`)
        memoization[node] = copied
        copied.next = copy(node.next, memoization)
        copied.random = copy(node.random, memoization)
        return copied
    }
}

/* Don't copy this class definition when submitting to Leetcode, as it will
 * cause a redeclaration error.
 */
class Node(var `val`: Int) {
    var next: Node? = null
    var random: Node? = null
}