package com.example.cofc.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.cofc.R;
import com.example.cofc.databinding.SignUpBinding;
import com.example.cofc.special_model.R_IsSuccess;
import com.example.cofc.special_model.S_SignUp;
import com.example.cofc.viewmodel.V_SignIn;
import com.example.cofc.viewmodel.V_SignUp;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    SignUpBinding binding;
    private S_SignUp sign;
    private R_IsSuccess isSuccess;
    V_SignUp mLive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.sign_up);

        mLive = new ViewModelProvider(this).get(V_SignUp.class);

        implementations();
        buttons();

        mLive.mLive.observe(this, new Observer<R_IsSuccess>() {
            @Override
            public void onChanged(R_IsSuccess r_isSuccess) {
                isSuccess = r_isSuccess;
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
                String username = binding.suEtUsername.getText().toString().trim();
                String email = binding.suEtEmail.getText().toString().trim();
                String password = binding.suEtPassword.getText().toString().trim();
                String confirm_password = binding.suEtConfirmPassword.getText().toString().trim();

                binding.suBtnRegister.setEnabled(!email.isEmpty() && !username.isEmpty() && !password.isEmpty() && !confirm_password.isEmpty());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        binding.suEtUsername.addTextChangedListener(watcher);
        binding.suEtEmail.addTextChangedListener(watcher);
        binding.suEtPassword.addTextChangedListener(watcher);
        binding.suEtConfirmPassword.addTextChangedListener(watcher);
    }

    void buttons()
    {
        binding.suBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!(binding.suEtPassword.getText().toString().equals(binding.suEtConfirmPassword.getText().toString())))
                        Toast.makeText(SignUp.this, "Password and Confirm Password don't match", Toast.LENGTH_SHORT).show();
                    else{
                        String username = binding.suEtUsername.getText().toString();
                        String email = binding.suEtEmail.getText().toString();
                        String password = binding.suEtPassword.getText().toString();
                        String confirmPassword = binding.suEtConfirmPassword.getText().toString();
                        sign = new S_SignUp(username , email , password , confirmPassword);
                        mLive.post_signUp(sign);
                        Toast.makeText(SignUp.this, "Successful process", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUp.this, SignIn.class));
                    }
                }
                catch (Exception e){
                    Toast.makeText(SignUp.this, e.getMessage()+"\n Invalid username or password", Toast.LENGTH_LONG).show();
                }

            }
        });

        binding.suBtnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this , SignIn.class));
            }
        });
    }
}


/*public void ErrorMessage(TextInputEditText text)
    {
        if (text.getEditableText().toString().trim().isEmpty())
        {
            text.setError("Field can't be empty");
        }
        else {
            text.setError(null);
        }
    }
    ErrorMessage(binding.suEtUsername);
                ErrorMessage(binding.suEtEmail);
                ErrorMessage(binding.suEtPassword);
                ErrorMessage(binding.suEtConfirmPassword);
*/

/* implements AdapterView.OnItemSelectedListener

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/