package com.mathisviollet.wooofapp.managers

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.mathisviollet.wooofapp.models.Author
import com.mathisviollet.wooofapp.models.Place
import com.mathisviollet.wooofapp.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object ProductsManager: ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct = _selectedProduct.asStateFlow()

    fun updateSelectedProduct(product: Product) {
        _selectedProduct.value = product
    }

    fun getProducts(): MutableStateFlow<List<Product>> {
        val db = Firebase.firestore
        db.collection("products")
            .get()
            .addOnSuccessListener { result ->
                val products = result.mapNotNull { document ->
                    try {
                        val placeMap = document.get("place") as? Map<String, Any> ?: emptyMap()
                        val authorMap = document.get("author") as? Map<String, Any> ?: emptyMap()

                        val place = Place(
                            latitude = placeMap["latitude"] as? Double ?: 0.0,
                            longitude = placeMap["longitude"] as? Double ?: 0.0,
                            address = placeMap["address"] as? String ?: ""
                        )

                        val author = Author(
                            firstname = authorMap["firstname"] as? String ?: "",
                            lastname = authorMap["lastname"] as? String ?: "",
                            note = authorMap["note"] as? Double ?: 0.0,
                            profilePictureUrl = authorMap["profilePictureUrl"] as? String ?: "",
                            isCertified = authorMap["isCertified"] as? Boolean ?: false
                        )

                        Product(
                            title = document.getString("title") ?: "",
                            description = document.getString("description") ?: "",
                            price = document.getDouble("price") ?: 0.0,
                            date = document.getString("date") ?: "",
                            place = place,
                            author = author,
                            isFavourite = document.getBoolean("isFavourite") ?: false,
                            imageUrl = document.getString("imageUrl") ?: ""
                        )
                    } catch (e: Exception) {
                        Log.e(ContentValues.TAG, "Error parsing document", e)
                        null
                    }
                }
                _products.value = products
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }

        return _products
    }


    fun addProduct() {
        val product = Product(
            title = "Faire la prière pour mon chat",
            description = "Il faut faire la prière pour mon chat",
            price = 2000.0,
            date = "10.01",
            place = Place(
                address= "Annecy",
                latitude = 45.899247,
                longitude = 6.129384,
            ),
            author = Author(
                firstname = "Mathieu",
                lastname = "D.",
                note = 3.0,
                profilePictureUrl = "https://firebasestorage.googleapis.com/v0/b/wooofapp-a237a.appspot.com/o/mathieuD.jpeg?alt=media&token=3638e996-9e92-4dbb-b4f5-d5129d851847",
                isCertified = false,
            ),
            isFavourite = true,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/wooofapp-a237a.appspot.com/o/mathieu_cat.jpeg?alt=media&token=d20e7303-9bdd-4c6f-97b7-3a3c963e684b",
        )

        val db = Firebase.firestore
        db.collection("products")
            .add(product)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }
    }
}