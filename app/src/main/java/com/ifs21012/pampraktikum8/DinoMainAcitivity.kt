package com.ifs21012.pampraktikum8

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21012.pampraktikum8.databinding.ActivityDinoMainAcitivityBinding

class DinoMainAcitivity : AppCompatActivity() {
    private lateinit var binding: ActivityDinoMainAcitivityBinding
    private val dataDinosss = ArrayList<Dinosss>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinoMainAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvDinosss.setHasFixedSize(false)
        dataDinosss.addAll(getListDinosss())
        showRecyclerList()

    }

    @SuppressLint("Recycle")
    private fun getListDinosss(): ArrayList<Dinosss> {
        val dataName =
            resources.getStringArray(R.array.dino_name)
        val dataIcon =
            resources.obtainTypedArray(R.array.dino_icon)
        val dataDescription =
            resources.getStringArray(R.array.dino_deskripsi)
        val dataKarakteristik =
            resources.getStringArray(R.array.dino_Karakteristik)
        val dataKelompok =
            resources.getStringArray(R.array.dino_Kelompok)
        val dataHabitat =
            resources.getStringArray(R.array.dino_Habitat)
        val dataPanjangTinggiBobot =
            resources.getStringArray(R.array.dino_PanjangTinggiBobot)
        val dataKelemahan =
            resources.getStringArray(R.array.dino_Kelemahan)
        val dataMakanan =
            resources.getStringArray(R.array.dino_Makanan)

        val listFamili = ArrayList<Dinosss>()
        for (i in dataName.indices) {
            val famili = Dinosss(
                dataName[i],
                dataIcon.getResourceId(i, -1), dataDescription[i], dataKarakteristik[i], dataKelompok[i],
                dataHabitat[i],dataPanjangTinggiBobot[i],dataKelemahan[i],dataMakanan[i],)
            listFamili.add(famili)
        }
        return listFamili
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE
        ) {
            binding.rvDinosss.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvDinosss.layoutManager =
                LinearLayoutManager(this)
        }
        val listDinosssAdapter = ListDinosssAdapter(dataDinosss)
        binding.rvDinosss.adapter = listDinosssAdapter
        listDinosssAdapter.setOnItemClickCallback(object :
            ListDinosssAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dinosss) {
                showSelectedDinosss(data)
            }
        })
    }

    private fun showSelectedDinosss(dinosss: Dinosss) {
        val intentWithData = Intent(this@DinoMainAcitivity, DinoDetailAcitivity::class.java)
        intentWithData.putExtra(DinoDetailAcitivity.EXTRA_FAMILI, dinosss)
        startActivity(intentWithData)
    }

    companion object {
        const val EXTRA_FAMILI = "extra_famili"
    }
}