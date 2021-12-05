package com.example.plmeria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.MapsInitializer
import com.huawei.hms.maps.OnMapReadyCallback
import com.huawei.hms.maps.SupportMapFragment

class ubicacionMapa : AppCompatActivity(), OnMapReadyCallback {
    val TAG = "MyMessage"
    private var hMap: HuaweiMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapsInitializer.setApiKey("CwEAAAAAV/RWwFd4r1PxAhZqXNtVUYAEuX3/VnrtumypLqyj73FHRTCW0UxiKLT+cTWIK5B1SlJBo87UbC0mvnzXmMWlnePYsIY=")
        setContentView(R.layout.activity_ubicacion_mapa)

        var mSupportMapFragment: SupportMapFragment? = null
        mSupportMapFragment = supportFragmentManager.findFragmentById(R.id.mapfragment_mapfragmentdemo) as SupportMapFragment?
        mSupportMapFragment?.getMapAsync(this)
    }

    override fun onMapReady(huaweiMap: HuaweiMap) {
        Log.d(TAG, "onMapReady: ")
         hMap = huaweiMap

    }

}