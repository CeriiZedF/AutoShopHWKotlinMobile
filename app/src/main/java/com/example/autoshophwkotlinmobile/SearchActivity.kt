package com.example.autoshophwkotlinmobile

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class SearchActivity : AppCompatActivity() {

    private lateinit var inputBrand: EditText
    private lateinit var inputModel: EditText
    private lateinit var inputYearFrom: EditText
    private lateinit var inputYearTo: EditText
    private lateinit var inputPriceFrom: EditText
    private lateinit var inputPriceTo: EditText
    private lateinit var matchesButton: Button

    private val allCars = mutableListOf<Car>()
    private var filteredCars = mutableListOf<Car>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val carsList: ArrayList<Car>? = intent.getParcelableArrayListExtra("cars_list")
        if (carsList != null) {
            allCars.addAll(carsList)
            filteredCars.addAll(allCars)
        }

        setupListeners()

        updateMatchesButton()
    }

    private fun setupListeners() {
        inputBrand = findViewById(R.id.inputBrandTextView)
        inputModel = findViewById(R.id.inputModelTextView)
        inputYearFrom = findViewById(R.id.inputYearFrom)
        inputYearTo = findViewById(R.id.inputYearTo)
        inputPriceFrom = findViewById(R.id.inputPriceFrom)
        inputPriceTo = findViewById(R.id.inputPriceTo)
        matchesButton = findViewById(R.id.matchesButton)

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                performDynamicSearch()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        inputBrand.addTextChangedListener(textWatcher)
        inputModel.addTextChangedListener(textWatcher)
        inputYearFrom.addTextChangedListener(textWatcher)
        inputYearTo.addTextChangedListener(textWatcher)
        inputPriceFrom.addTextChangedListener(textWatcher)
        inputPriceTo.addTextChangedListener(textWatcher)

        matchesButton.setOnClickListener {
            showFilteredResults()
        }
    }

    private fun performDynamicSearch() {
        val brand = inputBrand.text.toString().toLowerCase()
        val model = inputModel.text.toString().toLowerCase()
        val yearFrom = inputYearFrom.text.toString().toIntOrNull() ?: 0
        val yearTo = inputYearTo.text.toString().toIntOrNull() ?: Int.MAX_VALUE
        val priceFrom = inputPriceFrom.text.toString().toDoubleOrNull() ?: 0.0
        val priceTo = inputPriceTo.text.toString().toDoubleOrNull() ?: Double.MAX_VALUE

        filteredCars = allCars.filter {
            val isBrandMatch = brand.isEmpty() || it.brand.toLowerCase().contains(brand)
            val isModelMatch = model.isEmpty() || it.model.toLowerCase().contains(model)
            val isYearMatch = it.year in yearFrom..yearTo
            val isPriceMatch = it.cost in priceFrom..priceTo

            isBrandMatch && isModelMatch && isYearMatch && isPriceMatch
        }.toMutableList()

        updateMatchesButton()
    }

    private fun updateMatchesButton() {
        val resultCount = filteredCars.size
        matchesButton.text = "Matches ($resultCount)"
        matchesButton.isEnabled = resultCount > 0
    }

    private fun showFilteredResults() {
        if (filteredCars.isNotEmpty()) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putParcelableArrayListExtra("filteredCars", ArrayList(filteredCars))
            startActivity(intent)
        } else {
            Toast.makeText(this, "No matches found!", Toast.LENGTH_SHORT).show()
        }
    }
}
