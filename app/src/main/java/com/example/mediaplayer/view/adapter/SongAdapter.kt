package com.example.mediaplayer.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mediaplayer.R
import com.example.mediaplayer.databinding.ItemSongBinding
import com.example.mediaplayer.model.data.entities.Song
import com.example.mediaplayer.util.diffSongCallback
import com.example.mediaplayer.util.ext.toast
import timber.log.Timber

class SongAdapter (
    private val glide: RequestManager,
    private val context: Context
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    val differ = AsyncListDiffer(this@SongAdapter, diffSongCallback)

    var songList: List<Song>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(ItemSongBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songList[position]
        holder.bindItem(song)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    inner class SongViewHolder(
        private val binding: ItemSongBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(song: Song) {
            val artist: String = song.artist
            val album: String = song.album
            val albumId: Long = song.albumId
            val dateAdded: Int = song.dateAdded
            val isLocal: Boolean = song.isLocal
            val imageUri: String = song.imageUri
            val length: Long = song.length
            val mediaId: Long = song.mediaId
            val path: String = song.mediaPath
            val startFrom: Int = song.startFrom
            val title: String = song.title
            val year: Int = song.year
            val animation = AnimationUtils.loadAnimation(
                this@SongAdapter.context, R.anim.anim_slidein_down
            )
            val bullet = 0x2022.toChar()

            with(binding) {
                glide.asDrawable()
                    .load(imageUri)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_music_library_transparent)
                    .into(ivSongImage)
            }

            binding.apply {
                with(root) {
                    transitionName = song.mediaId.toString()
                    startAnimation(animation)
                    setOnClickListener {
                        onItemClickListener?.let {
                            it(song)
                        }
                    }
                    setOnLongClickListener {
                        onLongClickListener?.let {
                            it(song)
                        } ?: run {
                            toast(context, "Coming Soon!")
                            true
                        }
                    }
                }

                tvTitle.text = title.ifEmpty { "Unknown" }
                tvSecondaryTitle.text = artist.ifEmpty { "<Unknown>" }
            }
        }
    }

    private var onItemClickListener: ( ( Song ) -> Unit)? = null // variable that have the function

    fun setItemClickListener(listener: (Song) -> Unit) { // method to set the function
        onItemClickListener = listener
    }

    private var onLongClickListener: ( (Song) -> Boolean )? = null

    fun setItemLongClickListener(listener: (Song) -> Boolean) {
        onLongClickListener = listener
    }
}