package com.onetree.andresvergara.tasky.domain.user

import com.onetree.andresvergara.tasky.domain.base.DomainObject

interface User : DomainObject {
    var email: String
    var password: String?
}