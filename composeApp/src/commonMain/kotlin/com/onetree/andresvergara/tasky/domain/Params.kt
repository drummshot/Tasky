package com.onetree.andresvergara.tasky.domain

import com.onetree.andresvergara.tasky.domain.base.DomainObject

class Params<I : DomainObject> {
    var item: I? = null
    var id: Long? = null
}