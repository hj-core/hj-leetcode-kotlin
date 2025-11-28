package com.hj.leetcode.kotlin.problem2872

/**
 * LeetCode page: [2872. Maximum Number of K-Divisible Components](https://leetcode.com/problems/maximum-number-of-k-divisible-components/);
 */
class Solution2 {
    // Complexity:
    // Time O(n) and Space O(n).
    fun maxKDivisibleComponents(
        n: Int,
        edges: Array<IntArray>,
        values: IntArray,
        k: Int,
    ): Int {
        val subtreeSum = IntArray(n) { values[it] % k }

        val degreeShift = 40
        val unitDegree = 1L shl degreeShift
        // adjXor[node]:=
        // (inDegree<<degreeShift) | (xor of all its neighbour ids)
        val adjXor = LongArray(n)
        for ((u, v) in edges) {
            adjXor[u] = adjXor[u] xor v.toLong()
            adjXor[u] += unitDegree
            adjXor[v] = adjXor[v] xor u.toLong()
            adjXor[v] += unitDegree
        }

        val nodeQueue = IntArray(n)
        var qHead = 0
        var qLast = -1

        for (node in 0..<n) {
            if ((adjXor[node] shr degreeShift) == 1L) {
                qLast++
                nodeQueue[qLast] = node
            }
        }

        var result = 1 // Skip the very last node (component)
        while (qLast > qHead) {
            val node = nodeQueue[qHead]
            qHead++
            if (subtreeSum[node] == 0) {
                result++
            }

            val next = (adjXor[node] - unitDegree).toInt()
            subtreeSum[next] += subtreeSum[node]
            if (subtreeSum[next] >= k) {
                subtreeSum[next] -= k
            }

            adjXor[next] = adjXor[next] xor node.toLong()
            adjXor[next] -= unitDegree
            if ((adjXor[next] shr degreeShift) == 1L) {
                qLast++
                nodeQueue[qLast] = next
            }
        }
        return result
    }
}
