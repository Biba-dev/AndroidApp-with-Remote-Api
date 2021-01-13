package com.example.faircorp


import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

const val ROOM_ID_PARAM = "com.example.faircorp.roomid.attribute"
const val WINDOW_NAME_PARAM = "com.faircorp.windowname.attribute"
const val Room_NAME_PARAM = "com.faircorp.roomname.attribute"

// we declare the XML resource file where your view content is defined
class MainActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val alert = findViewById<Button>(R.id.btn_open_message)
    }

    fun setOnClickListener(view: View) {
        val mAlertDialog = AlertDialog.Builder(this@MainActivity)
        mAlertDialog.setIcon(R.mipmap.ic_launcher_round) //set alertdialog icon
        mAlertDialog.setTitle("Title!") //set alertdialog title
        mAlertDialog.setMessage("Your message here") //set alertdialog message
        mAlertDialog.setPositiveButton("Yes") { dialog, id ->
            //perform some tasks here
            Toast.makeText(this@MainActivity, "Yes", Toast.LENGTH_SHORT).show()
        }
        mAlertDialog.setNegativeButton("No") { dialog, id ->
            //perform som tasks here
            Toast.makeText(this@MainActivity, "No", Toast.LENGTH_SHORT).show()
        }
        mAlertDialog.show()
    }
}



