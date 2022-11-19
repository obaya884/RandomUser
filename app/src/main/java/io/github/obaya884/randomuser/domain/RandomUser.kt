package io.github.obaya884.randomuser.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RandomUser(
    val gender: String,
    val name: UserName,
    val location: Location,
    val email: String,
    val phone: String,
    val picture: UserImage
) {
    data class UserName(
        val title: String,
        val first: String,
        val last: String
    )

    data class Location(
        val country: String
    )

    data class UserImage(
        val thumbnail: String
    )
}

