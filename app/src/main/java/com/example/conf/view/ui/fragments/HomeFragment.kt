package com.example.conf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.conf.R
import com.example.conf.model.Publication
import com.example.conf.view.adapter.PubAdapter
import com.example.conf.viewmodel.PubsViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var pubAdapter: PubAdapter
    private lateinit var viewModel: PubsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PubsViewModel::class.java)
        viewModel.refresh()

        pubAdapter = PubAdapter()

        rvHome.apply {
            layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
            adapter = pubAdapter
        }
        observeViewModel()

    }
    fun observeViewModel(){
        viewModel.listPub.observe(viewLifecycleOwner, Observer<List<Publication>>{
                pub -> pubAdapter.updateData(pub)
        })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer <Boolean>{
            if (it != null)
                rlBaseSpeakers.visibility = View.INVISIBLE
        })
    }
}