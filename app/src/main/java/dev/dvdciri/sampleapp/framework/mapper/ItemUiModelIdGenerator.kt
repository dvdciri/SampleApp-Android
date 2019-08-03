package dev.dvdciri.sampleapp.framework.mapper

import java.util.*


class ItemUiModelIdGenerator constructor(){

    fun generateId(): String {
        return UUID.randomUUID().toString()
    }
}