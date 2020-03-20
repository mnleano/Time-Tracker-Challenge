package com.mnleano.timetrackerchallenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mnleano.timetrackerchallenge.databinding.RowInputBinding
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class InputsAdapter(private val items: ArrayList<DateTime>) :
    RecyclerView.Adapter<InputsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowInputBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val sdf = SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.getDefault())
        holder.bind("${sdf.format(item.startDate)} - ${sdf.format(item.endDate)}")
    }

    inner class ViewHolder(private val binding: RowInputBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(text: String) {
            binding.text = text
        }
    }
}