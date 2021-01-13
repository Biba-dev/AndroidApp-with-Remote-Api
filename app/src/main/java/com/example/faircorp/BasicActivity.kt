package com.example.faircorp

import android.content.Intent
import android.net.Uri
//import android.support.v7.app.AppCompatActivity
//import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class BasicActivity : AppCompatActivity(){
    //activate the menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    //method passes the MenuItem selected and handle each possible values in BasicActivity class
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_windows -> startActivity(
                Intent(this, WindowsActivity::class.java)

            )
            R.id.menu_rooms -> startActivity(
                    Intent(this, RoomsActivity::class.java)
            )
            R.id.menu_website -> startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse("https://dev-mind.fr"))
            )
            R.id.menu_email -> startActivity(
                Intent(Intent.ACTION_SENDTO, Uri.parse("mailto://hiba.awad@etu.emse.fr"))
            )

        }
        return super.onContextItemSelected(item)
    }

}