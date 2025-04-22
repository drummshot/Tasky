package com.onetree.andresvergara.tasky.domain

enum class Error(val code: String) {
    UNKNOWN("UNK_000"),
    INVALID_PARAMS("IVD_001");

    companion object {
        fun fromCode(code: String): Error = entries.find { it.code == code } ?: UNKNOWN
    }


}