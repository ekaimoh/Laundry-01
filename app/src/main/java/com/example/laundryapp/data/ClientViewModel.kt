package com.example.laundryapp.data

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import com.example.laundryapp.model.Client
import com.example.laundryapp.navigation.ROUTE_LOGIN
import com.example.laundryapp.navigation.ROUTE_VIEW_CLIENT
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class ClientViewModel(var navController: NavController,
                      var context: Context
) {
    var authRepository: AuthViewModel

    init {
        authRepository=AuthViewModel(navController,context)
        if (!authRepository.isloggedin()){
            navController.navigate(ROUTE_LOGIN)
        }
    }

    fun saveClient(
        filePath: Uri, firstname: String, lastname: String, gender: String, age:String,
        bio: String){
        var id = System.currentTimeMillis().toString()
        var storageReference = FirebaseStorage.getInstance().getReference().child("Passport/$id")

        storageReference.putFile(filePath).addOnCompleteListener{
            if (it.isSuccessful){
                storageReference.downloadUrl.addOnSuccessListener{
                    var imageUrl = it.toString()
                    var houseData = Client(imageUrl,firstname,lastname,gender,age,bio,id)
                    var dbRef = FirebaseDatabase.getInstance().getReference().child("Client/$id")
                    dbRef.setValue(houseData)
                    Toast.makeText(context,"Client added successfully", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(context,"${it.exception!!.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun viewClients(client: MutableState<Client>, clients: SnapshotStateList<Client>): SnapshotStateList<Client> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Client")


        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                clients.clear()

                for (snap in snapshot.children){
                    val value = snap.getValue(Client::class.java)
                    client.value = value!!
                    clients.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return clients
    }
    fun updateClient(
        context: Context,
        navController: NavController,
        filePath: Uri,
        fullNames: String,
        phoneNumber: String,
        gender: String,
        age: String,
        bio: String,
        id: String,
        currentImageUrl: String.Companion
    ) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("Client/$id")

        if (filePath != Uri.EMPTY) {
            val storageReference = FirebaseStorage.getInstance().reference
            val imageRef = storageReference.child("Passport/${UUID.randomUUID()}.jpg")

            imageRef.putFile(filePath)
                .addOnSuccessListener {
                    imageRef.downloadUrl.addOnSuccessListener { uri ->
                        val imageUrl = uri.toString()
                        val updatedClient = Client(imageUrl, fullNames, phoneNumber, gender, age, bio, id)

                        databaseReference.setValue(updatedClient)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
                                    navController.navigate(ROUTE_VIEW_CLIENT)
                                } else {
                                    Toast.makeText(context, "Update failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(context, "Image upload failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            val updatedClient = Client("", fullNames, phoneNumber, gender, age, bio, id)
            databaseReference.setValue(updatedClient)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
                        navController.navigate(ROUTE_VIEW_CLIENT)
                    } else {
                        Toast.makeText(context, "Update failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

}