package io.github.obaya884.randomuser.domain

import android.service.controls.templates.ThumbnailTemplate
import java.util.jar.Attributes

data class RandomUser(
    val gender: String,
    val name: UserName,
    val country: String,
    val email: String,
    val phone: String,
    val picture: UserImage
    ) {

    data class UserName(
        val title: String,
        val first: String,
        val last: String
    )

    data class UserImage(
        val thumbnail: String
    )
}

