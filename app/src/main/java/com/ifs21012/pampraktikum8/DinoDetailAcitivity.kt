package com.ifs21012.pampraktikum8

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21012.pampraktikum8.databinding.ActivityDinoDetailAcitivityBinding

class DinoDetailAcitivity : AppCompatActivity() {
    private lateinit var binding: ActivityDinoDetailAcitivityBinding
    private var dino: Dinosss? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinoDetailAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dino = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAMILI,
                Dinosss::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILI)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dino != null) {
            supportActionBar?.title = "Buah ${dino!!.name}"
            loadData(dino!!)
        } else {
            finish()
        }
    }
    private fun loadData(dino: Dinosss) {
        binding.ivDetailIcondino.setImageResource(dino.icon)
        binding.tvDetailNamedino.text = dino.name
        binding.tvDetaildescriptiondino.text = dino.description
        binding.tvDetailKarakteristikdino.text = dino.Karakteristik
        binding.tvDetailKelompokdino.text = dino.Kelompok
        binding.tvDetailHabitatdino.text = dino.Habitat
        binding.tvDetailPanjangTinggiBobot.text = dino.PanjangTinggiBobot
        binding.tvDetailKelemahan.text = dino.Kelemahan
        binding.tvDetailMakanan.text = dino.Makanan
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    companion object {
        const val EXTRA_FAMILI = "extra_famili"
    }
}