package io.github.obaya884.randomuser.presentation

import com.airbnb.epoxy.TypedEpoxyController
import io.github.obaya884.randomuser.domain.RandomUser
import io.github.obaya884.randomuser.epoxyViewHolderRandomUser

class RandomUserListController : TypedEpoxyController<List<RandomUser>>() {
    override fun buildModels(users: List<RandomUser>?) {
        users?.forEach { user ->
            epoxyViewHolderRandomUser {
                id("id")
                userName(user.name.first + ' ' + user.name.last)
            }
        }
    }
}