package com.example.plmeria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.OnMapReadyCallback
import com.huawei.hms.maps.SupportMapFragment

class ubicacionMapa : AppCompatActivity(), OnMapReadyCallback {
    val TAG = "MyMessage"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var mSupportMapFragment: SupportMapFragment? = null
        mSupportMapFragment = supportFragmentManager.findFragmentById(R.id.mapfragment_mapfragmentdemo) as SupportMapFragment?
        mSupportMapFragment?.getMapAsync(this)
    }

    override fun onMapReady(huaweiMap: HuaweiMap) {
        Log.d(TAG, "onMapReady: ")
        var hMap = huaweiMap

    }
}