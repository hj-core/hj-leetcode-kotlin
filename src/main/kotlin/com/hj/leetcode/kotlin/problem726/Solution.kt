package com.hj.leetcode.kotlin.problem726

/**
 * LeetCode page: [726. Number of Atoms](https://leetcode.com/problems/number-of-atoms/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N) where N is the length of formula;
     */
    fun countOfAtoms(formula: String): String {
        val atomStack = mutableListOf<Map<String, Int>>()
        val leftPIndices = mutableListOf<Int>()
        var i = 0
        while (i < formula.length) {
            when (formula[i]) {
                '(' -> {
                    leftPIndices.add(atomStack.size)
                    atomStack.add(mapOf())
                    i++
                }

                ')' -> {
                    val unionFrom = leftPIndices.last() + 1
                    val multiplierStr = getMultiplierStr(formula, i + 1)
                    val multiplier = if (multiplierStr.isEmpty()) 1 else multiplierStr.toInt()
                    val union = unionAll(atomStack, unionFrom, atomStack.size, multiplier)

                    // Pop the stacks up to and include last left parentheses
                    repeat(atomStack.size - leftPIndices.last()) {
                        atomStack.removeLast()
                    }
                    leftPIndices.removeLast()

                    atomStack.add(union)
                    i += 1 + multiplierStr.length
                }

                else -> {
                    val name = getName(formula, i)
                    val multiplierStr = getMultiplierStr(formula, i + name.length)
                    val multiplier = if (multiplierStr.isEmpty()) 1 else multiplierStr.toInt()
                    atomStack.add(mutableMapOf(name to multiplier))
                    i += name.length + multiplierStr.length
                }
            }
        }

        val resultCount = unionAll(atomStack, 0, atomStack.size, 1)
        return toOutput(resultCount)
    }

    private fun unionAll(
        maps: List<Map<String, Int>>,
        start: Int,
        endExclusive: Int,
        valueMultiplier: Int,
    ): Map<String, Int> {
        val result = mutableMapOf<String, Int>()
        for (i in start..<endExclusive) {
            for ((key, value) in maps[i]) {
                result.compute(key) { _, oldValue -> (oldValue ?: 0) + value * valueMultiplier }
            }
        }
        return result
    }

    private fun getName(formula: String, start: Int): String {
        if (start !in formula.indices || !formula[start].isUpperCase()) {
            return ""
        }
        val endExclusive = (start + 1..<formula.length).find {
            !formula[it].isLowerCase()
        } ?: formula.length
        return formula.substring(start, endExclusive)
    }

    private fun getMultiplierStr(formula: String, start: Int): String {
        if (start !in formula.indices || formula[start] !in '0'..'9') {
            return ""
        }
        val endExclusive = (start + 1..<formula.length).find {
            formula[it] !in '0'..'9'
        } ?: formula.length
        return formula.substring(start, endExclusive)
    }

    private fun toOutput(atomCounts: Map<String, Int>): String = buildString {
        val sortedNames = atomCounts.keys.sorted()
        for (name in sortedNames) {
            append(name)
            if (atomCounts[name] != 1) {
                append(atomCounts[name])
            }
        }
    }
}