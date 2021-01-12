package com.example.faircorp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.faircorp.model.RoomDto

class RoomAdapter(val listener: OnRoomSelectedListener) :RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() { // an adapter must implement RecyclerView.Adapter wich manage a RecyclerView.ViewHolder
    inner class RoomViewHolder(view: View) :
        RecyclerView.ViewHolder(view) { // we create a RoomViewHolder which is able to hold fields defined in layout activity_rooms_item.xml. When you scroll through the list view, system does not recreate these fields. It will update the values via method (7)
        val name: TextView = view.findViewById(R.id.txt_room_name)
        val windowlist: TextView = view.findViewById(R.id.txt_room_windows)
        val temperature: TextView = view.findViewById(R.id.txt_temperature)
    }

    private val items =
        mutableListOf<RoomDto>() //  adapter has a mutable list to store elements to display

    fun update(rooms: List<RoomDto>) {  // method used to update the list content. This method will be called when data will be ready
        items.clear()
        items.addAll(rooms)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =
        items.size // RecyclerView.Adapter abstract class asks you to implement a first method that returns the number of records

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_rooms_item, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = items[position]

        holder.apply {
            name.text = room.name
            temperature.text = room.currentTemperature.toString()
            windowlist.text = room.windowslist.toString()
            itemView.setOnClickListener { listener.onRoomSelected(room.id) }
        }
    }


    override fun onViewRecycled(holder: RoomViewHolder) { // (2)
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}