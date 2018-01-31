package com.example.android.inventoryapp;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.inventoryapp.data.InventoryContract;

import javax.crypto.spec.IvParameterSpec;

/**
 * Created by Chris on 1/30/2018.
 */

public class CreateActivity extends AppCompatActivity  {

    private static final int EXISTING_INVENTORY_LOADER = 0;
    private Uri mCurrentProductUri;
    private EditText mNameEditText;
    private EditText mQuantityEditText;
    private EditText mPriceEditText;
    private EditText mEmailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Intent intent = getIntent();
        mCurrentProductUri = intent.getData();

        mNameEditText = (EditText) findViewById(R.id.edit_name);
        mQuantityEditText = (EditText) findViewById(R.id.edit_quantity);
        mPriceEditText = (EditText) findViewById(R.id.edit_price);
        mEmailEditText = (EditText) findViewById(R.id.edit_provider_email);

        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                saveProduct();
                finish();
            }
        });

    }

    private void saveProduct() {
        String nameString = mNameEditText.getText().toString().trim();
        String quantityString = mQuantityEditText.getText().toString().trim();
        String priceString = mPriceEditText.getText().toString().trim();
        String emailString = mEmailEditText.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME, nameString);
        values.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_QUANTITY, quantityString);
        values.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_PRICE, priceString);
        values.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_SUPPLIER_EMAIL, emailString);
        int quantity = 0;
        if (!TextUtils.isEmpty(quantityString)) {
            quantity = Integer.parseInt(quantityString);
        }
        values.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_QUANTITY, quantity);

        if (mCurrentProductUri == null) {
            Uri newUri = getContentResolver().insert(InventoryContract.InventoryEntry.CONTENT_URI,
                    values);
            if (newUri == null) {
                Toast.makeText(this, getString(R.string.create_product_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.create_product_success),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

}
