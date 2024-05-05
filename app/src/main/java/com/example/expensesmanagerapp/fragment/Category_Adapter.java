package com.example.expensesmanagerapp.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensesmanagerapp.R;
import com.example.expensesmanagerapp.databinding.SampleCategoryItemBinding;

import java.util.ArrayList;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.CategoryViewHolder> {

    //Context of the current activity
    Context context;
    //ArrayList of the Category_Model class
    ArrayList<Category_Model> arrayList;

    //creating a interface for handling the clicked event of "select Category" tag and display the selected category type in the field
    public interface CategoryClickListener{
        void onCategoryClicked(Category_Model categoryModel);
    }

    //obj of CategoryClickListener interface
    CategoryClickListener categoryClickListener;

    //constructor of the class with parameter
    public Category_Adapter(Context context, ArrayList<Category_Model> arrayList, CategoryClickListener categoryClickListener) {
        this.context = context;
        this.arrayList = arrayList;
        this.categoryClickListener= categoryClickListener;
    }

    //three methods of adapter class
    //1. onCreateViewHolder for inflating the layout
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.sample_category_item,parent,false));
    }

    //onBindViewHolder for binding the view and data of the Account_Model class
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        //getting position of the element and data of Category_Model
        Category_Model categoryModel = arrayList.get(position);
        //getting Category Name from position of Category_Model and setting it to UI tags of layout
        holder.binding.categoryTextView.setText(categoryModel.getCategoryName());
        //getting images from position/drawable folder of Category_Model and setting it to UI tags of layout
        holder.binding.categoryIcon.setImageResource(categoryModel.getCategoryImage());

        //setting background to the categoryIcon
        holder.binding.categoryIcon.setBackgroundTintList(context.getColorStateList(categoryModel.getCategoryColor()));

        //handling the onClick of the itemView
        holder.itemView.setOnClickListener(c ->{
            categoryClickListener.onCategoryClicked(categoryModel);
        });
    }

    //returning the ItemCount means size of array
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    //ViewHolder class extending from RecyclerView.ViewHolder
    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        //initiating View Binding here
        SampleCategoryItemBinding binding;

        //matching super constructor
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            //binding view via View Binding
            binding = SampleCategoryItemBinding.bind(itemView);
        }
    }
}
