package com.example.autoshophwkotlinmobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarAdapter(private val context: Context, private val carList: List<Car>) :
    RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    private var onItemClickListener: ((Car) -> Unit)? = null

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val field1: TextView = itemView.findViewById(R.id.field1)
        val field2: TextView = itemView.findViewById(R.id.field2)
        val field3: TextView = itemView.findViewById(R.id.field3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_car_card, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = carList[position]
        holder.imageView.setImageResource(car.imageId)
        holder.field1.text = "${car.brand}, ${car.model} (${car.year})"
        holder.field2.text = car.description
        holder.field3.text = "${car.cost} $"

        // Установка слушателя клика
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(car)
        }
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    // Метод для установки слушателя клика
    fun setOnItemClickListener(listener: (Car) -> Unit) {
        onItemClickListener = listener
    }
}