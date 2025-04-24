package com.onetree.andresvergara.tasky.domain

sealed class AppException(
    override val message: String,
    val errorCode: ErrorCode = ErrorCode.UNKNOWN,
    cause: Throwable? = null
) : Throwable(message, cause) {

    override fun toString(): String {
        return "[$errorCode] $message"
    }

    class ValidationException(
        message: String = "Validation error",
        errorCode: ErrorCode = ErrorCode.UNKNOWN,
        cause: Throwable? = null
    ) : AppException(message = message, cause = cause, errorCode = errorCode)

    class DataException(
        message: String = "Database error",
        errorCode: ErrorCode = ErrorCode.UNKNOWN,
        cause: Throwable? = null
    ) : AppException(message = message, cause = cause, errorCode = errorCode)

    class UnknownException(
        message: String = "Unknown error",
        errorCode: ErrorCode = ErrorCode.UNKNOWN,
        cause: Throwable? = null
    ) : AppException(message = message, cause = cause, errorCode = errorCode)

    class NotImplementedException(
        message: String = "Not implemented error",
        errorCode: ErrorCode = ErrorCode.NOT_IMPLEMENTED,
        cause: Throwable? = null
    ) : AppException(message = message, cause = cause, errorCode = errorCode)


}