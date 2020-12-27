package com.project.kotlin.submissionmade.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.kotlin.submissionmade.R
import com.project.kotlin.submissionmade.core.data.source.local.entity.MovieEntity
import com.project.kotlin.submissionmade.databinding.ItemMovieBinding
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<MovieEntity>()
    var onItemClick: ((String) -> Unit)? = null

    fun setData(newListData: List<MovieEntity>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieBinding.bind(itemView)
        @SuppressLint("SimpleDateFormat")
        fun bind(data: MovieEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w342${data.poster}")
                    .into(imgItemShow)
                tvItemName.text = data.title
                tvItemRating.text = data.rating
                tvItemOverview.text = data.overview

                var dateRelease: String? = ""
                try {
                    val date = SimpleDateFormat("yyyy-MM-dd").parse(data.release)
                    dateRelease = DateFormat.getDateInstance(DateFormat.MEDIUM).format(Objects.requireNonNull(date))
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
                tvItemRelease.text = dateRelease
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition].movieId)
            }
        }
    }
}