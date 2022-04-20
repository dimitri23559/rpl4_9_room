package com.example.rpl4_9_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rpl4_9_room.room.Movie
import com.example.rpl4_9_room.room.movieDb
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch




class MainActivity : AppCompatActivity() {

    val db by lazy { movieDb(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListener()
    }

    override fun onStart(){
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val movies = db.movieDao().getmovie()
            log.d("mainActivity","dbresponse",$movies)
        }
    }

    fun setupListener(){
        add_movie.setOnClickListener{
            startActivity(Intent(this,AddActivity::class.java))
        }
    }
}