package com.example.libraryapplication.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.libraryapplication.R;
import com.example.libraryapplication.adapters.BookListAdapter;
import com.example.libraryapplication.models.BookDetails;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    BookListAdapter bookListAdapter;
    ArrayList<BookDetails> bookDetailsArrayList;
    FirebaseFirestore database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView =  findViewById(R.id.list_of_books);

        recyclerView.setHasFixedSize(true);
        floatingActionButton = findViewById(R.id.add_action_button);
        database =FirebaseFirestore.getInstance();
        bookDetailsArrayList = new ArrayList<BookDetails>();
        bookListAdapter = new BookListAdapter(MainActivity.this,bookDetailsArrayList);
        recyclerView.setAdapter(bookListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });

        database.collection("BookDetails").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for(DocumentSnapshot d: list){

                                BookDetails b = d.toObject(BookDetails.class);
                                Toast.makeText(MainActivity.this, b.getTitle(), Toast.LENGTH_SHORT).show();
                                bookDetailsArrayList.add(b);
                            }

                            bookListAdapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(MainActivity.this, "No data found in database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Fail to get the data", Toast.LENGTH_SHORT).show();
                    }
                });

    }

//    private void EventChangeListner()
//    {
//        database.collection("BookDetails").orderBy("authorName")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                if(error != null){
//                    Log.e("FireStore error",error.getMessage());
//                    return;
//                }
//                for (DocumentChange dc : value.getDocumentChanges()){
//
//                    if(dc.getType() == DocumentChange.Type.ADDED){
//                        bookDetailsArrayList.add(dc.getDocument().toObject(BookDetails.class));
//                    }
//                    bookListAdapter.notifyDataSetChanged();
//                }
//            }
//        });
//    }
}