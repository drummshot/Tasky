package com.onetree.andresvergara.tasky.domain

import com.onetree.andresvergara.tasky.domain.base.DomainObject

class Params<I : DomainObject> {
    val item: I? = null
    val id: String? = null
}