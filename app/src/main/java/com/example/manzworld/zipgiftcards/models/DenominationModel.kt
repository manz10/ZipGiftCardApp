package com.example.manzworld.zipgiftcards.models

import java.io.Serializable
import java.util.*

data class DenominationModel(
        var price: Double,
        var currency: Currency
):Serializable{

}