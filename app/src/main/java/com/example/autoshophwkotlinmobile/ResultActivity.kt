package com.example.autoshophwkotlinmobile

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ResultActivity : AppCompatActivity() {

    private lateinit var filteredCars: ArrayList<Car>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        filteredCars = intent.getParcelableArrayListExtra<Car>("filteredCars") ?: ArrayList()

        val resultCountText = findViewById<TextView>(R.id.resultCountText)
        resultCountText.text = "Found ${filteredCars.size} cars"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CarAdapter(this, filteredCars)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}
