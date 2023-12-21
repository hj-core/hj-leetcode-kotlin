package com.hj.leetcode.kotlin.problem1472

/**
 * LeetCode page: [1472. Design Browser History](https://leetcode.com/problems/design-browser-history/);
 */
class BrowserHistory(homepage: String) {

    private var currPage = Page(homepage)

    private class Page(val url: String, var prev: Page? = null, var next: Page? = null)

    /* Complexity:
     * Time O(1) and Space O(L) where L is the length of url;
     */
    fun visit(url: String) {
        val newPage = Page(url = url, prev = currPage)
        currPage.next = newPage
        currPage = newPage
    }

    /* Complexity:
     * Time O(steps) and Space O(1);
     */
    fun back(steps: Int): String {
        var remainingSteps = steps
        while (remainingSteps > 0 && currPage.prev != null) {
            currPage = checkNotNull(currPage.prev)
            remainingSteps--
        }
        return currPage.url
    }

    /* Complexity:
     * Time O(steps) and Space O(1);
     */
    fun forward(steps: Int): String {
        var remainingSteps = steps
        while (remainingSteps > 0 && currPage.next != null) {
            currPage = checkNotNull(currPage.next)
            remainingSteps--
        }
        return currPage.url
    }
}