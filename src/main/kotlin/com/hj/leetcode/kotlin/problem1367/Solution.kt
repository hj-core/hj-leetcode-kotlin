package com.hj.leetcode.kotlin.problem1367

import com.hj.leetcode.kotlin.common.model.ListNode
import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1367. Linked List in Binary Tree](https://leetcode.com/problems/linked-list-in-binary-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(N) where M is the number of nodes in head and
     * N is the number of nodes in root.
     */
    fun isSubPath(
        head: ListNode?,
        root: TreeNode?,
    ): Boolean {
        if (head == null) {
            return true
        }
        if (root == null) {
            return false
        }
        var result = isSubPath(head, root.left) || isSubPath(head, root.right)
        if (head.`val` == root.`val`) {
            result = result ||
                match(head.next, root.left) ||
                match(head.next, root.right)
        }
        return result
    }

    private fun match(
        head: ListNode?,
        root: TreeNode?,
    ): Boolean =
        when {
            head == null -> true
            root == null -> false
            root.`val` != head.`val` -> false
            else -> match(head.next, root.left) || match(head.next, root.right)
        }
}
