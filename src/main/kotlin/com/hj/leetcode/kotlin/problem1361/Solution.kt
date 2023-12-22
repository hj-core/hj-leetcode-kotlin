package com.hj.leetcode.kotlin.problem1361

/**
 * LeetCode page: [1361. Validate Binary Tree Nodes](https://leetcode.com/problems/validate-binary-tree-nodes/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(n);
     */
    fun validateBinaryTreeNodes(n: Int, leftChild: IntArray, rightChild: IntArray): Boolean {
        val roots = findRoots(n, leftChild, rightChild)
        if (roots.size != 1) {
            return false
        }

        val root = roots.first()
        val visited = hashSetOf<Int>()
        val nodeStack = ArrayDeque<Int>().apply { addLast(root) }

        // Check if DFS from the root can visit all nodes and no cycle is found
        while (nodeStack.isNotEmpty()) {
            val node = nodeStack.removeLast()
            if (node in visited) {
                return false
            }
            visited.add(node)

            rightChild[node].let {
                if (it != -1) {
                    nodeStack.addLast(it)
                }
            }
            leftChild[node].let {
                if (it != -1) {
                    nodeStack.addLast(it)
                }
            }
        }
        return visited.size == n
    }

    private fun findRoots(n: Int, leftChild: IntArray, rightChild: IntArray): Set<Int> {
        val result = (0..<n).toHashSet()
        for (left in leftChild) {
            result.remove(left)
        }
        for (right in rightChild) {
            result.remove(right)
        }
        return result
    }
}