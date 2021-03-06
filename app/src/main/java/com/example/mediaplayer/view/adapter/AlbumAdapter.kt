package com.example.mediaplayer.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mediaplayer.R
import com.example.mediaplayer.databinding.ItemHomeBinding
import com.example.mediaplayer.model.data.entities.Album
import com.example.mediaplayer.model.data.entities.Artist
import com.example.mediaplayer.util.diffAlbumCallback
import com.example.mediaplayer.util.ext.toast
import timber.log.Timber

class AlbumAdapter(
    private val glide: RequestManager,
    private val context: Context
): RecyclerView.Adapter<AlbumAdapter.HomeViewHolder>()  {

    val differ = AsyncListDiffer(this, diffAlbumCallback)

    var itemList: List<Album>
        get() = differ.currentList
        set(value) {
            val submit = value.sortedBy { it.name.lowercase() }
            differ.submitList(submit)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(ItemHomeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = itemList[position]
        holder.bindItems(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class HomeViewHolder(
        private val binding: ItemHomeBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: Album) {
            val name = item.name
            val song = item.song
            val firstSong = song.first { it.album == name }

            binding.apply {
                mtvTitle.text = name

                glide.asDrawable()
                    .load(firstSong.imageUri)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_music_library_transparent)
                    .into(sivItemImage)

                root.setOnClickListener {
                    root.transitionName = item.name
                    onItemClickListener?.let { passedMethod ->
                        passedMethod(item)
                        Timber.d("$item clicked")// function passed by fragment in this case
                        // I want to use item from my adapter
                    } ?: toast(context, "msg")     // do something else
                }                                       // if the method is not passed yet
            }
        }
    }

    var onItemClickListener: ((Album) -> Unit)? = null // variable that have the function

    fun setItemClickListener(listener: (Album) -> Unit) { // method to set the function
        onItemClickListener = listener
    }
}