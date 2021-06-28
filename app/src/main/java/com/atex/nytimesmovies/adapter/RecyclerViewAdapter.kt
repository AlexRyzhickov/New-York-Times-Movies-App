package com.atex.nytimesmovies.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.atex.nytimesmovies.R
import com.atex.nytimesmovies.entities.Movie
import com.bumptech.glide.Glide

class RecyclerViewAdapter() :
    PagingDataAdapter<Movie, RecyclerViewAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)

        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val img: ImageView = view.findViewById(R.id.movieImg)
        val movieName: TextView = view.findViewById(R.id.movieName)

        fun bind(data: Movie) {
            movieName.text = data.display_title

            data.multimedia?.src?.let {
                Glide.with(img)
                    .load(it)
//                    .circleCrop()
                    .into(img)
            }

        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.display_title == newItem.display_title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.display_title == newItem.display_title
//                    && oldItem.species == newItem.species
        }

    }

}