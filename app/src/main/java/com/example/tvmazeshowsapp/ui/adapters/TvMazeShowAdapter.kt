package com.example.tvmazeshowsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvmazeshowsapp.databinding.LayoutMazeTvShowBinding
import com.example.tvmazeshowsapp.ui.models.TvMazeResponseItem

class TvMazeShowAdapter(private val listTvShows: List<TvMazeResponseItem>) : RecyclerView.Adapter<TvMazeShowAdapter.TvMazeShowHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvMazeShowHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layoutMazeTvShowBinding = LayoutMazeTvShowBinding.inflate(inflater, parent, false)
        return TvMazeShowHolder(parent,layoutMazeTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvMazeShowHolder, position: Int) {
        val tvMazeResponseItem: TvMazeResponseItem = listTvShows[position]
        holder.bind(tvMazeResponseItem)
    }

    override fun getItemCount() =  listTvShows.size

    inner  class TvMazeShowHolder(
        private val parent: ViewGroup,
        private val layoutMazeTvShowBinding: LayoutMazeTvShowBinding
    ) : RecyclerView.ViewHolder(layoutMazeTvShowBinding.root){
        fun bind(linkedProfile: TvMazeResponseItem) {
            layoutMazeTvShowBinding.textView.text = linkedProfile.name
            Glide.with(parent.context)
                .load(linkedProfile.image.original)
                .centerCrop()
                .into(layoutMazeTvShowBinding.imageView)
        }
    }
}