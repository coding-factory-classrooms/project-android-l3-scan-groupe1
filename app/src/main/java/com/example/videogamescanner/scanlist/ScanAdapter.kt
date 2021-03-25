package com.example.videogamescanner.scanlist

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.videogamescanner.Game
import com.example.videogamescanner.databinding.ItemScanBinding
import com.example.videogamescanner.gamedetail.GameDetailActivity

class ScanAdapter(private var games: List<Game>)
    : RecyclerView.Adapter<ScanAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemScanBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemScanBinding.inflate(inflater,parent,false)
        binding.itemWrapper.setOnClickListener {
            val intent = Intent(parent.context, GameDetailActivity::class.java)
            startActivity(parent.context, intent,null)
        }
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val games = games[position]
        with (holder.binding) {
            titleTextView.text = games.name
            typeTextView.text = games.type
            plateformTextView.text = games.plateform
            releaseDateTextView.text = games.release
        }

    }

    override fun getItemCount(): Int = games.size
    fun updateDataSet(games: List<Game>) {
        this.games = games
        notifyDataSetChanged()
    }

}
