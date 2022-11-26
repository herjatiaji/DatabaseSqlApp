package com.pad1.databasesqlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText mNameEditText;
    EditText mPriceEditText;
    Button mInsertBtn;
    EditText mKeywordEditText;
    Button mSearchBtn;
    TextView mPriceResult;
    DatabaseHelper mDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameEditText = findViewById(R.id.edittext_name);
        mPriceEditText = findViewById(R.id.edittext_price);
        mInsertBtn = findViewById(R.id.btn_insert);
        mKeywordEditText = findViewById(R.id.edittext_search);
        mSearchBtn = findViewById(R.id.btn_search);
        mPriceResult = findViewById(R.id.price_result);

        mDatabaseHelper = new DatabaseHelper(this);

        mInsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPriceFromName(mKeywordEditText.getText().toString());
            }
        });
    }

    public void showAllProducts(){
        List<Product> products = mDatabaseHelper.getAllProduct();
        String result = "";
        for (int i=0; i<products.size(); i++ ){
            Product product = products.get(i);
            result = result + product.getId()+ ",";
        }
    }

    public void insertData(){
        Product product = new Product();
        product.setName(mNameEditText.getText().toString());
        product.setPrice(Integer.valueOf(mPriceEditText.getText().toString()));

        mDatabaseHelper.insertRecord(product);
        Toast.makeText(MainActivity.this,"succes", Toast.LENGTH_SHORT).show();
    }

    public void showPriceFromName(String keyword){
        Product product = mDatabaseHelper.getProductFromName(keyword);
        mPriceResult.setText(String.valueOf(product.getPrice()));
    }


}