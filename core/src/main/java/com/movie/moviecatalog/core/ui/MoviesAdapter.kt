package com.movie.moviecatalog.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.movie.moviecatalog.core.R
import com.movie.moviecatalog.core.databinding.ItemRowBinding
import com.movie.moviecatalog.core.domain.model.Movie
import java.util.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    companion object {
        const val BASEIMG_URL = "https://image.tmdb.org/t/p/w500/"
        private const val WIDTH_IMG = 350
        private const val HEIGHT_IMG = 550
    }

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(BASEIMG_URL + data.image)
                    .override(WIDTH_IMG, HEIGHT_IMG)
                    .into(imgMovie)
                tvTitle.text = data.title
                tvOverview.text = data.overview
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}