package com.example.faircorp

//import androidx.lifecycle.lifecycleScope

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.faircorp.model.ApiServices
import com.example.faircorp.model.WindowDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WindowsActivity : BasicActivity() {


    var listWindows: List<WindowDto>? = null
    var id: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_windows)

        val recyclerView = findViewById<RecyclerView>(R.id.list_windows)

        val switchWindowsButton = findViewById<Button>(R.id.switch_button)

        val adapter = WindowAdapter(this) // (3)

        id = intent.getLongExtra("room_id", 0)

        switchWindowsButton.setOnClickListener { onSwitchButton(adapter) }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        updateRecycler(adapter)
    }

    private fun updateRecycler(adapter: WindowAdapter) {
        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().windowsApiService.findAll().execute() } // (2)
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        listWindows = it.body()
                        if (id == 0L) {
                            adapter.update(it.body() ?: emptyList())
                        } else {
                            var filteredListWindows: ArrayList<WindowDto> = ArrayList()
                            for (window in listWindows!!) {
                                if (window.roomId == id) {
                                    filteredListWindows.add(window)
                                }
                            }
                            adapter.update(filteredListWindows)
                        }
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Error on windows loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    private fun onSwitchButton(adapter: WindowAdapter) {
        lifecycleScope.launch(context = Dispatchers.IO) {
            for (window in listWindows!!) {
                runCatching {
                    ApiServices().roomsApiService.switchRoomWindows(window.roomId).execute()
                }
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                            updateRecycler(adapter)
                        }
                    }
                    .onFailure {
                        withContext(context = Dispatchers.Main) { // (3)
                            Toast.makeText(
                                applicationContext,
                                "Error on windows loading $it",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }

        }
    }


}