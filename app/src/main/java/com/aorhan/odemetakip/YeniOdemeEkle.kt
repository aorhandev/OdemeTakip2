package com.aorhan.odemetakip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aorhan.odemetakip.databinding.ActivityYeniOdemeEkleBinding
import com.aorhan.odemetakip.entities.Odeme
import com.aorhan.odemetakip.entities.OdemeTipi

class YeniOdemeEkle : AppCompatActivity() {

    lateinit var binding: ActivityYeniOdemeEkleBinding

    var odeme: Odeme? = null
    var odemeTipi: OdemeTipi? = null
    var odemeIslemleri: OdemeIslemleri
    var odemeTipiIslemleri: OdemeTipiIslemleri

    init {
        odemeIslemleri = OdemeIslemleri(this)
        odemeTipiIslemleri = OdemeTipiIslemleri(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYeniOdemeEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val odemeTipiId = intent.getIntExtra("odemeTipiId", -1)
            odeme = Odeme()
            odemeTipi = odemeTipiIslemleri.odemeTipiGetir(odemeTipiId)
            binding.tvOdemeTipAdi.setText(odemeTipi!!.OdemeTipiAdi + " Faturanız için Ödeme Girin")


    }

    fun btnOdemeKaydet_OnClick(view: View) {
        odeme!!.OdemeTipiId = odemeTipi!!.Id
        odeme!!.OdemeAdi = binding.editOAd.text.toString()
        odeme!!.Tutar = binding.editOTutar.text.toString()

        if (odeme!!.Id == null) {
            odemeIslemleri.odemeEkle(odeme!!)
        } else {
            odemeIslemleri.odemeGuncelle(odeme!!)
        }

        setResult(RESULT_OK)
        finish()
    }

    fun btnOdemeSil_OnClick(view: View) {
        odemeIslemleri.odemeSil(odeme!!.Id!!)
        setResult(RESULT_OK)
        finish()
    }

}