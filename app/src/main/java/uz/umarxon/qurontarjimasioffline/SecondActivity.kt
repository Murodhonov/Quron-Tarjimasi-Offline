package uz.umarxon.qurontarjimasioffline

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.umarxon.qurontarjimasioffline.adapter.RvAdapter2
import uz.umarxon.qurontarjimasioffline.databinding.BottomSheetBinding
import uz.umarxon.qurontarjimasioffline.models.quran.Quran
import uz.umarxon.qurontarjimasioffline.models.quran.QuranX
import uz.umarxon.qurontarjimasioffline.models.quranInfo.Chapter

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        supportActionBar?.hide()

        val chapterList = intent.getSerializableExtra("chapter") as Chapter

        val jsonFileString = getJsonDataFromAsset(applicationContext, "quran_uzb.json")!!
        val jsonFileString2 = getJsonDataFromAsset(applicationContext, "quran_original.json")!!

        val gson = Gson()

        val lisType = object : TypeToken<Quran>() {}.type
        val lisType2 = object : TypeToken<Quran>() {}.type

        val quranTranslatedText: Quran = gson.fromJson(jsonFileString, lisType)
        val quranText2: Quran = gson.fromJson(jsonFileString2, lisType)

        val list2 = ArrayList<QuranX>()
        val list3 = ArrayList<QuranX>()

        val count = chapterList.verses.size
        var count2 = 0

        for (i in quranTranslatedText.quran) {
            if (chapterList.chapter == i.chapter) {
                Log.d("Looping", "$count2 => $count")
                list2.add(i)
                if (count2 == count) break else count2 += 1
            }
        }

        val count3 = chapterList.verses.size
        var count4 = 0

        for (i in quranText2.quran) {
            if (chapterList.chapter == i.chapter) {
                Log.d("Looping2", "$count4 => $count3")
                list3.add(i)
                if (count4 == count3) break else count4 += 1
            }
        }

        findViewById<TextView>(R.id.name).text = chapterList.name

        findViewById<RecyclerView>(R.id.rv2).adapter = RvAdapter2(list2, list3,object :
            RvAdapter2.rv_click {
            override fun click(chapter: QuranX, position: Int) {
                val d = BottomSheetDialog(this@SecondActivity)

                val i = BottomSheetBinding.inflate(layoutInflater)

                i.name.text = chapter.text
                i.detail.text = "${chapter.verse} - Оят , ${chapterList.verses[position].juz} - Жуз"

                d.setContentView(i.root)

                d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                d.setCancelable(true)

                d.show()
            }
        })

    }

}