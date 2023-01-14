package com.hj.leetcode.kotlin.problem1061

/**
 * LeetCode page: [1061. Lexicographically Smallest Equivalent String](https://leetcode.com/problems/lexicographically-smallest-equivalent-string/);
 */
class Solution {
    /* Complexity:
     * Time O(M+N) and Space O(1) where M and N are the length of s1 and baseStr;
     */
    fun smallestEquivalentString(s1: String, s2: String, baseStr: String): String {
        return EquivalencyUnionFind().let {
            it.union(s1, s2)
            it.findSmallestEquivalentString(baseStr)
        }
    }

    private class EquivalencyUnionFind {
        /* Store the equivalency between lowercase. A lowercase is the parent of another if they are
         * equivalent and its id is smaller. Here the so-called id is the distance from lowercase to
         * 'a'. The initial parent of a lowercase is set to itself;
         */
        private val parentIds = IntArray(26) { it }

        fun findSmallestParent(char: Char): Char {
            val id = char.id()
            return 'a' + findSmallestParentId(id)
        }

        private fun Char.id(): Int {
            require(this in 'a'..'z') { "Require lowercase" }
            return this - 'a'
        }

        private fun findSmallestParentId(id: Int): Int {
            var currId = id
            while (currId != parentIds[currId]) {
                currId = parentIds[currId]
            }
            return currId
        }

        fun union(char1: Char, char2: Char) {
            unionBySmallerParentId(char1.id(), char2.id())
        }

        private fun unionBySmallerParentId(id1: Int, id2: Int) {
            val parentId1 = findSmallestParentId(id1)
            val parentId2 = findSmallestParentId(id2)
            val (small, large) = if (parentId1 <= parentId2) parentId1 to parentId2 else parentId2 to parentId1

            parentIds[id1] = small
            parentIds[id2] = small
            parentIds[large] = small
        }
    }

    private fun EquivalencyUnionFind.union(s1: String, s2: String) {
        require(s1.length == s2.length)
        for (index in s1.indices) {
            union(s1[index], s2[index])
        }
    }

    private fun EquivalencyUnionFind.findSmallestEquivalentString(baseStr: String): String {
        return buildString {
            for (char in baseStr) {
                val smallestEquivalentChar = findSmallestParent(char)
                append(smallestEquivalentChar)
            }
        }
    }
}