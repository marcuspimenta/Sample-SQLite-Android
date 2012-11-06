package com.sqlite.activity;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sqlite.R;
import com.sqlite.manager.SQLManager;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 20:30:40 04/11/2012
 */
@ContentView(R.layout.cadastre)
public class CadastreUserActivity extends RoboActivity{
	
	@InjectView(R.id.name)
	private EditText name;
	
	@InjectView(R.id.profession)
	private EditText profession;
	
	@InjectView(R.id.age)
	private EditText age;
	
	@InjectView(R.id.bn_cadastre)
	private Button bnCadastre;
	
	private SQLManager sqlManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		sqlManager = new SQLManager(CadastreUserActivity.this);
		configViews();
	}
	
	public void configViews(){
		bnCadastre.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				if(!name.getText().toString().trim().equals("") && !profession.getText().toString().trim().equals("") && 
				   !age.getText().toString().trim().equals("")){
					sqlManager.insert(name.getText().toString().trim(), profession.getText().toString().trim(), Integer.valueOf(age.getText().toString().trim()));
					
					name.setText("");
					profession.setText("");
					age.setText("");
				}
				else{
					Toast.makeText(CadastreUserActivity.this, getString(R.string.msg_fill_fields), Toast.LENGTH_LONG).show();
				}
			}
		});
	}

}