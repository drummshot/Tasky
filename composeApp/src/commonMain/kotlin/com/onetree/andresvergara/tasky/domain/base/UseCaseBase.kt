package com.onetree.andresvergara.tasky.domain.base

import com.onetree.andresvergara.tasky.domain.Params

abstract class BaseUseCase<I : DomainObject, R> : UseCase<I, R> {

    protected abstract suspend fun execute(params: Params<I>? = null): Result<R?>

    override suspend fun invoke(params: Params<I>?): Result<R?> {
        return execute(params)
    }
}