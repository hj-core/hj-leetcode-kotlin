package com.hj.leetcode.kotlin.problem1233

/**
 * LeetCode page: [1233. Remove Sub-Folders from the Filesystem](https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the flattened length of folder.
     */
    fun removeSubfolders(folder: Array<String>): List<String> {
        val root = Trie()
        root.addTopFolders(folder) // exclude all subfolders

        return buildList {
            dfs(root) { node ->
                add(folder[node.terminationId])
            }
        }
    }

    private class Trie {
        val children = mutableMapOf<Char, Trie>()
        var terminationId = -1

        fun addTopFolders(allFolders: Array<String>) {
            for ((index, path) in allFolders.withIndex()) {
                merge(index, path)
            }
        }

        fun merge(
            id: Int,
            path: String,
            start: Int = 0,
        ) {
            if (start == path.length) {
                terminationId = id
                children.remove('/')
                return
            }
            if (path[start] == '/' && terminationId != -1) {
                return
            }
            children
                .computeIfAbsent(path[start]) { Trie() }
                .merge(id, path, start + 1)
        }
    }

    private fun dfs(
        root: Trie,
        onEachTermination: (node: Trie) -> Unit,
    ) {
        if (root.terminationId != -1) {
            onEachTermination(root)
        }
        for (child in root.children.values) {
            dfs(child, onEachTermination)
        }
    }
}
