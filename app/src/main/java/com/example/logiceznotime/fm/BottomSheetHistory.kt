package com.example.logiceznotime.fm

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.logiceznotime.R
import com.example.logiceznotime.adapter.CustomAdapter
import com.example.logiceznotime.model.CoinModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetHistory():BottomSheetDialogFragment() {
    private lateinit var viewModel: DataViewModel
    private lateinit var customAdapter: CustomAdapter
    private lateinit var recyclerView:RecyclerView
    private  var listData: MutableList<CoinModel> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =inflater.inflate(R.layout.layout_bottom,container,false)
         recyclerView = view.findViewById(R.id.rv_history)

        return view
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]
        viewModel.mutableLiveData.observe(viewLifecycleOwner, Observer {
            val layoutManager = LinearLayoutManager(this.requireContext())
            customAdapter = CustomAdapter(it)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = customAdapter
            val dividerItemDecoration = DividerItemDecoration(
                recyclerView.context,
                layoutManager.orientation
            )
            recyclerView.addItemDecoration(dividerItemDecoration)
        })

    }
}