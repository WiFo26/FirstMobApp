package com.example.conf.view.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.conf.R
import com.example.conf.model.Ubication
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class UbicationFragment : Fragment(),OnMapReadyCallback,GoogleMap.OnMarkerClickListener {
    private lateinit var mMap: GoogleMap


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val ubi = Ubication()
        // Add a marker  and move the camera
        val zoom =16f
        val centerMap = LatLng(ubi.latitude, ubi.longitude)
        val bitmapDraw = context?.applicationContext?.let{
            ContextCompat.getDrawable(it,R.drawable.logo_platzi)
        } as BitmapDrawable
        val smallMarker = Bitmap.createScaledBitmap(bitmapDraw.bitmap,150,150,false)
        mMap.addMarker(
            MarkerOptions()
                .position(centerMap)
                .title(ubi.name)
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap,zoom))
        mMap.setOnMarkerClickListener(this)

        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context,R.raw.custom_map))
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        findNavController().navigate(R.id.ubicationDetailFragmentDialog)
        return true
    }

}