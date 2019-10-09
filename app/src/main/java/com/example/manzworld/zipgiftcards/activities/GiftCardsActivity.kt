package com.example.manzworld.zipgiftcards.activities

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.manzworld.zipgiftcards.R
import com.example.manzworld.zipgiftcards.services.GetGiftCardsService
import com.example.manzworld.zipgiftcards.adapters.GiftCardRecyclerAdapter
import com.example.manzworld.zipgiftcards.models.GiftCards
import com.example.manzworld.zipgiftcards.services.ServiceBuilder
import com.google.gson.Gson
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.activity_gift_cards.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GiftCardsActivity : Activity() {

    private lateinit var giftCardAdapter: GiftCardRecyclerAdapter
    var allGiftCards: List<GiftCards> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gift_cards)

        recycler.apply {
            layoutManager = LinearLayoutManager(this@GiftCardsActivity)
            giftCardAdapter = GiftCardRecyclerAdapter()
            getAllData()
            adapter = giftCardAdapter

        }


    }

    private fun getAllData() {

        val service = ServiceBuilder.buildService(GetGiftCardsService::class.java)

        val call = service.getAllCards()

        call?.enqueue(object : Callback<JsonArray> {
            override fun onFailure(call: Call<JsonArray>?, t: Throwable?) {
                Toast.makeText(this@GiftCardsActivity, "Error reading JSON data", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<JsonArray>?, response: Response<JsonArray>?) {

                //parsing JSONArray to list<GiftCards>
                allGiftCards = Gson().fromJson(response?.body(), Array<GiftCards>::class.java).toList();
                giftCardAdapter.submitList(allGiftCards)

            }

        })


    }


}
