package com.hj.leetcode.kotlin.problem1948

/**
 * LeetCode page: [1948. Delete Duplicate Folders in System](https://leetcode.com/problems/delete-duplicate-folders-in-system/);
 */
class Solution {
    // Time O(?) and Space O(?).
    fun deleteDuplicateFolder(paths: List<List<String>>): List<List<String>> {
        val root = buildTrie(paths)
        // Entry = (path index, repr of subtrees)
        val indexReprs = mutableMapOf<Int, String>()
        val reprFreqs = mutableMapOf<String, Int>()

        dfsRepr(root) { index, repr ->
            indexReprs[index] = repr
            reprFreqs[repr] = (reprFreqs[repr] ?: 0) + 1
        }

        val result = mutableListOf<List<String>>()
        dfsUnique(root, indexReprs, reprFreqs) { index ->
            result.add(paths[index])
        }
        return result
    }

    private fun buildTrie(paths: List<List<String>>): Trie {
        val result = Trie()
        for (i in paths.indices) {
            result.insert(i, paths)
        }
        return result
    }

    // Returns a unique representation of root's subtrees, capturing
    // all equivalent structures. Returns an empty string for leaves,
    // which do not have any subtrees.
    private fun dfsRepr(
        root: Trie,
        onEachNode: (index: Int, subtreeRepr: String) -> Unit,
    ): String {
        if (root.childNodes.isEmpty()) {
            onEachNode(root.index, "")
            return ""
        }

        val builder = StringBuilder()
        builder.append("(")
        for (key in root.childNodes.keys.sorted()) {
            builder.append("$key:")
            builder.append(dfsRepr(root.childNodes[key]!!, onEachNode))
            builder.append(",")
        }
        builder.append(")")

        val result = builder.toString()
        onEachNode(root.index, result)
        return result
    }

    // Invokes onEachUnique on the retained nodes if duplicate folders
    // are deleted.
    private fun dfsUnique(
        root: Trie,
        indexReprs: Map<Int, String>,
        reprFreqs: Map<String, Int>,
        onEachUnique: (index: Int) -> Unit,
    ) {
        for ((_, child) in root.childNodes) {
            val repr = checkNotNull(indexReprs[child.index])
            if (repr == "" || checkNotNull(reprFreqs[repr]) == 1) {
                onEachUnique(child.index)
                dfsUnique(child, indexReprs, reprFreqs, onEachUnique)
            }
        }
    }
}

private class Trie {
    // The path index corresponding to this node. Note that since every
    // parent folder of a folder is also in the input, every node except
    // the root will have a corresponding index.
    var index = -1
        private set

    val childNodes get() = _childNodes as Map<String, Trie>
    private val _childNodes = mutableMapOf<String, Trie>()

    // Insert paths[index] to this node.
    fun insert(
        index: Int,
        paths: List<List<String>>,
    ) {
        var curr = this
        for (part in paths[index]) {
            curr = curr._childNodes.computeIfAbsent(part) { Trie() }
        }
        curr.index = index
    }
}
