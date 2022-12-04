package io.github.obaya884.randomuser.presentation

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import io.github.obaya884.randomuser.epoxyViewHolderRandomUser

class RandomUserListController(
    private val listener: Listener
) : TypedEpoxyController<RandomUserListUiModel>() {
    override fun buildModels(uiModel: RandomUserListUiModel) {
        uiModel.users?.forEachIndexed { index, user ->
            epoxyViewHolderRandomUser {
                id(user.email + index.toString())
                iconUrl(user.picture.thumbnail)
                userName(user.name.first + ' ' + user.name.last)

                onVisibilityStateChanged { model, view, visibilityState ->
                    val willReloadIndex =
                        uiModel.users.lastIndex - uiModel.pagination.reloadPositionFromLast
                    if (index == willReloadIndex) {
                        if (visibilityState == View.VISIBLE) {
                            this@RandomUserListController.listener.onNextPage()
                        }
                    }
                }
            }
        }
    }

    interface Listener {
        fun onNextPage()
    }
}