package com.example.manzworld.zipgiftcards.models

import java.io.Serializable

data class GiftCards(
        var id: String,
        var brand: String,
        var vendor: String,
        var discount: String,
        var image: String,
        var denominations: List<DenominationModel>,
        var terms: String
) : Serializable {

}