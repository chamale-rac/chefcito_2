package com.chama.chefcito_2

import android.R
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.chama.chefcito_2.databinding.CreateRecipeFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import org.checkerframework.common.returnsreceiver.qual.This


class CreateRecipeFragment : Fragment() {
    //lateinit var storage: FirebaseStorage
    var imageURL = ""
    private var _binding: CreateRecipeFragmentBinding? = null
    private val binding get() = _binding!!
    val db = Firebase.firestore

    private lateinit var firebaseAuth: FirebaseAuth


    lateinit var stepsArr: ArrayList<String>
    lateinit var ingredientsArr: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stepsArr = ArrayList()
        ingredientsArr = arrayListOf()

        //val storageRef = storage.reference

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = CreateRecipeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        listManager(binding.stepsListView, binding.stepsAddButton, binding.stepsEditText, stepsArr, "Step")
        listManager(binding.ingredientsListView, binding.ingredientsAddButton, binding.ingredientsEditText, ingredientsArr, "Ingredient")

        binding.btnChoosePhoto.setOnClickListener {
            // PICK INTENT picks item from data
            // and returned selected item
            val galleryIntent = Intent(Intent.ACTION_PICK)
            // here item is type of image
            galleryIntent.type = "image/*"
            // ActivityResultLauncher callback
            imagePickerActivityResult.launch(galleryIntent)
        }

        var userID = db.collection("users").document(firebaseAuth.currentUser?.email.toString())
        var documentId = ""

        binding.Publish.setOnClickListener {
            val fireRecipe = hashMapOf(
                "author" to userID,
                "image" to imageURL,
                "ingredients" to ingredientsArr,
                "liked" to 0,
                "saved" to 0,
                "steps" to stepsArr,
                "title" to binding.titleText.text.toString()
            )


            db.collection("recipes")
                .add(fireRecipe)
                .addOnSuccessListener { documentReference ->
                    documentId = documentReference.id
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }

        userID.update("recipes", FieldValue.arrayUnion(documentId))

    }

    private fun listManager(myListView: ListView,
                            addButton: Button,
                            myEditText: EditText,
                            myList: ArrayList<String>,
                            typeList: String) {
        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
            requireActivity(),
            R.layout.simple_list_item_1,
            myList as List<String?>
        )

        myListView.adapter = adapter

        addButton.setOnClickListener {
            val item = myEditText.text.toString()
            if (item.isNotEmpty()) {
                myEditText.setText("")
                myList.add(item)
                adapter.notifyDataSetChanged()
            }
        }

        myListView.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, index, _ ->
            myList.removeAt(index)
            Toast.makeText(context, "$typeList removed", Toast.LENGTH_SHORT).show()
            adapter.notifyDataSetChanged()
            true
        }
    }


    private var imagePickerActivityResult: ActivityResultLauncher<Intent> =
    // lambda expression to receive a result back, here we
        // receive single item(photo) on selection
        registerForActivityResult( ActivityResultContracts.StartActivityForResult()) { result ->
            if (result != null) {
                //var storage = FirebaseStorage.getInstance()
                //val storageRef = storage.reference
                val storage = Firebase.storage
                var storageRef = storage.reference

                // getting URI of selected Image
                val imageUri: Uri? = result.data?.data

                // val fileName = imageUri?.pathSegments?.last()

                // extract the file name with extension
                val sd = getFileName(requireActivity(), imageUri!!)
                // Upload Task with upload to directory 'file'
                // and name of the file remains same

                var imagesRef: UploadTask = storageRef.child("file/$sd").putFile(imageUri)


                // On success, download the file URL and display it
                imagesRef.addOnSuccessListener {
                    // using glide library to display the image
                    storageRef.child("file/$sd").downloadUrl.addOnSuccessListener {
                        imageURL = it.toString()
                        //Log.e("ImageURL", imageURL)
                        Glide.with(requireActivity())
                            .load(it)
                            .into(binding.viewImage)

                        Log.e("Firebase", "download passed")
                    }.addOnFailureListener {
                        Log.e("Firebase", "Failed in downloading")
                    }
                }.addOnFailureListener {
                    Log.e("Firebase", "Image Upload fail")
                }
            }
        }

    private fun getFileName(context: Context, uri: Uri): String? {
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            cursor.use {
                if (cursor != null ) {
                    if(cursor.moveToFirst()) {
                        return cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)) // NO IMPORTA
                    }
                }
            }
        }
        return uri.path?.lastIndexOf('/')?.let { uri.path?.substring(it) }
    }


}