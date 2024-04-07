package com.wtfih.heartstitcher.data

data class LogInUIState (
    var email : String = "",
    var password : String = "",

    var emailError: Boolean = false,
    var passwordError: Boolean = false,
)
