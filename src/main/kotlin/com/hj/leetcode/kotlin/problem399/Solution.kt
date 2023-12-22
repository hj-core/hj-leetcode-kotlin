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
            computeQuotient(u, v, knownQuotients) ?: -1.0
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
        knownQuotients: Map<String, List<Quotient>>,
        visited: MutableSet<String> = hashSetOf(),
    ): Double? {
        if (dividend !in knownQuotients || divisor !in knownQuotients) {
            return null
        }
        if (dividend == divisor) {
            return 1.0
        }

        visited.add(dividend)
        for (quotient in knownQuotients[dividend] ?: emptyList()) {
            if (quotient.divisor in visited) {
                continue
            }

            val subQuotient =
                computeQuotient(quotient.divisor, divisor, knownQuotients, visited)
            if (subQuotient != null) {
                return quotient.value * subQuotient
            }
        }
        return null
    }
}