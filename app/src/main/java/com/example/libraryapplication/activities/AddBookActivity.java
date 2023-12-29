package com.example.libraryapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libraryapplication.R;
import com.example.libraryapplication.models.BookDetails;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddBookActivity extends AppCompatActivity {

    //initialize the text
    EditText book_title_text;
    EditText author_book_text;
    EditText number_of_pages;
    Button add_button;

    //variables
    private String title;
    private String authorName;
    private int numberOfPages;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        book_title_text = findViewById(R.id.booktitle_text);
        author_book_text = findViewById(R.id.authorbook_text);
        number_of_pages = findViewById(R.id.editTextNumber);
        add_button = findViewById(R.id.add_btn);
        db = FirebaseFirestore.getInstance();

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = book_title_text.getText().toString();
                authorName = author_book_text.getText().toString();
                numberOfPages = Integer.parseInt(number_of_pages.getText().toString());
                addDataToFireBase(title,authorName,numberOfPages);
            }
        });
    }

    private void addDataToFireBase(String title,String authorName,int numberOfPages){
        CollectionReference dbbooks = db.collection("BookDetails");
        BookDetails bookDetails = new BookDetails(title,authorName,numberOfPages);
        dbbooks.add(bookDetails).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(AddBookActivity.this, "Added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddBookActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}