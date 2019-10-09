package com.example.manzworld.zipgiftcards.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.manzworld.zipgiftcards.models.GiftCards
import com.example.manzworld.zipgiftcards.R
import com.example.manzworld.zipgiftcards.activities.DetailPageActivity
import kotlinx.android.synthetic.main.layout_recycler_items.view.*
import java.util.*

class GiftCardRecyclerAdapter : RecyclerView.Adapter<GiftCardViewHolder>() {

    private var items: List<GiftCards> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GiftCardViewHolder {

        return GiftCardViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.layout_recycler_items, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: GiftCardViewHolder, p1: Int) {
        if (!items.isEmpty()) {
            p0.bind(items.get(p1))
        }
    }


    fun submitList(giftCards: List<GiftCards>) {
        val data = giftCards
        items = data.sortedWith(compareBy({it.brand}))
        notifyDataSetChanged()
    }
}


class GiftCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val card_image = itemView.card_image
    val brand = itemView.brand
    val vendor = itemView.vendor
    val discount = itemView.discount
    val btn_details = itemView.btn_details

    var id = "0"


    fun bind(giftCard: GiftCards) {
        brand.setText(giftCard.brand)
        vendor.setText("Vendor: "+giftCard.vendor)
        discount.setText(giftCard.discount+ "% OFF")
        id = giftCard.id

        Glide.with(itemView.context)
                .load(giftCard.image)
                .into(card_image)

        btn_details.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(v?.context, DetailPageActivity::class.java)
                intent.putExtra("selectedCard", giftCard)
                v?.context?.startActivity(intent)
            }
        })
    }



}

