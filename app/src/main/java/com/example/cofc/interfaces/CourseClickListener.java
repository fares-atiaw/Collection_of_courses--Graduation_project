package com.example.cofc.interfaces;

import com.example.cofc.model.Data_of_course;

public interface CourseClickListener
{
    void onClick(Data_of_course data);
    void onLongClick(Data_of_course data , int position);
}
