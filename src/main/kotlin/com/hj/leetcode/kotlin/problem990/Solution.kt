package com.hj.leetcode.kotlin.problem990

/**
 * LeetCode page: [990. Satisfiability of Equality Equations](https://leetcode.com/problems/satisfiability-of-equality-equations/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of equations;
     */
    fun equationsPossible(equations: Array<String>): Boolean {
        val idPerLowercase = UnionFind()
        unifyIdByEqual(equations, idPerLowercase)
        idPerLowercase.optimizeInternalState()
        return validateNotEqual(equations, idPerLowercase)
    }

    private class UnionFind {
        private val numLowercase = 26
        private val parentIdPerLowercase = IntArray(numLowercase) { indexAsInitialId -> indexAsInitialId }

        fun unifyId(lowercase1: Char, lowercase2: Char) {
            val id1 = findId(lowercase1)
            val id2 = findId(lowercase2)
            if (id1 == id2) return

            val (smallId, largeId) = if (id1 < id2) id1 to id2 else id2 to id1
            updateParentId(lowercase1, smallId)
            updateParentId(lowercase2, smallId)

            val index = largeId
            updateParentId(index, smallId)
        }

        fun findId(lowercase: Char): Int {
            val index = getIndex(lowercase)
            return findId(index)
        }

        private fun getIndex(lowercase: Char): Int {
            require(lowercase.isLowerCase())
            return lowercase - 'a'
        }

        private tailrec fun findId(index: Int): Int {
            val parentId = parentIdPerLowercase[index]
            val isRootId = parentId == index
            return if (isRootId) parentId else findId(parentId)
        }

        private fun updateParentId(lowercase: Char, newId: Int) {
            val index = getIndex(lowercase)
            updateParentId(index, newId)
        }

        private fun updateParentId(index: Int, newId: Int) {
            parentIdPerLowercase[index] = newId
        }

        fun optimizeInternalState() {
            for (index in parentIdPerLowercase.indices) {
                parentIdPerLowercase[index] = findId(index)
            }
        }
    }

    private fun unifyIdByEqual(equations: Array<String>, idPerLowercase: UnionFind) {
        for (equation in equations) {
            if (equation[1] == '!') continue
            val (variable1, variable2) = getVariables(equation)
            idPerLowercase.unifyId(variable1, variable2)
        }
    }

    private fun getVariables(equation: String) = Pair(equation[0], equation[3])

    private fun validateNotEqual(equations: Array<String>, idPerLowercase: UnionFind): Boolean {
        for (equation in equations) {
            if (equation[1] == '=') continue
            val (variable1, variable2) = getVariables(equation)
            val id1 = idPerLowercase.findId(variable1)
            val id2 = idPerLowercase.findId(variable2)
            if (id1 == id2) return false
        }
        return true
    }
}