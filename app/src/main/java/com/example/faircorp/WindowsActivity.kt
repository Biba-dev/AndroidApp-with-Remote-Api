package com.example.faircorp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
//import androidx.lifecycle.lifecycleScope

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.faircorp.model.ApiServices
import com.example.faircorp.model.WindowService

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WindowsActivity : BasicActivity(), OnWindowSelectedListener {

   // val windowService = WindowService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_windows)


        val recyclerView = findViewById<RecyclerView>(R.id.list_windows)
        //***max
        val switchWindowsButton = findViewById<Button>(R.id.switch_button)

        val adapter = WindowAdapter(this) // (3)
//***max
        val id = intent.getLongExtra(ROOM_ID_PARAM, 0)
        //**max
        switchWindowsButton.setOnClickListener { onSwitchButton(id, adapter) }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        updateRecycler(adapter, id)
    }
//        adapter.update(windowService.findAll()) // (4) I replace it by the below code.

//        runCatching { ApiServices().windowsApiService.findAll().execute() } // (1)
//            .onSuccess { adapter.update(it.body() ?: emptyList()) }  // (2)
//            .onFailure {
//                Toast.makeText(this, "Error on windows loading $it", Toast.LENGTH_LONG)
//                    .show()  // (3)
//            }
//
//
private fun updateRecycler(adapter: WindowAdapter,id: Long) {
        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().windowsApiService.findAll().execute() } // (2)
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        adapter.update(it.body() ?: emptyList())
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
    private fun onSwitchButton(id: Long, adapter: WindowAdapter) {
        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().roomsApiService.switchRoomWindows(id).execute() }
                .onSuccess {
                    withContext(context=Dispatchers.Main) {
                        updateRecycler(adapter,id)
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
    override fun onWindowSelected(id: Long) {
        val intent = Intent(this, WindowActivity::class.java).putExtra(WINDOW_NAME_PARAM, id)
        startActivity(intent)
    }
}