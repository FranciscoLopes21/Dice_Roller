package com.programa.diceroller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class Menu_DiceApp : AppCompatActivity() {
    lateinit var mAdView : AdView
    lateinit var btn_roller : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_dice_app)

        var numberPicker: NumberPicker = findViewById(R.id.np_NDices)

        mAdView = findViewById(R.id.adView)
        btn_roller = findViewById(R.id.btn_Roll)

        btn_roller.text= resources.getString(R.string.roll_dice)

        numberPicker.maxValue = 5
        numberPicker.minValue = 1

        //Start Ads
        MobileAds.initialize(this) {}
        val adView = AdView(this)
        adView.adUnitId = "ca-app-pub-4484908704965075/7937447182"
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        //End Ads

        btn_roller.setOnClickListener {
            val nDice = numberPicker.value
            val changePage = Intent(this, MainActivity::class.java)
            changePage.putExtra("NDices", nDice)
            startActivity(changePage)
        }
    }
}