package com.hj.leetcode.kotlin.problem310

import java.util.*

/**
 * LeetCode page: [310. Minimum Height Trees](https://leetcode.com/problems/minimum-height-trees/);
 */
class Solution {
    /* Complexity:
     * Time O(???) and Space O(???);
     *
     * This is a very DRAFT solution, the ideas are:
     * 1. The root of min height tree is the middle node of the longest path or middle nodes
     *    if the longest path has an even length.
     * 2. There may be several longest paths, but they share the middle node or middle nodes
     *    if the longest path has an even length.
     * 3. Then the problem reduced to find the longest path in the tree.
     */
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        var longestPath = emptyList<Int>()
        dfs(0, 0, adjacencyList(edges)) {
            if (it.size > longestPath.size) {
                longestPath = it
            }
        }

        return buildList {
            add(longestPath[longestPath.size / 2])
            if (longestPath.size % 2 == 0) {
                add(longestPath[longestPath.size / 2 - 1])
            }
        }
    }

    private fun adjacencyList(edges: Array<IntArray>): Map<Int, Set<Int>> {
        val result = mutableMapOf<Int, MutableSet<Int>>()
        for ((u, v) in edges) {
            result.computeIfAbsent(u) { mutableSetOf() }.add(v)
            result.computeIfAbsent(v) { mutableSetOf() }.add(u)
        }
        return result
    }

    private fun dfs(
        node: Int,
        parent: Int,
        adjacencyList: Map<Int, Set<Int>>,
        onEachNode: (longestPassThrough: List<Int>) -> Unit,
    ): List<Int> {
        val twoLongestBranches = PriorityQueue<List<Int>>(compareBy { it.size })
        for (next in adjacencyList[node] ?: emptySet()) {
            if (next == parent) {
                continue
            }

            twoLongestBranches.offer(dfs(next, node, adjacencyList, onEachNode))
            if (twoLongestBranches.size > 2) {
                twoLongestBranches.poll()
            }
        }

        return when (twoLongestBranches.size) {
            0 -> {
                onEachNode(listOf(node))
                listOf(node)
            }
            1 -> {
                val longestPassThrough = buildList {
                    add(node)
                    addAll(twoLongestBranches.peek())
                }
                onEachNode(longestPassThrough)
                return longestPassThrough
            }

            2 -> {
                val longestPassThrough = buildList {
                    addAll(twoLongestBranches.last().asReversed())
                    add(node)
                    addAll(twoLongestBranches.first())
                }
                onEachNode(longestPassThrough)

                buildList {
                    add(node)
                    addAll(twoLongestBranches.last())
                }
            }

            else -> throw IllegalStateException()
        }
    }
}