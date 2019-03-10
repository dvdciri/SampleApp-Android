package dev.dvdciri.sampleapp.framework

import io.reactivex.Scheduler

interface ScheduleProvider {

    fun io(): Scheduler
}