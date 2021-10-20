package com.example.cofc.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.cofc.R;
import com.example.cofc.databinding.SignInBinding;
import com.example.cofc.special_model.R_7_IDs;
import com.example.cofc.special_model.S_SignIn;

import com.example.cofc.viewmodel.V_SignIn;

public class SignIn extends AppCompatActivity {

    SignInBinding binding;
    public SharedPreferences sp;
    public SharedPreferences.Editor edit;
    private static final String ID_S1 = "Student_ID";
    private static final String ID_I2 = "Instructor_ID";
    private static final String ID_C3 = "Center_ID";
    private static final String ID_F4 = "Favorite_ID";
    private static final String ID_CC5 = "CurrentCourses_ID";
    private static final String ID_W6 = "WatchLaterID";
    private static final String ID_C7 = "CartID";
    private static final String ARG_PARAM1 = "USER_IDs";
    private static final String ARG_PARAM3 = "category type";
    private static final String CHOICE = "choice";
    private S_SignIn sign;
    V_SignIn mLive;
    private R_7_IDs id_s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.sign_in);

        sp = this.getSharedPreferences("USER_IDs" , MODE_PRIVATE);
        mLive = new ViewModelProvider(this).get(V_SignIn.class);

        implementations();

        mLive.mLive.observe(this, new Observer<R_7_IDs>() {
            @Override
            public void onChanged(R_7_IDs r_7_iDs) {
                id_s = r_7_iDs;
                Log.v("nooo", "ffffffffff");
                if (r_7_iDs != null) {
                    Log.v("kii", "ffffffffff");
                    edit = sp.edit();
                    // Insert here the getting data ..........
                    edit.putInt(ID_S1, r_7_iDs.getStudentID());
                    edit.putInt(ID_I2, r_7_iDs.getInstructorID());
                    edit.putInt(ID_C3, r_7_iDs.getCenterID());
                    edit.putInt(ID_F4, r_7_iDs.getFavoriteID());
                    edit.putInt(ID_CC5, r_7_iDs.getCurrentCoursesID());
                    edit.putInt(ID_W6, r_7_iDs.getWatchLaterID());
                    edit.putInt(ID_C7, r_7_iDs.getCartID());
                    edit.apply();
                }
            }
        });

        binding.siBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sign = new S_SignIn(binding.siEtEmail.getText().toString() , binding.siEtPassword.getText().toString());
                Log.v("dddd" , sign.getEmail() +"\n"+sign.getPassword());
                mLive.post_login(sign);

                try {
                    if(mLive != null){
                        Toast.makeText(SignIn.this, "id_s.getStudentID()", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SignIn.this, Main_Category.class));
                    }
                    else
                        Toast.makeText(SignIn.this, "Invalid username and*or password", Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    Toast.makeText(SignIn.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.siBtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(SignIn.this , SignUp.class));
            }
        });


    }

    void implementations()
    {
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = binding.siEtEmail.getText().toString().trim();
                String password = binding.siEtPassword.getText().toString().trim();

                binding.siBtnLogin.setEnabled(!email.isEmpty() && !password.isEmpty());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        binding.siEtEmail.addTextChangedListener(watcher);
        binding.siEtPassword.addTextChangedListener(watcher);
    }
}