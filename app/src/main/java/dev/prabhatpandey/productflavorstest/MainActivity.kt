package dev.prabhatpandey.productflavorstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import dev.prabhatpandey.productflavorstest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val database: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    private lateinit var namesAdapter : NamesAdapter
    private var namesList  = listOf<Name>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        namesAdapter = NamesAdapter(namesList)

        binding.itemsRecyclerView.apply {
            setHasFixedSize(true)
            adapter = namesAdapter
        }


        database
            .collection("names")
            .orderBy("title")
            .addSnapshotListener { value, error ->
                if(error == null && value != null) {
                    val list = mutableListOf<Name>()
                    for(document in value) {
                        val name = document.toObject(Name::class.java)
                        list.add(name)
                    }

                    namesList = list

                    namesAdapter.setNameList(list)

                } else {
                    Toast.makeText(this, "No Names Found", Toast.LENGTH_SHORT).show()
                }
            }

        binding.saveButton.setOnClickListener {
            val nameTitle =  binding.textInput.text.toString()

            if (nameTitle.isEmpty()) {
                Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            saveName(nameTitle)
        }
    }

    private fun saveName(nameTitle: String) {
        binding.progressBar.visibility = View.VISIBLE
        val name = Name(nameTitle)

        database
            .collection("names")
            .document()
            .set(name)
            .addOnSuccessListener {
                Log.d(TAG, "saveName: Name Saved Successfully.")
                Toast.makeText(this, "Name Saved", Toast.LENGTH_SHORT).show()
                binding.textInput.setText("")
                binding.progressBar.visibility = View.GONE
            }
            .addOnFailureListener {
                Log.d(TAG, "saveName: Error => $it")
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}