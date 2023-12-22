package com.hj.leetcode.kotlin.problem399

/**
 * LeetCode page: [399. Evaluate Division](https://leetcode.com/problems/evaluate-division/);
 */
class Solution {
    /* Complexity:
     * Time O(NE) and Space O(E) where E is the size of equations and N is the size of queries;
     */
    fun calcEquation(equations: List<List<String>>, values: DoubleArray, queries: List<List<String>>): DoubleArray {
        val knownQuotients = adjacencyList(equations, values)

        return DoubleArray(queries.size) { index ->
            val (u, v) = queries[index]
            val isUnknownVariable = u !in knownQuotients || v !in knownQuotients
            if (isUnknownVariable) {
                -1.0
            } else {
                computeQuotient(u, v, 1.0, knownQuotients) ?: -1.0
            }
        }
    }

    private fun adjacencyList(
        equations: List<List<String>>,
        values: DoubleArray,
    ): Map<String, List<Quotient>> {
        val result = hashMapOf<String, MutableList<Quotient>>()
        for ((index, equation) in equations.withIndex()) {
            val (u, v) = equation
            val value = values[index]
            result.computeIfAbsent(u) { mutableListOf() }.add(Quotient(v, value))
            result.computeIfAbsent(v) { mutableListOf() }.add(Quotient(u, 1.0 / value))
        }
        return result
    }

    private data class Quotient(val divisor: String, val value: Double)

    private fun computeQuotient(
        dividend: String,
        divisor: String,
        weightProduct: Double,
        knownQuotients: Map<String, List<Quotient>>,
        visited: MutableSet<String> = hashSetOf(),
    ): Double? {
        if (dividend == divisor) {
            return weightProduct
        }

        visited.add(dividend)
        val adjacentNodes = knownQuotients[dividend] ?: emptyList()
        for (adjacentNode in adjacentNodes) {
            if (adjacentNode.divisor in visited) {
                continue
            }

            visited.add(adjacentNode.divisor)
            computeQuotient(
                adjacentNode.divisor,
                divisor,
                weightProduct * adjacentNode.value,
                knownQuotients,
                visited
            )?.let { return it }
        }
        return null
    }
}