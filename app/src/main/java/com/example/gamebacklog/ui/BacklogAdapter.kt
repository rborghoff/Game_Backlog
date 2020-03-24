package com.example.gamebacklog.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Backlog

class BacklogAdapter (private val backlog: List<Backlog>) : RecyclerView.Adapter<BacklogAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.backlog_item_view_test,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return backlog.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(backlog[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val etTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val etConsole: TextView = itemView.findViewById(R.id.tvConsole)
        private val etDag: TextView = itemView.findViewById(R.id.tvDate)
        private val etmaand: TextView = itemView.findViewById(R.id.tvDate)
        private val etjaar: TextView = itemView.findViewById(R.id.tvDate)


        fun bind(backlog: Backlog) {
            etTitle.text = backlog.title
            etConsole.text = backlog.platform
            etDag.text = backlog.dag.toString()
            etmaand.text =backlog.maand
            etjaar.text = backlog.jaar.toString()




        }
    }
}