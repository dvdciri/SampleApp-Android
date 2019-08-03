package dev.dvdciri.sampleapp.framework

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class SchedulerProviderImpl : ScheduleProvider {

    override fun io(): Scheduler {
        return Schedulers.io()
    }
}