package com.example.logiceznotime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.example.logiceznotime.fm.TestCodeFM
import com.example.logiceznotime.fm.TestLogicFM
import com.example.logiceznotime.service.CoinApi
import com.example.logiceznotime.service.RetrofitClient
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        val adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter

        // เชื่อม TabLayout กับ ViewPager
        tabLayout.setupWithViewPager(viewPager)
    }

    private inner class ViewPagerAdapter(fm: androidx.fragment.app.FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getCount(): Int {
            // จำนวน Fragment
            return 2
        }

        override fun getItem(position: Int): androidx.fragment.app.Fragment {
            // สร้างและรีเทิร์น Fragment ตามตำแหน่งที่กำหนด
            return when (position) {
                0 -> TestCodeFM()
                1 -> TestLogicFM()
                else -> throw IllegalArgumentException("Invalid position: $position")
            }


        }

        override fun getPageTitle(position: Int): CharSequence? {
            // กำหนดชื่อแท็บสำหรับแต่ละ Fragment
            return when (position) {
                0 -> "TestCode"
                1 -> "TestLogic"
                else -> null
            }
        }
    }


}