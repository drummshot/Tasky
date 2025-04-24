package com.onetree.andresvergara.tasky.domain


enum class ErrorCode(val code: String) {
    UNKNOWN("UNK_000"),
    INVALID_PARAMS("IVD_001"),
    DATABASE_INSERTION("DBI_002"),
    DATABASE_READING("DBR_003"),
    DATABASE_UPDATING("DBU_004"),
    DATABASE_DELETING("DBU_005"),
    NOT_IMPLEMENTED("NTI_006");

    companion object {
        fun fromCode(code: String): ErrorCode = entries.find { it.code == code } ?: UNKNOWN
    }
}