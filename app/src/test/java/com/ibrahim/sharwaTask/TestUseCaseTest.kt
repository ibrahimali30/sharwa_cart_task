package com.ibrahim.sharwaTask

import com.google.common.truth.Truth
import com.ibrahim.sharwaTask.cart.data.ItemsRemoteDataSource
import com.ibrahim.sharwaTask.cart.data.ItemsRepositoryImpl
import com.ibrahim.sharwaTask.cart.domain.entity.MenuItem
import com.ibrahim.sharwaTask.cart.domain.interactor.CartOperationsUsecase
import com.ibrahim.sharwaTask.cart.domain.interactor.GetItemsUsecase
import com.testUtil.getTestMenuItemsList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TestUseCaseTest {


    lateinit var getItemsUsecase: GetItemsUsecase
    lateinit var operationsUsecase: CartOperationsUsecase
    @MockK
    private lateinit var itemsRepository: ItemsRepositoryImpl

    val menuCategory: List<MenuItem> = getTestMenuItemsList()


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getItemsUsecase = GetItemsUsecase(itemsRepository)
        operationsUsecase = CartOperationsUsecase(itemsRepository)
    }

    @Test
    fun test_getItemsUseCase_getItems() =  runBlocking {
        every {
            itemsRepository.getItems()
        }returns flow {
            emit(menuCategory)
        }

        itemsRepository.getItems()

        val emissions = getItemsUsecase.excute().toList()

        Truth.assertThat(menuCategory.size).isEqualTo(emissions[0].size)
        Truth.assertThat(menuCategory.first()).isEqualTo(emissions[0].first())

        verify {
            itemsRepository.getItems()
        }
    }

    @Test
    fun test_getItemsUseCase_addToCart() =  runBlocking {
        val menuItem = menuCategory.first()
        every {
            itemsRepository.addItemToCart(menuItem)
        }returns flow {
            emit(menuItem)
        }

        itemsRepository.addItemToCart(menuItem)

        val emissions = operationsUsecase.addItemToCart(menuItem).toList()

        Truth.assertThat(menuItem.name).isEqualTo(emissions[0].name)

        verify {
            itemsRepository.addItemToCart(menuItem)
        }
    }

    @Test
    fun test_getItemsUseCase_removeFromCart() =  runBlocking {
        val menuItem = menuCategory.first()
        every {
            itemsRepository.removeItemFromCart(menuItem)
        }returns flow {
            emit(menuItem)
        }

        itemsRepository.removeItemFromCart(menuItem)

        val emissions = operationsUsecase.removeItemFromCart(menuItem).toList()

        Truth.assertThat(menuItem.name).isEqualTo(emissions[0].name)

        verify {
            itemsRepository.removeItemFromCart(menuItem)
        }
    }

}