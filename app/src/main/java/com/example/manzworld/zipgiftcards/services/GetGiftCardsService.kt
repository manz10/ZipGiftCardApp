package com.example.manzworld.zipgiftcards.services

import com.example.manzworld.zipgiftcards.models.GiftCards
import com.google.gson.JsonArray
import com.google.gson.annotations.JsonAdapter
import retrofit2.Call
import retrofit2.http.GET

interface GetGiftCardsService {

    @GET("giftcards/api/giftcards")
    fun getAllCards(): Call<JsonArray>
}