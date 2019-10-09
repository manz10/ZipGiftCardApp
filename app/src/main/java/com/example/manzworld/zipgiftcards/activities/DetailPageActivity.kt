package com.example.manzworld.zipgiftcards.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.manzworld.zipgiftcards.R
import com.example.manzworld.zipgiftcards.models.DenominationModel
import com.example.manzworld.zipgiftcards.models.GiftCards
import kotlinx.android.synthetic.main.activity_detail_page.*
import kotlinx.android.synthetic.main.grid_item_layout.*
import kotlinx.android.synthetic.main.grid_item_layout.view.*

class DetailPageActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        val selectedGiftCard = intent.getSerializableExtra("selectedCard") as GiftCards
        lateinit var gridAdapter: GridAdapter

        Glide.with(this@DetailPageActivity)
                .load(selectedGiftCard.image)
                .into(detail_card_image)
        detail_brand.setText(selectedGiftCard.brand)
        detail_discount.setText(selectedGiftCard.discount + "% OFF")
        detail_term.setText((selectedGiftCard.terms))

        val selectedDeno = selectedGiftCard.denominations as List<DenominationModel>

        grid_denominations.apply {
            gridAdapter = GridAdapter(selectedDeno)
            adapter = gridAdapter
        }

        grid_denominations.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                btn_checkout.isEnabled = true


                val selectedItem = parent?.getItemAtPosition(position) as DenominationModel
                val selectedDenoPrice = selectedItem.price.toInt()

                btn_checkout.setOnClickListener(object : View.OnClickListener{
                    override fun onClick(v: View?) {

                        val url = "https://zip.co/giftcards/checkout/"+ selectedGiftCard.id+ "?denominations="+selectedDenoPrice
                        val intent = Intent(android.content.Intent.ACTION_VIEW)
                        intent.data = Uri.parse(url)
                        startActivity(intent)

                    }

                })
            }

        }


    }

    internal class GridAdapter constructor(val selectedDeno: List<DenominationModel>) : BaseAdapter() {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            val inflater = LayoutInflater.from(parent?.context)
            val view = inflater.inflate(R.layout.grid_item_layout, parent, false)

            view.curr.setText(selectedDeno.get(position).currency.toString())
            view.price.setText(selectedDeno.get(position).price.toString())

            return view
        }

        override fun getItem(position: Int): Any {
            return selectedDeno.get(position)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return selectedDeno.size
        }


    }
}
