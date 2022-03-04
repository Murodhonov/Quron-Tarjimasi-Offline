package uz.umarxon.qurontarjimasioffline.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.umarxon.qurontarjimasioffline.databinding.ItemRvBinding
import uz.umarxon.qurontarjimasioffline.models.quranInfo.Chapter
import uz.umarxon.qurontarjimasioffline.models.quranInfo.QuranInfo

class RvAdapter(private val list: List<Chapter>, val rvClick: rv_click) :
    RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(chapter: Chapter, position: Int) {

            itemRv.name.text = chapter.name
            itemRv.pos.text = (position+1).toString()

            val location = if (chapter.revelation == "Mecca"){
                "Makkada nozil bo'lgan"
            }else{
                "Madinada nozil bo'lgan"
            }

            val countOfVerse = chapter.verses.size

            itemRv.location.text = "$location | $countOfVerse oyatdan iborat"
            itemRv.root.setOnClickListener {
                rvClick.click(chapter, position)
            }
        }
    }


    interface rv_click {
        fun click(chapter: Chapter, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}