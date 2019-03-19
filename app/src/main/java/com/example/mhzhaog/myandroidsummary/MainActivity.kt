package com.example.mhzhaog.myandroidsummary

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import com.example.mhzhaog.myandroidsummary.adapter.MainFragmentPagerAdapter
import com.example.mhzhaog.myandroidsummary.fragment.HomeVideoFragment
import com.example.mhzhaog.myandroidsummary.fragment.MapFragment
import com.example.mhzhaog.myandroidsummary.fragment.ProfileFragment
import com.example.mhzhaog.myandroidsummary.service.MusicPlayerService
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity(), HomeVideoFragment.OnFragmentInteractionListener ,MapFragment.OnFragmentInteractionListener,ProfileFragment.OnFragmentInteractionListener{
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val titieArray = arrayOf("视频列表","地图展示","个人信息")
    var fragmentList: ArrayList<Fragment> = ArrayList()
    val homeVideoFragment:HomeVideoFragment = HomeVideoFragment()
    val mapFragment:MapFragment = MapFragment()
    val profileFragment:ProfileFragment = ProfileFragment()
    val TAG = MusicPlayerService::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }

    private fun initView() {
        viewPager.offscreenPageLimit = 4

        fragmentList.add(homeVideoFragment)
        fragmentList.add(mapFragment)
        fragmentList.add(profileFragment)

        tabLayout.tabMode = TabLayout.MODE_FIXED

        var adapter = MainFragmentPagerAdapter(supportFragmentManager,titieArray,fragmentList)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
//        viewPager
    }

    private var isStart: Boolean = false

    override fun onResume() {
        Log.e(TAG, "onResume()")
        super.onResume()
        if (!isStart){
            var intent = Intent()
            intent.setClass(this,MusicPlayerService::class.java)
            startForegroundService(intent)
//            startService(intent)
            isStart = true
        }


    }
}
