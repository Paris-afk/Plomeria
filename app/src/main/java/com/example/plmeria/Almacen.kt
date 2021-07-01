package com.example.plmeria

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_almacen.*
class Almacen : AppCompatActivity() {

    lateinit var baseDeDatos :BaseDeDatos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_almacen)
        baseDeDatos = BaseDeDatos(this)
        rv_dashboard.layoutManager = LinearLayoutManager(this)
        fab_dashboard.setOnClickListener {
            var dialog =  AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.dialog_dashboard,null);
            val medidas = view.findViewById<EditText>(R.id.ev_todo)
            val medidasCantidad = view.findViewById<EditText>(R.id.ev_todo2)
            dialog.setView(view)
            dialog.setPositiveButton("Add") { _: DialogInterface, _: Int ->
                if (medidas.text.isNotEmpty()) {
                    val medida = Medidas()
                    medida.id_medida = medidas.text.toString().toInt()
                    medida.cantidad = medidasCantidad.text.toString().toInt()
                    baseDeDatos.addMedidas(medida)
                    refreshList()
                }
            }
            dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->

            }

            dialog.show()

        }
    }

    override fun onResume() {
        refreshList()
        super.onResume()
    }

    private fun refreshList(){
        rv_dashboard.adapter = DashboardAdapter(this,baseDeDatos.obtenerMedidas())
    }

    class DashboardAdapter(val context:Context, val list:MutableList<Medidas>): RecyclerView.Adapter<DashboardAdapter.ViewHolder>(){

    class ViewHolder(v:View): RecyclerView.ViewHolder(v){
        val cantidad : TextView = v.findViewById(R.id.tv_todo_name)
        val cantidadDePulg : TextView = v.findViewById(R.id.tv_todo_name)
    }

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_child_dashboard,p0,false))
        }

        override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
           holder.cantidad.text = "id: " + list[p1].id_medida.toString() + " | Cantidad: " + list[p1].cantidad.toString()
        }

        override fun getItemCount(): Int {
           return list.size
        }

    }


}