package com.example.pilotDetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pilotDetail.R
import com.example.pilotDetail.database.model.response.ItemsItem

/**
 * The NotesAdapter.kt to populate the recyclerview
 */
class PilotAdapter(
    private val noteItemClickListener: NoteItemClickListener
) : RecyclerView.Adapter<PilotAdapter.MyViewHolder>() {

    private val pilotList: ArrayList<ItemsItem> = ArrayList()

    fun setItems(newsData: List<ItemsItem>) {
        newsData.run {
            pilotList.clear()
            pilotList.addAll(this)
            notifyDataSetChanged()
        }
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //creating views
        var mainLayout = view.findViewById(R.id.mainLayout) as CardView
        var name = view.findViewById(R.id.tvTitle) as TextView
        var point = view.findViewById(R.id.tvDetails) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //returning the View Holder
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pilot_ui, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pilotList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //populating the data from list to view
        val note = pilotList[position]
        note.run {
            holder.name.text = name
            holder.point.text = point.toString()

            //attaching the onCLick to the layout
            holder.mainLayout.setOnClickListener {
                noteItemClickListener.onItemClickListener(this)
            }
        }
    }

    //interface to get the callback
    interface NoteItemClickListener {
        fun onItemClickListener(note: ItemsItem)
    }
}