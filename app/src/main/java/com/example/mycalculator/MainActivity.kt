package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView?=null
    var lastnumeric:Boolean=false
    var lastdot:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput=findViewById(R.id.tvinput)
    }
    fun onDigit(view:View){
//        Toast.makeText(this, "Button clicked", Toast.LENGTH_LONG).show()
        tvInput?.append((view as Button).text)
        lastnumeric=true 
        lastdot=false

    }
    fun onclear(view:View){
        tvInput?.text=""
    }
    fun ondecimal(view:View){
            if(lastnumeric&&!lastdot){
                tvInput?.append(".")
                lastnumeric=false
                lastdot=true
            }
    }
    fun onoperator(view: View){
        tvInput?.text?.let{
            if(lastnumeric&&!inoperator(it.toString())){
                tvInput?.append((view as Button).text)
                     lastnumeric=false
                lastdot=false 
            }
        }
    }
    fun onequal(view: View){
        if(lastnumeric){
            var tvValue=tvInput?.text.toString()
            var prefix=""
            try{
                if(tvValue.startsWith("-")){
                    // to ignore minus at first
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValue=tvValue.split("-")
                    // if number is 99-1 so we are spliting through "-"
                    var one=splitValue[0] //99
                    var two=splitValue[1] //1
                    if(prefix.isNotEmpty()){
                        // adding minus sign again with one digit
                        one=prefix+one
                    }
                    var result=(one.toDouble()-two.toDouble())
                    tvInput?.text=removezeroafterdot(result.toString())
                }
                else  if(tvValue.contains("+")){
                    val splitValue=tvValue.split("+")
                    // if number is 99-1 so we are spliting through "-"
                    var one=splitValue[0] //99
                    var two=splitValue[1] //1
                    if(prefix.isNotEmpty()){
                        // adding minus sign again with one digit
                        one=prefix+one
                    }
                    var result=(one.toDouble()+two.toDouble())
                    tvInput?.text=removezeroafterdot(result.toString())
                }
                else  if(tvValue.contains("/")){
                    val splitValue=tvValue.split("/")
                    // if number is 99-1 so we are spliting through "-"
                    var one=splitValue[0] //99
                    var two=splitValue[1] //1
                    if(prefix.isNotEmpty()){
                        // adding minus sign again with one digit
                        one=prefix+one
                    }
                    var result=(one.toDouble()/two.toDouble())
                    tvInput?.text=removezeroafterdot(result.toString())
                }else  if(tvValue.contains("*")){
                    val splitValue=tvValue.split("*")
                    // if number is 99-1 so we are spliting through "-"
                    var one=splitValue[0] //99
                    var two=splitValue[1] //1
                    if(prefix.isNotEmpty()){
                        // adding minus sign again with one digit
                        one=prefix+one
                    }
                    var result=(one.toDouble()*two.toDouble())
                    tvInput?.text=removezeroafterdot(result.toString())
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }

        }
    }
    private fun removezeroafterdot(result:String):String{
        var value =result
        if(result.contains(".0")){
            value=result.substring(0,result.length-2)
        }
        return value
    }

    private fun inoperator(value:String):Boolean{
               return if(value.startsWith("-")){
                   false
               }
        else{
               value.contains("/")||value.contains("*")||value.contains("+")||value.contains("-")
        }
    }
}