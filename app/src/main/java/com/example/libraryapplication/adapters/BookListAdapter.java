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

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {
    Context context;
    ArrayList<BookDetails> bookDetailsArrayList;

    public BookListAdapter(Context context, ArrayList<BookDetails> bookDetailsArrayList) {
        this.context = context;
        this.bookDetailsArrayList = bookDetailsArrayList;
    }

    @NonNull
    @Override
    public BookListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_books,parent,false);
        
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListAdapter.ViewHolder holder, int position) {
        BookDetails bookdetails = bookDetailsArrayList.get(position);
        //get the element from the data set at this position and replace the
        //contents of the view with that element
        holder.bookTitle.setText(bookdetails.getTitle());
        holder.authorName.setText(bookdetails.getAuthorName());
        holder.numberOfPages.setText(String.valueOf(bookdetails.getNumberOfPages()));
    }

    @Override
    public int getItemCount() {

        return bookDetailsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle, authorName, numberOfPages;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.textView);
            authorName = itemView.findViewById(R.id.textView2);
            numberOfPages = itemView.findViewById(R.id.textView3);
        }
    }
}
