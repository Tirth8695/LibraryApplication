package com.example.libraryapplication.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapplication.R;
import com.example.libraryapplication.models.BookDetails;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    Context context;
    ArrayList<BookDetails> bookDetailsArrayList;

    public ListAdapter(Context context, ArrayList<BookDetails> bookDetailsArrayList) {
        this.context = context;
        this.bookDetailsArrayList = bookDetailsArrayList;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_books,parent,false);
        
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        BookDetails bookdetails = bookDetailsArrayList.get(position);
        //get the element from the data set at this position and replace the
        //contents of the view with that element
        holder.bookTitle.setText(bookdetails.getTitle());
        holder.authorName.setText(bookdetails.getAuthorName());
        holder.numberOfPages.setText(bookdetails.getNumberOfPages());
    }

    @Override
    public int getItemCount() {

        return bookDetailsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText bookTitle, authorName, numberOfPages;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.booktitle_text);
            authorName = itemView.findViewById(R.id.authorbook_text);
            numberOfPages = itemView.findViewById(R.id.editTextNumber);
        }
    }
}
