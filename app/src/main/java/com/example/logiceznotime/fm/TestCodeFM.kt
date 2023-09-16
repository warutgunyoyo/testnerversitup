package com.example.logiceznotime.fm

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.logiceznotime.R
import com.example.logiceznotime.model.CoinModel
import com.example.logiceznotime.model.Currency
import com.example.logiceznotime.service.CoinApi
import com.example.logiceznotime.service.RetrofitClient
import java.time.LocalDateTime


class TestCodeFM : Fragment() {

    private lateinit var usdTextView: TextView
    private lateinit var gbpTextView: TextView
    private lateinit var eurTextView: TextView
    private lateinit var timerTextView: TextView
    private lateinit var loading: ContentLoadingProgressBar
    private val historyList: MutableList<CoinModel> = mutableListOf()
    private lateinit var viewModel: DataViewModel
    private lateinit var showHistory: Button
    private lateinit var contentLinear: LinearLayout
    private lateinit var spinner: Spinner
    private var listCurrency: MutableList<Currency> = mutableListOf()
    private lateinit var toBTC: Button


    private lateinit var tv_spinnerValue: EditText

    var rate: Double = 0.0
    val types = arrayOf("USD", " GBP", "EUR")

    val mainHandler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_testcode, container, false)

        usdTextView = view.findViewById(R.id.usdTextView)
        gbpTextView = view.findViewById(R.id.gbpTextView)
        eurTextView = view.findViewById(R.id.eurTextView)
        timerTextView = view.findViewById(R.id.timerTextView)
        showHistory = view.findViewById(R.id.viewHistory)
        loading = view.findViewById(R.id.load)
        contentLinear = view.findViewById(R.id.content_linear)
        spinner = view.findViewById(R.id.spinner1)
        tv_spinnerValue = view.findViewById(R.id.spinner_select)
        toBTC = view.findViewById(R.id.to_btc)

        toBTC.setOnClickListener {
       val valueToDisplay =convertToBTC(tv_spinnerValue.text.toString().toDouble(),rate)
            Toast.makeText(
                requireContext(),
                valueToDisplay.toString(),
                Toast.LENGTH_LONG).show()

        }


        mainHandler.post(object : Runnable {
            override fun run() {
                loading.visibility = View.VISIBLE
                contentLinear.visibility = View.GONE

                getListData()
                mainHandler.postDelayed(this, 60000)
            }
        })
        showHistory.setOnClickListener {
            var histoRyBottomSheetHistory = BottomSheetHistory()
            histoRyBottomSheetHistory.show(this.parentFragmentManager, "")
        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(DataViewModel::class.java)

        viewModel.mutableLiveData.value = historyList
    }


    @SuppressLint("SetTextI18n")
    fun getListData() {
        var retrofit = RetrofitClient.getInstance()
        var apiInterface = retrofit.create(CoinApi::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getBitcoinPrice()
                if (response.isSuccessful) {
                    val dataResponse = response.body()
                    if (dataResponse != null) {
                        historyList.add(dataResponse)


                        usdTextView.text =
                            dataResponse.bpi.USD.code + ": " + dataResponse.bpi.USD.rate
                        gbpTextView.text =
                            dataResponse.bpi.GBP.code + ": " + dataResponse.bpi.GBP.rate
                        eurTextView.text =
                            dataResponse.bpi.EUR.code + ": " + dataResponse.bpi.EUR.rate
                        timerTextView.text =
                            "Updated: " + dataResponse.time.updated + " " + LocalDateTime.now().hour + ":" + LocalDateTime.now().minute
                        listCurrency.clear()
                        listCurrency.add(dataResponse.bpi.USD)
                        listCurrency.add(dataResponse.bpi.GBP)
                        listCurrency.add(dataResponse.bpi.EUR)


                        val adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            types
                        )
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinner.adapter = adapter

                        spinner.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                    println("erreur")
                                }

                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    rate = listCurrency[position].rate_float.toDouble()
                                }

                            }

                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
                loading.visibility = View.GONE
                contentLinear.visibility = View.VISIBLE


            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
        }

    }

    fun convertToBTC(amount: Double, rate: Double): Double? {
        try {
            return amount / rate
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

        ///เขียน program generate จำนวนเฉพาะ (2, 3, 5, 7, 11, 13, 17, 19, …)g
    fun isPrime(number: Int): Boolean {
        if (number <= 1) {
            return false
        }

        if (number <= 3) {
            return true
        }

        if (number % 2 == 0 || number % 3 == 0) {
            return false
        }

        var i = 5
        while (i * i <= number) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false
            }
            i += 6
        }

        return true
    }

    fun generatePrimes(n: Int): List<Int> {
        val primes = mutableListOf<Int>()
        var number = 2

        while (primes.size < n) {
            if (isPrime(number)) {
                primes.add(number)
            }
            number++
        }

        return primes
    }

}
