package com.mathisviollet.wooofapp.managers

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

object UserManager {

    fun checkIfUserIsConnected(callbackYes: () -> Unit = {}, callbackNo: () -> Unit = {}) {
        val auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null) {
            callbackYes()
        } else {
            callbackNo()
        }
    }

    fun createUser(email:String, firstname:String, age:Int, phoneNumber:String) {
        val user = hashMapOf(
            "email" to email,
            "firstname" to firstname,
            "age" to age,
            "phoneNumber" to phoneNumber,
        )

        val db = Firebase.firestore
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun signIn(email:String, password:String, confirmationPassword : String, onSignedIn: () -> Unit) {
        if(password != confirmationPassword) {
            Log.w(TAG, "Password and confirmation password are not the same")
            return
        }

        val auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    createUser(email, "Mathis", 21, "06 00 00 00 00")
                    onSignedIn()

                } else {
                    Log.w(TAG, "createUserWithEmail:failure", it.exception)
                }
            }
    }

    fun logIn(email:String, password:String, onLoggedIn: () -> Unit) {
        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if (it.isSuccessful) {
                    Log.d(TAG, "logInWithEmail:success")
                    onLoggedIn()
                } else {
                    Log.w(TAG, "logInWithEmail:failure", it.exception)
                }
            }
    }

    fun logOut(onLogOut: () -> Unit) {
        val auth = Firebase.auth
        auth.signOut()
        Log.d(TAG, "User signed out")
        onLogOut()
    }
}