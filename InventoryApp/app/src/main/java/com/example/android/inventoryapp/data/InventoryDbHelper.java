package com.example.android.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Chris on 1/29/2018.
 */

public class InventoryDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "inventory.db";

    private static final int DATABASE_VERSION = 1;

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE " +
                InventoryContract.InventoryEntry.TABLE_NAME + " ("
                + InventoryContract.InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME + " NOT NULL, "
                + InventoryContract.InventoryEntry.COLUMN_PRODUCT_QUANTITY + " NOT NULL, "
                + InventoryContract.InventoryEntry.COLUMN_PRODUCT_PRICE + " NOT NULL, "
                + InventoryContract.InventoryEntry.COLUMN_PRODUCT_SUPPLIER_EMAIL + " NOT NULL, "
                + InventoryContract.InventoryEntry.COLUMN_PRODUCT_IMAGE + " NOT NULL);";

        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
