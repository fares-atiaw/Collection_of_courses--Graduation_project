package com.example.cofc.interfaces;

import com.example.cofc.model.Data_of_course;

public interface Listener_ID
{
    void onClick(int category_ID , String name);
    void onLongClick(int category_ID , String name , Data_of_course data , int position);
}
