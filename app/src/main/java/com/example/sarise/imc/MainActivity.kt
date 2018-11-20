package com.example.sarise.imc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import com.example.sarise.imc.R.id.radio
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var altura = 0.0
    var peso = 0
    var radio = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switch_mulher.setOnClickListener{
            mudaGenero()
        }

        sbAltura.setOnSeekBarChangeListener(
            object :SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    txtseekAltura.text = (progress.toString() + " cm")
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    altura = (seekBar!!.progress.toDouble())
                    txtseekAltura.text = (seekBar?.progress.toString() + " cm")
                }

            }

        )

        sbPeso.setOnSeekBarChangeListener(
            object :SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    txtSeekPeso.text = (progress.toString() + " kg")
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    peso = (seekBar!!.progress.toDouble().toInt())
                    txtSeekPeso.text = (seekBar?.progress.toString() + " kg")
                }


            }
        )

        sbIdade.setOnSeekBarChangeListener(
            object :SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    txtSeekIdade.text = (progress.toString() + " anos")
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    txtSeekIdade.text = (seekBar?.progress.toString())
                }

            }
        )

        radioGroup?.setOnCheckedChangeListener { group, checkedId -> radio

            when(checkedId){
                R.id.rbtn1 -> radio = "Exercite-se mais! #YouCanDoIt"
                R.id.rbtn2 -> radio= "Já é um começo! #KeepGoing"
                R.id.rbtn3 -> radio= "uhUUuu! #YouRock"
            }
        }
    }


    private fun mudaGenero(){
        if (switch_mulher.isChecked){
            switch_mulher.text = "Mulher"
        } else {
            switch_mulher.text = "Homem"
            }
    }

    fun calcularIMC (v: View){
        altura/=100

        val calc = peso / (altura * altura)
        txtResultado.text =calc.toString()

        if (calc < 18.5){
            rtbar.rating = 2F
            txtResultado.text = "IMC:" + calc + " Você está abaixo do peso :(  " +  radio.toString()
        }else{
            if (calc > 18.5 && calc < 24.9){
                rtbar.rating = 5F
                txtResultado.text = "IMC:" + calc + " Você esta no peso ideal :D   " + radio.toString()
            }else{
                rtbar.rating = 2F
                if(calc>25 && calc<29.9){
                    txtResultado.text = "IMC:" + calc + " Você esta Acima do peso :(   "+ radio.toString()
                }else{
                    if(calc>30){
                        rtbar.rating = 1F
                        txtResultado.text = "IMC:" + calc + "Você esá Obeso :0   " + radio.toString()
                    }
                }
            }
        }
    }

}


