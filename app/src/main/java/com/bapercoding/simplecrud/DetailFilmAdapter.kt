@file:Suppress("DEPRECATION")

package com.bapercoding.simplecrud

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.iarcuschin.simpleratingbar.SimpleRatingBar
import kotlinx.android.synthetic.main.activity_detail_film.*
import kotlinx.android.synthetic.main.detail_film.view.*
import java.text.DecimalFormat


@Suppress("DEPRECATION")
class DetailFilmAdapter(
        private val context: Context,
        private val listFilm: ArrayList<Film>,
        private val judul: String,
        private val rating: String,
        private val episode: String,
        private val sinopsis: String,
        private val imagePage: String,
        private val letak: Int,
        private val list2: ArrayList<String>,
        private val activity: FragmentActivity,
        private val listRating: ArrayList<String>,
        private val listDetail: ArrayList<String>,
        private val watch: String) : RecyclerView.Adapter<DetailFilmAdapter.Holder>() {

    private lateinit var dbReference: DatabaseReference
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.detail_film,parent,false))
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val film = listFilm[letak]
        if(list2.isNotEmpty()){
            holder.view.rvPhoto2.setHasFixedSize(true)
            holder.view.rvPhoto2.layoutManager = GridLayoutManager(context, 3)
            val adapter = PhotoFilmAdapter3(context,list2, judul)
            adapter.notifyItemRangeRemoved(0, list2.size)
            adapter.notifyDataSetChanged()
            holder.view.rvPhoto2.adapter = adapter

        }
        Glide.with(holder.itemView.context)
                .load(imagePage)
//                .apply(RequestOptions().fitCenter().format(DecodeFormat.PREFER_ARGB_8888).override(Target.SIZE_ORIGINAL))
                .into(holder.view.img_item_photo2)

        Glide.with(holder.itemView.context)
                .load(R.drawable.comingsoon)
//                .apply(RequestOptions().fitCenter().format(DecodeFormat.PREFER_ARGB_8888).override(Target.SIZE_ORIGINAL))
                .into(holder.view.img_cast)
        holder.view.tv_jumla_cast.setOnClickListener {
            activity.pager.setCurrentItem(2)
        }
        holder.view.tv_jumlah_photo.setOnClickListener {
            activity.pager.setCurrentItem(3)
        }
        holder.view.tv_rating.text = rating
        holder.view.tv_item_alamat.text = episode
        holder.view.tv_item_rating2.text = "Rating: "
        holder.view.ratingBar1.rating = rating.toFloat()
        holder.view.tv_view_user.text = "Watchers: $watch"
        holder.view.tv_sinopsis.text = sinopsis
        holder.view.tv_photo.text = "Photos"
        holder.view.tv_cast.text = "Reviews"
        holder.view.tv_jumla_cast.text = "View all (0)"

        holder.view.tv_item_rating.visibility = View.GONE

    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)
}