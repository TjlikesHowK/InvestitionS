package com.example.investitions.adapters

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import okhttp3.OkHttpClient
import okhttp3.Request

const val apiKey = "ck8c23pr01qmbnh420r0ck8c23pr01qmbnh420rg"

fun getCompanyData(ticker: String): JsonObject {
    return runBlocking {
        return@runBlocking companyData(ticker)
    }
}

fun getTickersData(ticker: String): JsonObject {
    return runBlocking {
        return@runBlocking tickersData(ticker)
    }
}

suspend fun companyData(ticker: String): JsonObject {
    val apiUrl = "https://finnhub.io/api/v1/stock/profile2?symbol=$ticker&token=$apiKey"

    return withContext(Dispatchers.IO) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(apiUrl)
            .build()

        val response = client.newCall(request).execute()
        val data = response.body?.string()
        val json = Json.parseToJsonElement(data.toString()).jsonObject

        return@withContext json
    }
}

suspend fun tickersData(ticker: String): JsonObject {
    val apiUrl = "https://finnhub.io/api/v1/search?q=$ticker&token=$apiKey"

    return withContext(Dispatchers.IO) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(apiUrl)
            .build()

        val response = client.newCall(request).execute()
        val data = response.body?.string()
        val json = Json.parseToJsonElement(data.toString()).jsonObject

        return@withContext json
    }
}