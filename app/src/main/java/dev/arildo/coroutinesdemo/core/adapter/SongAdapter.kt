package dev.arildo.coroutinesdemo.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.arildo.coroutinesdemo.databinding.ItemSongBinding
import dev.arildo.data.songs.model.Song

class SongAdapter(
    private var items: List<Song> = emptyList()
) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSongBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    fun setData(data: List<Song>?) {
        this.items = data.orEmpty()
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemSongBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            binding.song = song
            binding.executePendingBindings()
        }
    }
}