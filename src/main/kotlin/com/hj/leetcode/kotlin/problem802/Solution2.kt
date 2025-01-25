package com.hj.leetcode.kotlin.problem802

/**
 * LeetCode page: [802. Find Eventual Safe States](https://leetcode.com/problems/find-eventual-safe-states/);
 */
class Solution2 {
    /* Complexity:
     * Time O(E+N) and Space O(N)
     * where E is the flattened length of graph and N is the length of graph.
     */
    fun eventualSafeNodes(graph: Array<IntArray>): List<Int> {
        // 0=undefined, 1=safe, 2=unsafe
        val nodeSafety = IntArray(graph.size)
        val result = mutableListOf<Int>()

        // A stack that simulates the recursive function calls.
        // Each element is a intArrayOf(node, processingChildIndex).
        val stack = mutableListOf<IntArray>()

        for (node in graph.indices) {
            if (nodeSafety[node] != 0) {
                if (nodeSafety[node] == 1) {
                    result.add(node)
                }
                continue
            }

            nodeSafety[node] = 2
            stack.add(intArrayOf(node, 0))

            while (stack.isNotEmpty()) {
                val (curr, childIndex) = stack.last()

                if (childIndex == graph[curr].size) {
                    nodeSafety[curr] = 1
                    stack.removeLast()
                    continue
                }

                val child = graph[curr][childIndex]
                when (nodeSafety[child]) {
                    0 -> {
                        nodeSafety[child] = 2
                        stack.add(intArrayOf(child, 0))
                    }

                    1 -> stack.last()[1]++
                    2 -> break
                }
            }

            if (nodeSafety[node] == 1) {
                result.add(node)
            }
            stack.clear()
        }
        return result
    }
}
