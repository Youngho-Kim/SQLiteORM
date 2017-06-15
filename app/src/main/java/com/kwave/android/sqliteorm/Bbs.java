package com.kwave.android.sqliteorm;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by kwave on 2017-06-09.
 */

@DatabaseTable(tableName = "bbs")
public class Bbs {
    @DatabaseField
    private int id;
}
