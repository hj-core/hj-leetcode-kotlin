package com.hj.leetcode.kotlin.problem433

/**
 * LeetCode page: [433. Minimum Genetic Mutation](https://leetcode.com/problems/minimum-genetic-mutation/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N) where N is the size of bank;
     */
    fun minMutation(start: String, end: String, bank: Array<String>): Int {
        val allowedMutations = bank.toHashSet().apply { remove(start) }

        if (start == end) return 0
        if (end !in allowedMutations) return -1

        return findMinNonZeroMutation(0, listOf(start), allowedMutations, end)
    }

    private tailrec fun findMinNonZeroMutation(
        currDepth: Int,
        genesAtCurrDepth: List<String>,
        allowedMutations: MutableSet<String>,
        end: String
    ): Int {
        val genesAtNextDepth = mutableListOf<String>()

        for (gene in genesAtCurrDepth) {
            val mutationsIterator = allowedMutations.iterator()

            while (mutationsIterator.hasNext()) {
                val mutation = mutationsIterator.next()
                val isValidMutation = isValidMutation(gene, mutation)

                if (isValidMutation) {
                    if (mutation == end) return currDepth + 1

                    genesAtNextDepth.add(mutation)
                    mutationsIterator.remove()
                }
            }
        }

        return if (genesAtNextDepth.isEmpty()) {
            -1
        } else {
            findMinNonZeroMutation(currDepth + 1, genesAtNextDepth, allowedMutations, end)
        }
    }

    private fun isValidMutation(gene: String, target: String): Boolean {
        require(gene.length == target.length)
        var numDiff = 0

        for (index in gene.indices) {
            if (gene[index] != target[index]) {
                if (numDiff == 0) numDiff++ else return false
            }
        }
        return true
    }
}