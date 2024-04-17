package com.hj.leetcode.kotlin.problem988

import com.hj.leetcode.kotlin.common.model.TreeNode
import kotlin.math.min

/**
 * LeetCode page: [988. Smallest String Starting From Leaf](https://leetcode.com/problems/smallest-string-starting-from-leaf/);
 */
class Solution {
    fun smallestFromLeaf(root: TreeNode?): String {
        requireNotNull(root)
        var result: StringBuilder? = null
        dfs(root, StringBuilder()) {
            if (compareByReversed(it, result) < 0) {
                result = StringBuilder(it)
            }
        }
        checkNotNull(result).reverse()
        return result.toString()
    }

    private fun dfs(node: TreeNode, str: StringBuilder, onEachLeaf: (str: StringBuilder) -> Unit) {
        str.append('a' + node.`val`)
        if (node.isLeaf()) {
            onEachLeaf(str)
        }
        node.left?.let { dfs(it, str, onEachLeaf) }
        node.right?.let { dfs(it, str, onEachLeaf) }
        str.deleteAt(str.lastIndex)
    }

    private fun TreeNode.isLeaf(): Boolean {
        return left == null && right == null
    }

    private fun compareByReversed(s1: StringBuilder?, s2: StringBuilder?): Int {
        return when {
            s1 == null && s2 == null -> 0
            s1 == null -> 1
            s2 == null -> -1
            else -> {
                for (i in 0..<min(s1.length, s2.length)) {
                    val char1 = s1[s1.lastIndex - i]
                    val char2 = s2[s2.lastIndex - i]
                    if (char1 != char2) {
                        return char1.compareTo(char2)
                    }
                }
                return s1.length.compareTo(s2.length)
            }
        }
    }
}