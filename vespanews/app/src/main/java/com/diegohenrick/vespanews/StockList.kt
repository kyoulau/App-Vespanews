package com.diegohenrick.vespanews

class StockList {

    var token =  "zRFxefSLQYlknGaqTyLORfWH6dHWROkR0HeZVOwH"

    fun filterStocks(stockList: List<Stock>, filter: String): List<Stock> {
        return stockList.filter { it.symbol.contains(filter, ignoreCase = true) }
    }
}