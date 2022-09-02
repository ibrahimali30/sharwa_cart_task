package com.ibrahim.sharwaTask.cart

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken




class ItemsRemoteDataSource {

    val json = "[\n" +
            "{\n" +
            "    \"name\": \"KFC\",\n" +
            "    \"id\": \"123\",\n" +
            "    \"icon\": \"kfc\",\n" +
            "    \"menuCategory\": [\n" +
            "    {\n" +
            "        \"id\": \"678\",\n" +
            "        \"name\": \"Desert 1\",\n" +
            "        \"icon\": \"kfc\",\n" +
            "        \"decscriptionText\": \"Desert 1 description\",\n" +
            "        \"price\": 12,\n" +
            "        \"currecy\": \"SR\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": \"1243\",\n" +
            "        \"name\": \"Desert 2\",\n" +
            "        \"icon\": \"kfc\",\n" +
            "        \"decscriptionText\": \"Desert 2 description\",\n" +
            "        \"price\": 14,\n" +
            "        \"currecy\": \"SR\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": \"9384\",\n" +
            "        \"name\": \"Pepsi\",\n" +
            "        \"icon\": \"kfc\",\n" +
            "        \"decscriptionText\": \"Pepsi description\",\n" +
            "        \"price\": 24,\n" +
            "        \"currecy\": \"SR\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": \"44433\",\n" +
            "        \"name\": \"7 up\",\n" +
            "        \"icon\": \"kfc\",\n" +
            "        \"decscriptionText\": \"7 up description\",\n" +
            "        \"price\": 14,\n" +
            "        \"currecy\": \"SR\"\n" +
            "    }\n" +
            "    ]\n" +
            "}\n" +
            "]"







    fun getMokedList(): ItemsResponse? {
        val gson = Gson()
        val token = object : TypeToken<ItemsResponse>() {}.type
        return gson.fromJson<ItemsResponse>(json, token)

    }

}


class ItemsResponse : ArrayList<ItemsResponseItem>()

data class ItemsResponseItem(
    val icon: String,
    val id: String,
    val menuCategory: List<MenuItem>,
    val name: String
)

data class MenuItem(
    val currecy: String,
    val decscriptionText: String,
    val icon: String,
    val id: String,
    val name: String,
    val price: Int,
    var isAddedToCart: Boolean = false
)