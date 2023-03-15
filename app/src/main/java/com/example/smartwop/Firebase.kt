package com.example.smartwop

import android.app.Activity
import android.util.Log
import com.example.smartwop.model.UserModel
import com.example.smartwop.utils.Constans
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class Firebase {


    private val fireStore = FirebaseFirestore.getInstance()


    fun signUp(activity: SignActivity2, userInfo: UserModel) {
        fireStore.collection(Constans.USERS)
            .document(UserId()).set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess()
            }
            .addOnFailureListener { e ->
                Log.e(activity.javaClass.simpleName, "error waiting for document", e)
            }
    }

    fun logIn(activity: Activity){
        fireStore.collection(Constans.USERS)
            .document(UserId())
            .get()
            .addOnSuccessListener { document->
                val loggedInUser = document.toObject(UserModel::class.java)!!
                when(activity){
                    is LoginActivity2 ->{
                        activity.logInSuccess(loggedInUser)
                    }
                    is MainActivity ->{
                        activity.userProfile(loggedInUser)
                    }
                }

            }
            .addOnFailureListener { e ->
                Log.e(activity.javaClass.simpleName, "error waiting for document", e)
            }

    }

    fun UserId(): String {
        var currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

}
