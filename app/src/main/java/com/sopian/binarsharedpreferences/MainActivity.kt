package com.sopian.binarsharedpreferences

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import com.sopian.binarsharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val sharedPrefFile = "SharedPrefFile"

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPref = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        with(binding) {
            saveButton.setOnClickListener {
                val id = Integer.parseInt(idEdt.text.toString())
                val name = nameEdt.text.toString()

                sharedPref.edit {
                    putInt(ID_KEY, id)
                    putString(NAME_KEY, name)
                    apply()
                }

                Toast.makeText(this@MainActivity, "Data saved", Toast.LENGTH_SHORT).show()
            }

            viewButton.setOnClickListener {
                val sharedIdValue = sharedPref.getInt(ID_KEY, 0)
                val sharedNameValue = sharedPref.getString(NAME_KEY, "defaultname")

                if (sharedIdValue == 0 && sharedNameValue.equals("defaultname")) {
                    idTextView.text = "default id: $sharedIdValue"
                    nameTextView.text = "default name: $sharedNameValue"

                    Toast.makeText(this@MainActivity, "Data view kosong", Toast.LENGTH_SHORT).show()
                } else {
                    nameTextView.text = sharedNameValue.toString()
                    idTextView.text = sharedIdValue.toString()
                }

            }

            clearButton.setOnClickListener {
                sharedPref.edit {
                    clear()
                    apply()
                }

                nameTextView.text = ""
                idTextView.text = ""

                Toast.makeText(this@MainActivity, "Data clear", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val ID_KEY = "id"
        private const val NAME_KEY = "name"
    }
}