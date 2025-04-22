package com.onetree.andresvergara.tasky

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform