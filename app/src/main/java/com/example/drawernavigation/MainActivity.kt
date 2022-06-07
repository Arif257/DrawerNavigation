package com.example.drawernavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.drawernavigation.fragment.HomeFragment
import com.example.drawernavigation.fragment.MessageFragment
import com.example.drawernavigation.fragment.SettingFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var toggle : ActionBarDrawerToggle
    //lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //drawerLayout  = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            it.isChecked = true

            when(it.itemId){
                R.id.nav_home -> replaceFragment(HomeFragment(),it.title.toString())
                R.id.nav_message -> replaceFragment(MessageFragment(),it.title.toString())
                R.id.nav_sync -> Toast.makeText(applicationContext, "licked sync", Toast.LENGTH_SHORT).show()
                R.id.nav_trash -> Toast.makeText(applicationContext, "licked trash", Toast.LENGTH_SHORT).show()
                R.id.nav_setting -> replaceFragment(SettingFragment(),it.title.toString())
                R.id.nav_share -> Toast.makeText(applicationContext, "licked share", Toast.LENGTH_SHORT).show()
                R.id.nav_rate_us -> Toast.makeText(applicationContext, "licked Rate us", Toast.LENGTH_SHORT).show()
            }
            true
        }


    }


    private fun replaceFragment(fragment: Fragment,title : String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}