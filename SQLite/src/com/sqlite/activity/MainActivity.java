package com.sqlite.activity;

import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.sqlite.R;
import com.sqlite.manager.SQLManager;
import com.sqlite.view.ItemMenu;
import com.sqlite.view.ItemMenuAdapter;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 19:39:40 04/11/2012
 */
@ContentView(R.layout.list_view_menu)
public class MainActivity extends RoboActivity {

	@InjectView(R.id.listViewItemMenu)
	private ListView listViewItemMenu;
	
	private SQLManager sqlManager;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        sqlManager = new SQLManager(MainActivity.this);
        
        configViews();
    }
    
    public void configViews(){
    	List<ItemMenu> listOfMenu = new ArrayList<ItemMenu>();
        listOfMenu.add(new ItemMenu(getString(R.string.menu_cadastre_user)));
        listOfMenu.add(new ItemMenu(getString(R.string.menu_list_user)));
        listOfMenu.add(new ItemMenu(getString(R.string.menu_search_user)));
        listOfMenu.add(new ItemMenu(getString(R.string.menu_clean_db)));
        
        ItemMenuAdapter adapter = new ItemMenuAdapter(MainActivity.this, listOfMenu);
        listViewItemMenu.setAdapter(adapter);
        
        listViewItemMenu.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
            	switch (position) {
	    			case 0:
	    				startActivity(new Intent(MainActivity.this, CadastreUserActivity.class));
	    				break;
	    			
	    			case 1:
	    				if(sqlManager.retrieveListUser().size() > 0){
	    					startActivity(new Intent(MainActivity.this, ListUserActivity.class));
	    				}else{
	    					 Toast.makeText(MainActivity.this, getString(R.string.msg_no_registration), Toast.LENGTH_LONG).show();
	    				}
	    				break;
	    			
	    			case 2:
	    				if(sqlManager.retrieveListUser().size() > 0){
	    					startActivity(new Intent(MainActivity.this, SearchUserActivity.class));
	    				}else{
	    					 Toast.makeText(MainActivity.this, getString(R.string.msg_no_registration), Toast.LENGTH_LONG).show();
	    				}
	    				break;
	    			
	    			case 3:
	    				AlertDialog.Builder alertConfig = new AlertDialog.Builder(MainActivity.this);
	    				alertConfig.setMessage(R.string.msg_clean_db);
	    				
	    				alertConfig.setPositiveButton(getString(R.string.bn_confirm), new DialogInterface.OnClickListener() {
	    			    	public void onClick(DialogInterface dialog, int whichButton) {
	    			    		sqlManager.cleanDB();	    			    	}
	    			    });
	    				
	    				alertConfig.setNegativeButton(getString(R.string.bn_cancel), new DialogInterface.OnClickListener() {
	    			    	public void onClick(DialogInterface dialog, int whichButton) {
	    				    }
	    			    });
	    				
	    				alertConfig.create().show();
	    				break;
            	}
            }
        });
    }
    
    @Override
    public void onBackPressed() {
    	AlertDialog.Builder alertConfig = new AlertDialog.Builder(MainActivity.this);
		alertConfig.setMessage(R.string.msg_exit);
		
		alertConfig.setPositiveButton(getString(R.string.bn_confirm), new DialogInterface.OnClickListener() {
	    	public void onClick(DialogInterface dialog, int whichButton) {
	    		finish();
	    	}
	    });
		
		alertConfig.setNegativeButton(getString(R.string.bn_cancel), new DialogInterface.OnClickListener() {
	    	public void onClick(DialogInterface dialog, int whichButton) {
		    }
	    });
		
		alertConfig.create().show();
    }

}