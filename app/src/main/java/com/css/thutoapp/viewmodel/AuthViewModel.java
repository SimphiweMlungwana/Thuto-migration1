package com.css.thutoapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.css.thutoapp.repository.FirebaseRepository;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModel extends AndroidViewModel {

    private MutableLiveData<FirebaseUser> firebaseUserMutableLiveData;

    public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return firebaseUserMutableLiveData;
    }

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }

    private FirebaseUser currentUser;
    private FirebaseRepository repository;

    public AuthViewModel(@NonNull Application application) {
        super(application);

        repository = new FirebaseRepository(application);
        currentUser = repository.getCurrentUser();
        firebaseUserMutableLiveData = repository.getFirebaseUserMutableLiveData();
    }

    public void signUp(String email, String pass){
        repository.signUp(email, pass);
    }

    public void signIn(String email, String pass){
        repository.signIn(email, pass);
    }

    public void signOut(){
        repository.signOut();
    }
}
