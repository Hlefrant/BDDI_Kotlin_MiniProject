package com.hugolefrant.miniproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.hugolefrant.miniproject.fragments.ModesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

<<<<<<< HEAD
        change(ModesFragment())
=======

>>>>>>> 4cfe788b0fff1843e703ee3fc6069e8b26e33e3d
    }
}

fun FragmentActivity.change(fragment: Fragment) {
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.container, fragment)
        addToBackStack(null)
    }.commit()
}