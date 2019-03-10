package dev.dvdciri.sampleapp.framework.viewmodel

import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import kotlin.test.assertEquals

class BaseViewModelTest {

    @Test
    fun `given base view model when onCleared then disposable cleared`() {
        // Given
        val cut = BaseViewModel()
        cut.compositeDisposable.add(mock())

        // When
        cut.onCleared()

        // Then
        assertEquals(0, cut.compositeDisposable.size())
    }
}