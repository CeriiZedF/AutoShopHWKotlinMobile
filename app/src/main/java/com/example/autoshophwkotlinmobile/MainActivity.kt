package com.example.autoshophwkotlinmobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var carList: RecyclerView
    private lateinit var carAdapter: CarAdapter
    private var cars: ArrayList<Car> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        carList = findViewById(R.id.recyclerView) // Убедитесь, что id соответствует вашему XML
        carList.layoutManager = LinearLayoutManager(this)

        setInitialData()

        carAdapter = CarAdapter(this, cars)

        carList.adapter = carAdapter

        carAdapter.setOnItemClickListener { car ->
            Toast.makeText(this, "Вы выбрали: ${car.brand} ${car.model}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setInitialData() {
        cars.add(Car("Toyota", "Camry", 2020, "A reliable sedan", 24000.0, R.drawable.toyota_camry))
        cars.add(Car("Honda", "Accord", 2021, "A comfortable sedan", 25000.0, R.drawable.honda_accord))
        cars.add(Car("Ford", "Mustang", 2019, "A sporty car", 30000.0, R.drawable.ford_mustang))
        cars.add(Car("BMW", "X5", 2022, "A luxury SUV", 55000.0, R.drawable.bmw_x5))
        cars.add(Car("Audi", "A4", 2021, "A premium sedan", 35000.0, R.drawable.audi_a4))
        cars.add(Car("Mercedes-Benz", "C-Class", 2020, "A stylish sedan", 40000.0, R.drawable.mercedes_benz_c_class))
        cars.add(Car("Chevrolet", "Camaro", 2018, "A classic muscle car", 35000.0, R.drawable.chevrolet_camaro))
        cars.add(Car("Nissan", "Altima", 2021, "A fuel-efficient sedan", 28000.0, R.drawable.nissan_altima))
        cars.add(Car("Hyundai", "Elantra", 2022, "A compact sedan", 22000.0, R.drawable.hyundai_elantra))
        cars.add(Car("Mazda", "CX-5", 2021, "A compact crossover", 33000.0, R.drawable.mazda_cx5))
        cars.add(Car("Kia", "Sorento", 2020, "A spacious SUV", 37000.0, R.drawable.kia_sorento))
        cars.add(Car("Toyota", "Corolla", 2021, "A reliable compact sedan", 21000.0, R.drawable.toyota_camry))
        cars.add(Car("Honda", "Civic", 2022, "A popular compact sedan", 22000.0, R.drawable.honda_accord))
        cars.add(Car("Ford", "Explorer", 2020, "A spacious SUV", 43000.0, R.drawable.ford_mustang))
        cars.add(Car("BMW", "3 Series", 2021, "A luxury sports sedan", 45000.0, R.drawable.bmw_x5))
        cars.add(Car("Audi", "Q5", 2022, "A compact luxury SUV", 48000.0, R.drawable.audi_a4))
        cars.add(Car("Mercedes-Benz", "E-Class", 2021, "An elegant luxury sedan", 55000.0, R.drawable.mercedes_benz_c_class))
        cars.add(Car("Chevrolet", "Silverado", 2020, "A powerful pickup truck", 40000.0, R.drawable.chevrolet_camaro))
        cars.add(Car("Nissan", "Maxima", 2020, "A full-size sedan", 37000.0, R.drawable.nissan_altima))
        cars.add(Car("Hyundai", "Tucson", 2021, "A compact crossover SUV", 30000.0, R.drawable.hyundai_elantra))
        cars.add(Car("Mazda", "Mazda3", 2020, "A sporty compact sedan", 25000.0, R.drawable.mazda_cx5))
        cars.add(Car("Kia", "Optima", 2021, "A mid-size sedan", 33000.0, R.drawable.kia_sorento))
        cars.add(Car("Toyota", "Highlander", 2020, "A family-friendly SUV", 47000.0, R.drawable.toyota_camry))
        cars.add(Car("Honda", "Pilot", 2021, "A spacious family SUV", 52000.0, R.drawable.honda_accord))
        cars.add(Car("Ford", "F-150", 2021, "A tough full-size pickup", 55000.0, R.drawable.ford_mustang))
        cars.add(Car("BMW", "X3", 2020, "A versatile luxury SUV", 46000.0, R.drawable.bmw_x5))
        cars.add(Car("Audi", "A6", 2021, "A sophisticated luxury sedan", 51000.0, R.drawable.audi_a4))
        cars.add(Car("Mercedes-Benz", "GLC-Class", 2020, "A stylish luxury SUV", 55000.0, R.drawable.mercedes_benz_c_class))
        cars.add(Car("Chevrolet", "Tahoe", 2021, "A large family SUV", 65000.0, R.drawable.chevrolet_camaro))
        cars.add(Car("Nissan", "Rogue", 2021, "A compact crossover", 32000.0, R.drawable.nissan_altima))
        cars.add(Car("Hyundai", "Santa Fe", 2020, "A comfortable family SUV", 39000.0, R.drawable.hyundai_elantra))

    }

    fun searchButton(view: View) {
        val intent = Intent(this, SearchActivity::class.java)
        intent.putParcelableArrayListExtra("cars_list", cars)
        startActivity(intent)
    }
}