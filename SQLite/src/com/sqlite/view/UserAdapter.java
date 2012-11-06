package com.sqlite.view;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sqlite.R;
import com.sqlite.domain.User;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 19:48:46 04/11/2012
 */
public class UserAdapter extends BaseAdapter{
	
	private Context context;
    private List<User> listUser;

    public UserAdapter(Context context, List<User> listUser) {
        this.context = context;
    	this.listUser = listUser;
    }

    public int getCount() {
        return listUser.size();
    }

    public Object getItem(int position) {
        return listUser.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
    	User entry = listUser.get(position);
        
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_cadastre, null);
        }
        
        TextView nameUser = (TextView) convertView.findViewById(R.id.nameUser);
        nameUser.setText(entry.getName());
        
        TextView professionUser = (TextView) convertView.findViewById(R.id.professionUser);
        professionUser.setText(entry.getProfession());
        
        TextView ageUser = (TextView) convertView.findViewById(R.id.ageUser);
        ageUser.setText(entry.getAge().toString());

        return convertView;
    }

}