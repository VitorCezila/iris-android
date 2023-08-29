package com.ghn.iris.core.domain.use_case

import android.content.SharedPreferences
import com.ghn.iris.core.util.Constants

class GetOwnUserIdUseCase(
    private val sharedPreferences: SharedPreferences
) {
    operator fun invoke(): String {
        return (sharedPreferences.getString(Constants.KEY_USER_ID, "") ?: "")
    }
}