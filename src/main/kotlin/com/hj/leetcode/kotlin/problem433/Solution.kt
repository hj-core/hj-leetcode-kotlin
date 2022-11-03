package com.hj.leetcode.kotlin.problem433

/**
 * LeetCode page: [433. Minimum Genetic Mutation](https://leetcode.com/problems/minimum-genetic-mutation/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N) where N is the size of bank;
     */
    fun minMutation(start: String, end: String, bank: Array<String>): Int {
        val noMutationRequired = start == end
        if (noMutationRequired) return 0

        val validGenes = bank.toHashSet().apply { remove(start) }

        val isInvalidEndGene = end !in validGenes
        if (isInvalidEndGene) return -1

        return findMinNonZeroStepsToEndGene(0, listOf(start), validGenes, end)
    }

    private tailrec fun findMinNonZeroStepsToEndGene(
        currStep: Int,
        genesAtCurrStep: List<String>,
        validGenes: MutableSet<String>,
        endGene: String
    ): Int {
        val genesAtNextStep = mutableListOf<String>()

        for (gene in genesAtCurrStep) {
            val validGenesIterator = validGenes.iterator()

            while (validGenesIterator.hasNext()) {
                val validGene = validGenesIterator.next()
                val inOneMutation = inOneMutation(gene, validGene)

                if (inOneMutation) {
                    if (validGene == endGene) return currStep + 1

                    genesAtNextStep.add(validGene)
                    validGenesIterator.remove()
                }
            }
        }

        return if (genesAtNextStep.isEmpty()) {
            -1
        } else {
            findMinNonZeroStepsToEndGene(currStep + 1, genesAtNextStep, validGenes, endGene)
        }
    }

    private fun inOneMutation(gene: String, target: String): Boolean {
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