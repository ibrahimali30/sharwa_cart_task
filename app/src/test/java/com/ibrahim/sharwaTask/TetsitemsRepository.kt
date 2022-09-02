package com.ibrahim.sharwaTask

import com.google.common.truth.Truth
import com.ibrahim.sharwaTask.cart.data.ItemsRemoteDataSource
import com.ibrahim.sharwaTask.cart.data.ItemsRepositoryImpl
import com.ibrahim.sharwaTask.cart.domain.entity.MenuItem
import com.testUtil.getTestMenuItemsList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TetsitemsRepository {

    private lateinit var itemsRepository: ItemsRepositoryImpl
    @MockK
    private lateinit var itemsRemoteDataSource: ItemsRemoteDataSource

    val menuCategory: List<MenuItem> = getTestMenuItemsList()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        itemsRepository = ItemsRepositoryImpl(itemsRemoteDataSource)

    }

    @Test
    fun test_itemsRepository_getItems() = runBlocking{

        every {
            itemsRemoteDataSource.getMokedList()
        }returns menuCategory

        itemsRemoteDataSource.getMokedList()

        val emissions = itemsRepository.getItems().toList()

        Truth.assertThat(menuCategory.size).isEqualTo(emissions[0].size)
        Truth.assertThat(menuCategory.first()).isEqualTo(emissions[0].first())


        verify {
            itemsRemoteDataSource.getMokedList()
        }

    }

}