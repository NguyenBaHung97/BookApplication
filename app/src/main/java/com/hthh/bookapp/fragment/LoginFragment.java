package com.hthh.bookapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class LoginFragment extends Fragment {
    private EditText edtEmail;
    private EditText edtPassword;
    private TextView txtLogin;
    private TextView txtStartSignUp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (R.layout.fragment_login == 0) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            View view = inflater.inflate(R.layout.fragment_login, container, false);
            initView(view);
            listener();
            return view;
        }
    }

    private void initView(View view) {
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtPassword);
        txtLogin = view.findViewById(R.id.txtLogin);
        txtStartSignUp = view.findViewById(R.id.txtStartSignUp);
    }

    public void listener() {
        txtStartSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FirstActivity) getActivity()).startSignup();
            }
        });
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtEmail.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Vui lòng điền đầy đủ thông đăng nhập", Toast.LENGTH_SHORT).show();
                } else {
                    callApiLogin(edtEmail.getText().toString(), edtPassword.getText().toString());
                }
            }
        });
    }

    public void callApiLogin(String email, String password) {
        Utils.showLoadingDialog(getActivity());
        Call<UserData> call = RetrofitClient.getService().login(email, password);
        Callback<UserData> callback = new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                Utils.hideLoadingDialog();
                if (response.body().getStatus() == 0) {
                    Toast.makeText(getActivity(), "Email hoặc mật khẩu chưa chính xác", Toast.LENGTH_SHORT).show();
                } else {
                    Utils.setUser(getActivity(),response.body().getData().getId_user());
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Utils.hideLoadingDialog();
                Toast.makeText(getActivity(), "Email hoặc mật khẩu chưa chính xác", Toast.LENGTH_SHORT).show();
            }
        };
        call.enqueue(callback);
    }
}
