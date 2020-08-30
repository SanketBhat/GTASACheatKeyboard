package com.sanketbhat.cheatgtasa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Sanket Bhat
 */
class CheatListAdapter(private var cheats: List<Cheat>, val listener: CheatListener? = null) :
    RecyclerView.Adapter<CheatListAdapter.CheatViewHolder>() {

    interface CheatListener {
        fun onCheatClick(cheat: Cheat)
    }

    inner class CheatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.title)

        init {
            view.setOnClickListener {
                listener?.onCheatClick(get(adapterPosition))
            }
        }

        fun bind(cheat: Cheat) {
            textView.text = cheat.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cheat_item, parent, false)
        return CheatViewHolder(view)
    }

    override fun getItemCount() = cheats.size

    fun get(position: Int) = cheats[position]

    fun setList(list: List<Cheat>) {
        this.cheats = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CheatViewHolder, position: Int) {
        holder.bind(cheats[position])
    }
}