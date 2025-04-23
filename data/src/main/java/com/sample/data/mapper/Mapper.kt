package com.sample.data.mapper

import com.sample.data.model.UserDto
import com.sample.domain.model.UserInfo


fun UserDto.toDomain(): UserInfo {
    return UserInfo(
        firstName = name.first,
        lastName = name.last,
        address = "${location.street.number} ${location.street.name}",
        profilePicture = picture.large
    )
}