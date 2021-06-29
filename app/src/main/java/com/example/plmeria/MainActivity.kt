package com.example.plmeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_new_activity = findViewById(R.id.change) as Button
        btn_new_activity.setOnClickListener {
            val intent = Intent(this, Almacen::class.java)
            startActivity(intent);
        }
    }




    //valor de medida
    var valor = 0;

    fun MostrarResultado (view:View){
        var editText = ""
        try {
            //toma el valor ingresado
            editText = findViewById<EditText>(R.id.valorIngresado).text.toString();
            var valorIngresado = editText.toInt()

            Calcular(valorIngresado,valor,view);
        }catch (e:NumberFormatException) {
            // si no metio algun valor manda alert
            Toast.makeText(applicationContext,"Ha ocurrido un error o no has ingresado las medidas",Toast.LENGTH_SHORT).show()
        }
    }
    fun Calcular (valorElegido:Int,valorBoton:Int,view:View ){
        //si no selecciono botones de la izquierda manda alert
        if(valor == 0){
            Toast.makeText(applicationContext,"Ha ocurrido un error o no has ingresado las medidas",Toast.LENGTH_SHORT)             .show()
        }else{
            //hace divicion
            var resultadoFinalView = valorElegido / valorBoton;
            val resultadoFinal = findViewById(R.id.resultadoFinal) as TextView ;

            //manda a mostrar el resultado final
            resultadoFinal.text = " EL RESULTADO ES ${resultadoFinalView} TRAMOS"
        }


    }
    //modifican el valor de medida
    fun Boton1 (view:View){
        valor = 6

    }
    fun Boton2 (view:View){

         valor = 6;


    }
    fun Boton3 (view:View){

         valor = 8;

    }
    fun Boton4 (view:View){

         valor = 8;

    }
    fun Boton5 (view:View){

         valor = 10;
    }
    fun Boton6 (view:View){

         valor = 12;

    }
}