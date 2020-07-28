package com.example.belajarandroid1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtWidth;
    private EditText edtHeight;
    private EditText edtLenght;
    private Button btnCalculate;
    private TextView tvResult;
    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLenght = findViewById(R.id.edt_length);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(this);

        if(savedInstanceState != null){
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    //ini adalah function untuk mengecek nilai angka sesuai atau tidaknya
    private Double toDouble(String str){
        try{
            return Double.valueOf(str);
        }catch (NumberFormatException e){
            return null;
        }
    }

    @Override
    //ini adalah function untuk melakukan serangkaian aktifitas jika hitung di klik
    public void onClick(View v) {
        if(v.getId() == R.id.btn_calculate){
            String inputLength = edtLenght.getText().toString().trim();
            String inputWidth = edtWidth.getText().toString().trim();
            String inputHeight = edtHeight.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if(TextUtils.isEmpty(inputLength)){
                isEmptyFields = true;
                edtLenght.setError("field ini tidak boleh kosong!!!");
            }
            if(TextUtils.isEmpty(inputHeight)){
                isEmptyFields = true;
                edtHeight.setError("field ini tidak boleh kosong!!!");
            }
            if (TextUtils.isEmpty(inputWidth)){
                isEmptyFields = true;
                edtWidth.setError("field ini tidak boleh kosong!!!");
            }

            Double length = toDouble(inputLength);
            Double width = toDouble(inputWidth);
            Double height = toDouble(inputHeight);

            if(length == null){
                isInvalidDouble = true;
                edtLenght.setError("Field Ini harus berupa angka yang valid");
            }

            if(width == null){
                isInvalidDouble = true;
                edtWidth.setError("Field ini Harus berupa angka yang valid");
            }
            if(height == null){
                isInvalidDouble = true;
                edtHeight.setError("Field ini Harus berupa angka yang valid");
            }

            if(!isEmptyFields && !isInvalidDouble){
                double volume = length*width*height;
                tvResult.setText(String.valueOf(volume));
            }

        }
    }


}