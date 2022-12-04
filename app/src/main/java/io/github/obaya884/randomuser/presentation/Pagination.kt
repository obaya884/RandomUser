package io.github.obaya884.randomuser.presentation

data class Pagination(
    val page: Int,
    val pageSize: Int
) {
    val nextPage = page.inc()
    val reloadPositionFromLast = pageSize / 5

    companion object {
        fun createInitialPagination(pageSize: Int) =
            Pagination(page = 1, pageSize = pageSize)
    }
}