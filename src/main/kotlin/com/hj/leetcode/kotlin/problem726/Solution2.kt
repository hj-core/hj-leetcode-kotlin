package com.hj.leetcode.kotlin.problem726

class Solution2 {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the length of formula;
     */
    fun countOfAtoms(formula: String): String {
        val pIndicesStack = mutableListOf<Int>()
        // Index of left parentheses to index of its closing right parentheses
        val pIndices = mutableMapOf<Int, Int>()
        // Index of right parentheses to the multiplier following it
        val pMultipliers = mutableMapOf<Int, Int>()

        for ((index, c) in formula.withIndex()) {
            when (c) {
                '(' -> pIndicesStack.add(index)
                ')' -> {
                    val multiplier = getMultiplierStr(formula, index + 1)
                        .let { if (it.isEmpty()) 1 else it.toInt() }
                    pIndices[pIndicesStack.removeLast()] = index
                    pMultipliers[index] = multiplier
                }
            }
        }

        var extraMultiplier = 1
        val extraMultipliers = IntArray(formula.length)
        for ((index, c) in formula.withIndex()) {
            when (c) {
                '(' -> extraMultiplier *= checkNotNull(pMultipliers[pIndices[index]])
                ')' -> extraMultiplier /= checkNotNull(pMultipliers[index])
            }
            extraMultipliers[index] = extraMultiplier
        }

        val atomCounts = mutableMapOf<String, Int>()
        var index = 0
        while (index < formula.length) {
            if (formula[index].isUpperCase()) {
                val name = getName(formula, index)
                val multiplierStr = getMultiplierStr(formula, index + name.length)
                val multiplier = if (multiplierStr.isEmpty()) 1 else multiplierStr.toInt()
                val overallMultiplier = multiplier * extraMultipliers[index]
                atomCounts.compute(name) { _, oldCount -> (oldCount ?: 0) + overallMultiplier }
                index += name.length + multiplierStr.length
            } else {
                index++
            }
        }
        return toOutput(atomCounts)
    }

    private fun getName(formula: String, start: Int): String {
        if (formula.length <= start || !formula[start].isUpperCase()) {
            return ""
        }
        val endExclusive = (start + 1..<formula.length).find {
            !formula[it].isLowerCase()
        } ?: formula.length
        return formula.substring(start, endExclusive)
    }

    private fun getMultiplierStr(formula: String, start: Int): String {
        if (formula.length <= start || formula[start] !in '0'..'9') {
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