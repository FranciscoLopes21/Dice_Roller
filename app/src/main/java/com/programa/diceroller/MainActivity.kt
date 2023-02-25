package com.programa.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {
    lateinit var mAdView : AdView
    lateinit var btn_roller : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdView = findViewById(R.id.adView)
        btn_roller = findViewById(R.id.btn_roller)

        btn_roller.text= resources.getString(R.string.roll)

        //Start Ads
        MobileAds.initialize(this) {}
        val adView = AdView(this)
        adView.adUnitId = "ca-app-pub-4484908704965075/8234435123"
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        //End Ads

        val Intent = intent
        val NRoll = Intent.getIntExtra("NDices",0)
        val rollBtn: Button =findViewById(R.id.btn_roller)
        val img_dice2: ImageView = findViewById(R.id.img_Dice2)
        val img_dice3: ImageView = findViewById(R.id.img_Dice3)
        val img_dice4: ImageView = findViewById(R.id.img_Dice4)
        val img_dice5: ImageView = findViewById(R.id.img_Dice5)
        if (NRoll==1){img_dice2.isVisible=false
            img_dice3.isVisible=false
            img_dice4.isVisible=false
            img_dice5.isVisible=false}
        if (NRoll==2){img_dice3.isVisible=false
            img_dice4.isVisible=false
            img_dice5.isVisible=false}
        if (NRoll==3){ img_dice4.isVisible=false
            img_dice5.isVisible=false}
        if (NRoll==4){img_dice5.isVisible=false}



        rollBtn.setOnClickListener { rollDice(NRoll) }
    }
    var total = 0
    private fun rollDice(NRoll:Int) {
        val img_dice: ImageView = findViewById(R.id.img_Dice)
        val img_dice2: ImageView = findViewById(R.id.img_Dice2)
        val img_dice3: ImageView = findViewById(R.id.img_Dice3)
        val img_dice4: ImageView = findViewById(R.id.img_Dice4)
        val img_dice5: ImageView = findViewById(R.id.img_Dice5)
        val txt_RollNumber: TextView = findViewById(R.id.txt_Number)
        total=0
        for (n in 1..NRoll) {
            // Create new Dice object with 6 sides and roll it
            var dice = Dice(6)
            var diceRoll = dice.roll()
            val drawableResource = when (diceRoll) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            when (n) {
                1 -> img_dice.setImageResource(drawableResource)
                2 -> img_dice2.setImageResource(drawableResource)
                3 -> img_dice3.setImageResource(drawableResource)
                4 -> img_dice4.setImageResource(drawableResource)
                5 -> img_dice5.setImageResource(drawableResource)
            }

            total+=diceRoll
            txt_RollNumber.setText(total.toString())
        }

    }
}

class Dice(val numSides:Int){

    fun roll():Int{
        return (1..numSides).random()
    }
}