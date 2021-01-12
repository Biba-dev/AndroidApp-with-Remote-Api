package com.example.faircorp
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.faircorp.model.WindowService

const val WINDOW_NAME_PARAM = "com.faircorp.windowname.attribute"
const val Room_NAME_PARAM = "com.faircorp.roomname.attribute"
// we declare the XML resource file where your view content is defined
class MainActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /** Called when the user taps the button */
    fun openWindow(view: View) {
        // Extract value filled in editext identified with txt_window_name id
       val windowName = findViewById<EditText>(R.id.txt_window_name).text.toString()
       // Do something in response to button
        val intent = Intent(this, WindowActivity::class.java).apply {
            putExtra(WINDOW_NAME_PARAM, windowName)
        }
        startActivity(intent)
    }


}