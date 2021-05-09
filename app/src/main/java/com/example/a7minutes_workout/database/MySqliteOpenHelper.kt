package com.example.a7minutes_workout.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.a7minutes_workout.model.Historydataclass

class MySqliteOpenHelper(context: Context,factory: SQLiteDatabase.CursorFactory?):SQLiteOpenHelper(context, data_base_name,factory,
    data_base_version
) {
    companion object{
        var data_base_version = 1
        var data_base_name= "Exercise_DataBase"
        var data_base_table= "Exercise_History"
        var column_id="id"
        var column_history="history"
    }

    override fun onCreate(db: SQLiteDatabase?) {
           var create_query="CREATE TABLE "+ data_base_table + "("+ column_id + " INTEGER PRIMARY KEY ,"+ column_history + " TEXT);"
          db?.execSQL(create_query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db?.execSQL("DROP TABLE IF EXISTS"+ data_base_table)
        onCreate(db)
    }
    fun addDATE(date:String){
        var data_column = ContentValues()
        data_column.put(column_history,date)
        var db=this.writableDatabase
        db.insert(data_base_table,null,data_column)
        db.close()
    }


    fun read_all_date_completed_history():ArrayList<Historydataclass>{
        var all_data=ArrayList<Historydataclass>()
        var readable_data_base=this.readableDatabase
        var cursor=readable_data_base.rawQuery("SELECT * FROM $data_base_table",null)
        while (cursor.moveToNext()){
            var data_colmn_date=(cursor.getString(cursor.getColumnIndex(column_history)))
            var data_colmn_id=(cursor.getInt(cursor.getColumnIndex(column_id)))
            all_data.add(Historydataclass(data_colmn_id,data_colmn_date))
        }
        cursor.close()
        return all_data

    }
}