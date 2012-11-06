package com.sqlite.manager;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sqlite.domain.User;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 19:48:11 04/11/2012
 */
public class SQLManager {
	
	private final String NAME_TABLE = "user";
	private final String USER_COLUM_NAME = "name";
	private final String USER_COLUM_PROFESSION = "profession";
	private final String USER_COLUM_AGE = "age";
	
	private final String NOME_BANCO = "app_cadastre_users";
	private final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY, name TEXT, profession TEXT, age INTEGER);";
	private final String DELETE_TABLE_USER = "DELETE FROM user;";
	
	private SQLiteDatabase db;
	
	public SQLManager(Context context){
		db = context.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
		db.execSQL(CREATE_TABLE);
	}
	
	public long insert(String name, String profession, Integer age){
		ContentValues values = new ContentValues();
		values.put(USER_COLUM_NAME, name);
		values.put(USER_COLUM_PROFESSION, profession);
		values.put(USER_COLUM_AGE, age);
		
		long id = db.insert(NAME_TABLE, "", values);
		return id;
	}
	
	public List<User> retrieveListUser(){
		
		String[] colunas = { "name", "profession", "age" };
		List<User> listOfUsuarios = new ArrayList<User>();

		Cursor c = db.query(NAME_TABLE, colunas, null, null, null,null, null, null);
		
		if(c.getCount() > 0){
			
			c.moveToFirst();
			for(int x = 0; x < c.getCount(); x++){
				listOfUsuarios.add(new User(c.getString(0), c.getString(1), Integer.valueOf(c.getString(2))));
				c.moveToNext();
			}
		}

		return listOfUsuarios;
	}
	
	public String[] searchByColumn(String column, String valueSearch){
		
		String[] results = null;
		String[] columns = { "name", "profession", "age" };
		Cursor c = db.query(NAME_TABLE, columns, column + "='" + valueSearch + "'", null, null, null, null);
		
		if(c.getCount() > 0){
			
			results = new String[c.getCount()];
			c.moveToFirst();
			
			for(int x = 0; x < c.getCount(); x++){
				results[x] = c.getString(0);
				c.moveToNext();
			}
		}
		
		return results;
	}
	
	public void cleanDB(){
		db.execSQL(DELETE_TABLE_USER);
	}
}