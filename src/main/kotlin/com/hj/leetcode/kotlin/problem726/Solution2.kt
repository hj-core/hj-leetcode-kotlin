package com.hj.leetcode.kotlin.problem726

class Solution2 {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the length of formula;
     */
    fun countOfAtoms(formula: String): String {
        val pIndicesStack = mutableListOf<Int>()
        // Index of left parentheses to the index of right parentheses that closes it
        val pIndices = mutableMapOf<Int, Int>()
        // Index of right parentheses to the count followed it
        val pCounts = mutableMapOf<Int, Int>()

        for ((index, c) in formula.withIndex()) {
            when (c) {
                '(' -> pIndicesStack.add(index)
                ')' -> {
                    val multiplier = getCountStr(formula, index + 1).let {
                        if (it.isEmpty()) 1 else it.toInt()
                    }
                    pIndices[pIndicesStack.removeLast()] = index
                    pCounts[index] = multiplier
                }
            }
        }

        var multiplier = 1
        val multipliers = IntArray(formula.length)
        for ((index, c) in formula.withIndex()) {
            when (c) {
                '(' -> multiplier *= checkNotNull(pCounts[pIndices[index]])
                ')' -> multiplier /= checkNotNull(pCounts[index])
            }
            multipliers[index] = multiplier
        }

        val atomCounts = mutableMapOf<String, Int>()
        var index = 0
        while (index < formula.length) {
            if (formula[index].isUpperCase()) {
                val name = getName(formula, index)
                val countStr = getCountStr(formula, index + name.length)
                val count = if (countStr.isEmpty()) 1 else countStr.toInt()
                val effectiveCount = count * multipliers[index]
                atomCounts.compute(name) { _, existingCount -> (existingCount ?: 0) + effectiveCount }
                index += name.length + countStr.length
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

    private fun getCountStr(formula: String, start: Int): String {
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