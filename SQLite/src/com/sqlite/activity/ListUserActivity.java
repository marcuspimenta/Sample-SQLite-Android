package com.sqlite.activity;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.widget.ListView;

import com.sqlite.R;
import com.sqlite.manager.SQLManager;
import com.sqlite.view.UserAdapter;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 20:28:44 04/11/2012
 */
@ContentView(R.layout.list_view_user)
public class ListUserActivity extends RoboActivity{
	
	@InjectView(R.id.listView)
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		configViews();
	}
	
	public void configViews(){
		SQLManager sqlManager = new SQLManager(ListUserActivity.this);
		UserAdapter adapter = new UserAdapter(ListUserActivity.this, sqlManager.retrieveListUser());
		
		listView.setAdapter(adapter);
	}

}