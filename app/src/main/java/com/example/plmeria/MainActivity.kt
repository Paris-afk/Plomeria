package com.example.plmeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    var pulgada = 0;
    lateinit var baseDeDatos :BaseDeDatos

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

            baseDeDatos = BaseDeDatos(this)
            var resRes = baseDeDatos.estaDisponible(pulgada)

            if (resRes.size > 0){
                Log.d("DB: " + pulgada.toString(), resRes[0].cantidad.toString())
                if (resRes[0].cantidad < resultadoFinalView ){
                    //manda a mostrar el resultado final
                    resultadoFinal.text = " EL RESULTADO ES ${resultadoFinalView} TRAMOS \n No hay suficientes tramos"
                    Toast.makeText(applicationContext,"from DB: ",Toast.LENGTH_SHORT)
                    return;
                } else {
                    resultadoFinal.text = " EL RESULTADO ES ${resultadoFinalView} TRAMOS \n Hay suficientes tramos"
                    Toast.makeText(applicationContext,"No hay suficientes tramos",Toast.LENGTH_SHORT)
                    return
                }
            } else {
                Log.d("DB: ", "No hay en base de datos")
                resultadoFinal.text = "No hay de estos tramos \n en base de datos"
                return
            }

            resultadoFinal.text = " EL RESULTADO ES ${resultadoFinalView} TRAMOS \n Hay sificientes tramos"
        }


    }
    //modifican el valor de medida
    fun Boton1 (view:View){
        valor = 6
        pulgada = 1

    }
    fun Boton2 (view:View){

         valor = 6;
        pulgada = 2

    }
    fun Boton3 (view:View){

         valor = 8;
        pulgada = 3

    }
    fun Boton4 (view:View){

         valor = 8;
        pulgada = 4

    }
    fun Boton5 (view:View){
        pulgada = 6
         valor = 10;
    }
    fun Boton6 (view:View){
        pulgada = 8
         valor = 12;

    }
}