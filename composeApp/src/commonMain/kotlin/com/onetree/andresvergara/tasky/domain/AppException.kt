package com.onetree.andresvergara.tasky.domain

sealed class AppException(
    override val message: String,
    val code: Error = Error.UNKNOWN,
    cause: Throwable? = null
) : Throwable(message, cause) {

    override fun toString(): String {
        return "[$code] $message"
    }

    class ValidationException(
        message: String = "Validation error",
        code: Error = Error.UNKNOWN,
        cause: Throwable? = null
    ) : AppException(message = message, cause = cause, code = code)

    class DataException(
        message: String = "Database error",
        code: Error = Error.UNKNOWN,
        cause: Throwable? = null
    ) : AppException(message = message, cause = cause, code = code)

    class UnknownException(
        message: String = "Unknown error",
        code: Error = Error.UNKNOWN,
        cause: Throwable? = null
    ) : AppException(message = message, cause = cause, code = code)
}