package com.example.faircorp


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.faircorp.model.WindowDto

class WindowAdapter(var activity: WindowsActivity) :
    RecyclerView.Adapter<WindowAdapter.WindowViewHolder>() { // an adapter must implement RecyclerView.Adapter wich manage a RecyclerView.ViewHolder
    inner class WindowViewHolder(view: View) :
        RecyclerView.ViewHolder(view) { // we create a WindowViewHolder which is able to hold fields defined in layout activity_windows_item.xml. When you scroll through the list view, system does not recreate these fields. It will update the values via method (7)
        val name: TextView = view.findViewById(R.id.txt_window_name)
        val room: TextView = view.findViewById(R.id.txt_window_room)
        val status: TextView = view.findViewById(R.id.txt_status)
        val context: Context = view.context
    }

    private val items =
        mutableListOf<WindowDto>() //  adapter has a mutable list to store elements to display

    fun update(windows: List<WindowDto>) {  // method used to update the list content. This method will be called when data will be ready
        items.clear()
        items.addAll(windows)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =
        items.size // RecyclerView.Adapter abstract class asks you to implement a first method that returns the number of records

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WindowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_windows_item, parent, false)
        return WindowViewHolder(view)
    }

    override fun onBindViewHolder(holder: WindowViewHolder, position: Int) {
        val window = items[position]
        holder.apply {
            name.text = window.name
            status.text = window.windowStatus.toString()
            room.text = window.roomName
            itemView.setOnClickListener {
                var intent: Intent = Intent(holder.itemView.context, WindowActivity::class.java)
                intent.putExtra("window_id", window.id)
                activity.startActivity(intent)
            }
        }
    }


    override fun onViewRecycled(holder: WindowViewHolder) {
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}