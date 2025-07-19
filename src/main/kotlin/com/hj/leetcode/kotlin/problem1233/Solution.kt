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
    val children: Map<String, TrieNode>
        get() = _children
    private var _children = mutableMapOf<String, TrieNode>()

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
        var start = 1
        while (start < folder.length) {
            val end =
                folder
                    .indexOf('/', start)
                    .let { if (it == -1) folder.length else it }
            val segment = folder.substring(start, end)

            node = node._children.computeIfAbsent(segment) { TrieNode() }
            if (node.index >= 0) {
                return
            }
            start = end + 1
        }
        node.index = index
        node._children = mutableMapOf()
    }
}
