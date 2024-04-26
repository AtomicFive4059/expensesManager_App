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

    Context context;
    ArrayList<Category_Model> arrayList;

    public interface CategoryClickListener{
        void onCategoryClicked(Category_Model categoryModel);
    }

    CategoryClickListener categoryClickListener;

    public Category_Adapter(Context context, ArrayList<Category_Model> arrayList, CategoryClickListener categoryClickListener) {
        this.context = context;
        this.arrayList = arrayList;
        this.categoryClickListener= categoryClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.sample_category_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category_Model categoryModel = arrayList.get(position);
        holder.binding.categoryTextView.setText(categoryModel.getCategoryName());
        holder.binding.categoryIcon.setImageResource(categoryModel.getCategoryImage());

        holder.binding.categoryIcon.setBackgroundTintList(context.getColorStateList(categoryModel.getCategoryColor()));

        holder.itemView.setOnClickListener(c ->{
            categoryClickListener.onCategoryClicked(categoryModel);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        SampleCategoryItemBinding binding;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = SampleCategoryItemBinding.bind(itemView);
        }
    }
}
