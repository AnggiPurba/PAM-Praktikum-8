package com.ifs21012.pampraktikum8

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21012.pampraktikum8.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val dataDinosss = ArrayList<Dinosss>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val listDinosssAdapter = ListDinosssAdapter(dataDinosss)
        binding.rvDinosss.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDinosss.adapter = listDinosssAdapter
        listDinosssAdapter.setOnItemClickCallback(object :
            ListDinosssAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dinosss) {
                showSelectedDinosss(data)
            }
        })


        // Populate data into the RecyclerView
        dataDinosss.addAll(getListDinosss())
        listDinosssAdapter.notifyDataSetChanged()
    }

    private fun showSelectedDinosss(dinosss: Dinosss) {
        val intent = Intent(requireContext(), DinoDetailAcitivity::class.java)
        intent.putExtra(EXTRA_FAMILI, dinosss)
        startActivity(intent)
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

    companion object {
        const val EXTRA_MESSAGE = "extra_message"
        const val EXTRA_FAMILI = "extra_famili"
    }
}
