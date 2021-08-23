package com.example.composeuibasic.utils


data class RandomUser(
    val name: String = "이름",
    val description: String = "설명",
    val profileImage: String = "https://randomuser.me/api/portraits/women/76.jpg"
)

object DummyDataProvider {

    val userList = List<RandomUser>(200) { RandomUser() }

}
