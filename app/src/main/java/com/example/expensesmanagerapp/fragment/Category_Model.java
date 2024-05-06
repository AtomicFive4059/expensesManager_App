package com.example.expensesmanagerapp.fragment;

// model class of Category
public class Category_Model {

    //Initiating UI elements in java code
    private String categoryName;
    private int categoryImage;
    private int categoryColor;

    //empty constructor of the class
    public Category_Model() {
    }

    //parameterized constructor of the class
    public Category_Model(String categoryName, int categoryImage, int categoryColor) {
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.categoryColor = categoryColor;
    }


    // getters and setters of the layout UI

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(int categoryImage) {
        this.categoryImage = categoryImage;
    }

    public int getCategoryColor() {
        return categoryColor;
    }

    public void setCategoryColor(int categoryColor) {
        this.categoryColor = categoryColor;
    }

}
