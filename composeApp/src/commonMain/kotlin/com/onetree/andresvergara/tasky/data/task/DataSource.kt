package com.onetree.andresvergara.tasky.data.task

import com.onetree.andresvergara.tasky.domain.task.BusinessObject
import com.onetree.andresvergara.tasky.domain.task.Task

interface DataSource<B : BusinessObject> {

    fun create(item: B): Task

    fun read(id: String): B

    fun update(item: B): B

    fun delete(id: String): Boolean

    fun list(): List<B>
}