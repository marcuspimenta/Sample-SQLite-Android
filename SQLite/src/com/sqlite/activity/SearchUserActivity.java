package com.sqlite.activity;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.sqlite.R;
import com.sqlite.manager.SQLManager;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 20:31:13 04/11/2012
 */
@ContentView(R.layout.search_user)
public class SearchUserActivity extends RoboActivity{
	
	@InjectView(R.id.rgOptions)
	private RadioGroup rbOptions;
	
	@InjectView(R.id.rb_name)
	private RadioButton rbName;
	
	@InjectView(R.id.rb_profission)
	private RadioButton rbProfession;
	
	@InjectView(R.id.rb_age)
	private RadioButton rbAge;
	
	@InjectView(R.id.editText)
	private EditText editText;
	
	@InjectView(R.id.research)
	private ImageButton research;
	
	private SQLManager sqlManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		sqlManager = new SQLManager(SearchUserActivity.this);
		
		configViews();
	}
	
	public void configViews(){
		rbName.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				editText.setInputType(InputType.TYPE_CLASS_TEXT);
			}
		});
		
		rbProfession.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				editText.setInputType(InputType.TYPE_CLASS_TEXT);
			}
		});
		
		rbAge.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				editText.setInputType(InputType.TYPE_CLASS_NUMBER);
			}
		});
		
		research.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String results[] = searchUsers();
				
				if(results != null && results.length > 0){
					AlertDialog dialog = new AlertDialog.Builder(SearchUserActivity.this).setTitle(getString(R.string.msg_results)).setItems(results, null).create();
					dialog.show();
				}else{
					Toast.makeText(SearchUserActivity.this, getString(R.string.msg_no_found_user), Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	
	public String[] searchUsers(){
		String results[] = null;
		
		switch (rbOptions.getCheckedRadioButtonId()) {
			case R.id.rb_name:
				results = sqlManager.searchByColumn("name", editText.getText().toString().trim());
				break;
				
			case R.id.rb_profission:
				results = sqlManager.searchByColumn("profession", editText.getText().toString().trim());
				break;
			
			case R.id.rb_age:
				results = sqlManager.searchByColumn("age", editText.getText().toString().trim());
				break;
		}
		
		editText.setText("");
		return results;
	}

}