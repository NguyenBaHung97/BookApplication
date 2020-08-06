package com.hthh.bookapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hthh.bookapp.R;
import com.hthh.bookapp.Utils;
import com.hthh.bookapp.activity.FirstActivity;
import com.hthh.bookapp.activity.MainActivity;
import com.hthh.bookapp.model.UserData;
import com.hthh.bookapp.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFragment extends Fragment {
    private EditText edtEmail;
    private EditText edtPassword;
    private TextView txtSignUp;
    private TextView txtStartLogin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (R.layout.fragment_signup == 0) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            View view = inflater.inflate(R.layout.fragment_signup, container, false);
            initView(view);
            listener();
            return view;
        }
    }

    private void initView(View view) {
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtPassword);
        txtSignUp = view.findViewById(R.id.txtSignUp);
        txtStartLogin = view.findViewById(R.id.txtStartLogin);
    }

    private void listener() {
        txtStartLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtEmail.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Vui lòng điền đầy đủ thông đăng ký", Toast.LENGTH_SHORT).show();
                } else {
                    callApiSignUp(edtEmail.getText().toString(), edtPassword.getText().toString());
                }
            }
        });
    }

    public void callApiSignUp(String email, String password) {
        Call<UserData> call = RetrofitClient.getService().signup(email, password);
        Callback<UserData> callback = new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                if (response.body().getStatus() == 0) {
                    Toast.makeText(getActivity(), "Email hoặc mật khẩu đã tồn tại", Toast.LENGTH_SHORT).show();
                } else {
                    Utils.setUser(getActivity(),Integer.parseInt(response.body().getData().getId_user()));
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Toast.makeText(getActivity(), "Email hoặc mật khẩu đã tồn tại", Toast.LENGTH_SHORT).show();
            }
        };
        call.enqueue(callback);
    }


}
