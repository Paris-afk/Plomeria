package com.example.plmeria

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDeDatos(val context: Context): SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val crearTablaMedidas = "CREATE TABLE ${TABLE_MEDIDAS} (${COL_ID} integer PRIMARY KEY," +
                "${CANTIDAD} integer" +
                ");"
        db.execSQL(crearTablaMedidas);
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun addMedidas(medidas: Medidas) : Boolean{
        val db = writableDatabase
        val cv = ContentValues()
        cv.put(COL_ID, medidas.id_medida)
        cv.put(CANTIDAD, medidas.cantidad)
        val result = db.insert(TABLE_MEDIDAS,null,cv)
        return result != (-1).toLong()
    }

    fun deleteMedidas(medidasId: Int): Boolean {
        val db = writableDatabase
        val borrarMedidas = "DELETE FROM ${TABLE_MEDIDAS} WHERE ${COL_ID} = ${medidasId}"
        return db.delete("medidas", "id_medida =" + medidasId.toString(), null) > 0
        //db.execSQL(borrarMedidas);
        //return res
    }

    fun obtenerMedidas() : MutableList<Medidas> {
        var resultado: MutableList<Medidas> = ArrayList()
        val db = readableDatabase
        val queryResult = db.rawQuery("SELECT  * from ${TABLE_MEDIDAS}",null)
        if (queryResult.moveToFirst()) {
            do {
                val medidas = Medidas()
                medidas.id_medida = queryResult.getInt(queryResult.getColumnIndex(COL_ID))
                medidas.cantidad = queryResult.getInt(queryResult.getColumnIndex(CANTIDAD))
                resultado.add(medidas)
            } while (queryResult.moveToNext())
        }
        queryResult.close()
        return resultado
    }
}