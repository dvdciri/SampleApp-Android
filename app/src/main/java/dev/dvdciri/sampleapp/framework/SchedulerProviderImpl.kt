package dev.dvdciri.sampleapp.framework

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchedulerProviderImpl @Inject constructor() : ScheduleProvider {

    override fun io(): Scheduler {
        return Schedulers.io()
    }
}