package com.onetree.andresvergara.tasky.domain.base

import com.onetree.andresvergara.tasky.domain.Params

interface UseCase<I : DomainObject, R> {
    suspend fun invoke(params: Params<I>? = null): Result<R>
}