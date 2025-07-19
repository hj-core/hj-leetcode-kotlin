package com.hj.leetcode.kotlin.problem1233

/**
 * LeetCode page: [1233. Remove Sub-Folders from the Filesystem](https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/);
 */
class Solution {
    /* Complexity:
     * Time O(M) and Space O(M) where M is the flattened length of folder.
     */
    fun removeSubfolders(folder: Array<String>): List<String> = NonSubFolders(folder).toList()
}

private class NonSubFolders(
    val folders: Array<String>,
) {
    private val root = TrieNode()

    init {
        for ((i, f) in folders.withIndex()) {
            root.mergeNew(i, f)
        }
    }

    fun toList(): List<String> =
        buildList {
            dfs(root) { add(folders[it]) }
        }

    private fun dfs(
        node: TrieNode,
        onEachFolder: (index: Int) -> Unit,
    ) {
        if (node.index >= 0) {
            onEachFolder(node.index)
        }
        for (child in node.children) {
            dfs(child.value, onEachFolder)
        }
    }
}

private class TrieNode {
    val children get() = _children as Map<Char, TrieNode>
    private val _children = mutableMapOf<Char, TrieNode>()

    var index = -1
        private set

    // Does nothing if the folder is a subfolder of an existing folder.
    // Otherwise, inserts the folder and removes all its subfolders
    // from the trie.
    fun mergeNew(
        index: Int,
        folder: String,
    ) {
        var node = this
        for (c in folder) {
            if (c == '/' && node.index >= 0) {
                return
            }
            node = node._children.computeIfAbsent(c) { TrieNode() }
        }
        node.index = index
        node._children.remove('/')
    }
}
