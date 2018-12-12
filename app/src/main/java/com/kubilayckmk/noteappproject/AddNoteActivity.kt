package com.kubilayckmk.noteappproject

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*
import java.lang.Exception

class AddNoteActivity : AppCompatActivity() {

    var dbTable = "Notes"
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        try {
            val bundle: Bundle = intent.extras
            id = bundle.getInt("ID", 0)
            if (id != 0) {
                titleEdit.setText(bundle.getString("name"))
                descEdit.setText(bundle.getString("des"))
            }
        } catch (ex: Exception) {
        }
    }

    fun addFunc(view: View) {
        var dbManager = dbManager(this)
        var values = ContentValues()
        values.put("Title", titleEdit.text.toString())
        values.put("Description", descEdit.text.toString())

        if (id == 0) {
            var ID = dbManager.insert(values)
            if (ID > 0) {
                Toast.makeText(this, "Note is added", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error whiling add note", Toast.LENGTH_SHORT).show()
            }
        } else {
            var selectionArgs = arrayOf(id.toString())
            val ID = dbManager.update(values, "ID=?", selectionArgs)
            if (ID > 0) {
                Toast.makeText(this, "Note is added", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error whiling add note", Toast.LENGTH_SHORT).show()
            }

        }
    }

}
