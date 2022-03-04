package uz.umarxon.qurontarjimasioffline.models.quran

import java.io.Serializable

data class QuranX(
    val chapter: Int,
    val text: String,
    val verse: Int
):Serializable