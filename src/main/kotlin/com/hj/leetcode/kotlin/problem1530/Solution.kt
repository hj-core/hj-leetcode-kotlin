package com.hj.leetcode.kotlin.problem1530

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1530. Number of Good Leaf Nodes Pairs](https://leetcode.com/problems/number-of-good-leaf-nodes-pairs/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N^2) where N is the number of nodes in root;
     */
    fun countPairs(root: TreeNode?, distance: Int): Int {
        var result = 0
        dfsLeafDepths(root, distance) { validPairs ->
            result += validPairs
        }
        return result
    }

    private fun dfsLeafDepths(
        root: TreeNode?,
        distance: Int,
        onEachNode: (validPairs: Int) -> Unit
    ): List<Int> {
        if (root == null) {
            return emptyList()
        }

        val depthsLeft = dfsLeafDepths(root.left, distance, onEachNode)
        val depthsRight = dfsLeafDepths(root.right, distance, onEachNode)

        return when {
            depthsLeft.isEmpty() && depthsRight.isEmpty() -> listOf(0)
            depthsLeft.isEmpty() -> depthsRight.map { it + 1 }
            depthsRight.isEmpty() -> depthsLeft.map { it + 1 }
            else -> {
                val pairs = countValidPairs(depthsRight, depthsLeft, distance)
                onEachNode(pairs)
                val result = mergeSorted(depthsLeft, depthsRight)
                for (i in result.indices) {
                    result[i] += 1
                }
                return result
            }
        }
    }

    private fun countValidPairs(
        depthsRight: List<Int>,
        depthsLeft: List<Int>,
        distance: Int,
    ): Int {
        var pairs = 0
        var rightPtr = depthsRight.lastIndex
        for (depth in depthsLeft) {
            while (0 <= rightPtr && depthsRight[rightPtr] > distance - depth - 2) {
                rightPtr--
            }
            if (rightPtr < 0) {
                break
            }
            pairs += rightPtr + 1
        }
        return pairs
    }

    private fun mergeSorted(sorted1: List<Int>, sorted2: List<Int>): MutableList<Int> {
        val result = mutableListOf<Int>()
        var index1 = 0
        var index2 = 0
        while (index1 < sorted1.size && index2 < sorted2.size) {
            if (sorted1[index1] <= sorted2[index2]) {
                result.add(sorted1[index1])
                index1++
            } else {
                result.add(sorted2[index2])
                index2++
            }
        }
        for (i in index1..<sorted1.size) {
            result.add(sorted1[i])
        }
        for (i in index2..<sorted2.size) {
            result.add(sorted2[i])
        }
        return result
    }
}