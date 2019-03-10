package dev.dvdciri.sampleapp.framework.mapper

import java.util.*
import javax.inject.Inject

class ItemUiModelIdGenerator @Inject constructor(){

    fun generateId(): String {
        return UUID.randomUUID().toString()
    }
}