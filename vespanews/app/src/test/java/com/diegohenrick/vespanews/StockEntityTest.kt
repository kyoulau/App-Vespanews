package com.diegohenrick.vespanews

import com.diegohenrick.vespanews.feature.data.local.entity.StockEntity
import com.diegohenrick.vespanews.feature.data.local.entity.StockList
import org.junit.Assert.assertEquals
import org.junit.Test

class StockEntityTest {

    @Test
    fun `create StockEntity with valid data`() {
        val stock = StockEntity(
            name = "Test Company",
            price = 150.0,
            day_change = 5.0
        )

        assertEquals("Test Company", stock.name)
        assertEquals(150.0, stock.price, 0.01)
        assertEquals(5.0, stock.day_change, 0.01)
    }

    @Test
    fun `create StockList with multiple StockEntities`() {
        val stockList = StockList(
            data = listOf(
                StockEntity("Company A", 100.0, 2.0),
                StockEntity("Company B", 200.0, -1.5),
                StockEntity("Company C", 300.0, 3.5)
            )
        )

        assertEquals(3, stockList.data.size)
        assertEquals("Company A", stockList.data[0].name)
        assertEquals(200.0, stockList.data[1].price, 0.01)
        assertEquals(-1.5, stockList.data[1].day_change, 0.01)
    }
}
