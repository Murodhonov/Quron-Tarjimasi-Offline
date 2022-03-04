package uz.umarxon.qurontarjimasioffline.models.quranInfo

import java.io.Serializable

data class Verse(
    val juz: Int,
    val line: Int,
    val manzil: Int,
    val maqra: Int,
    val page: Int,
    val ruku: Int,
    val sajda: Any,
    val verse: Int
):Serializable