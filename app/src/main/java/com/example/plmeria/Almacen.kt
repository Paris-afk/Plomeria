package com.example.plmeria

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_almacen.*
import kotlinx.android.synthetic.main.rv_child_dashboard.view.*


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

    fun refreshList(){
        rv_dashboard.adapter = DashboardAdapter(this,baseDeDatos.obtenerMedidas())
    }

    class DashboardAdapter(val context:Context, var list:MutableList<Medidas>): RecyclerView.Adapter<DashboardAdapter.ViewHolder>(){
        lateinit var baseDeDatos :BaseDeDatos

        class ViewHolder(v:View): RecyclerView.ViewHolder(v){
            val cantidad : TextView = v.findViewById(R.id.tv_todo_name)
            val deleteButton: Button = v.findViewById(R.id.delete_button)
        }

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_child_dashboard,p0,false))
        }

        override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
           holder.cantidad.text = "Id: " + list[p1].id_medida.toString() + " | Cantidad: " + list[p1].cantidad.toString()
            holder.deleteButton.text = "Borrar " + list[p1].id_medida.toString()
            holder.deleteButton.setOnClickListener(View.OnClickListener { view ->
                delete(p1)

                list = baseDeDatos.obtenerMedidas()
                this.notifyDataSetChanged()

            })
        }

        fun delete(p1: Int): DashboardAdapter {
            baseDeDatos = BaseDeDatos(context)
            baseDeDatos.deleteMedidas(list[p1].id_medida)
            Toast.makeText(context, "Borrado de la base de datos.", Toast.LENGTH_SHORT).show()
            list = baseDeDatos.obtenerMedidas()
            return Almacen.DashboardAdapter(context, list)
        }

        override fun getItemCount(): Int {
           return list.size
        }

    }


}