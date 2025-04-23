package com.sample.data.model

data class UserResponse(
    val results: List<UserDto>
)

data class UserDto(
    val name: NameDto,
    val location: LocationDto,
    val picture: PictureDto
)

data class NameDto(val first: String, val last: String)
data class LocationDto(val street: StreetDto)
data class StreetDto(val number: Int, val name: String)
data class PictureDto(val large: String)

