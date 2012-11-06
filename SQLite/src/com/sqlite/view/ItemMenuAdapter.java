package com.sqlite.view;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sqlite.R;

/**
 * 
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 19:49:59 04/11/2012
 */
public class ItemMenuAdapter extends BaseAdapter {
    
	private Context context;

    private List<ItemMenu> listItemMenu;

    public ItemMenuAdapter(Context context, List<ItemMenu> listItemMenu) {
        this.context = context;
    	this.listItemMenu = listItemMenu;
    }

    public int getCount() {
        return listItemMenu.size();
    }

    public Object getItem(int position) {
        return listItemMenu.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
    	ItemMenu entry = listItemMenu.get(position);
        
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_menu, null);
        }
        
        TextView nameItemMenu = (TextView) convertView.findViewById(R.id.nameItemMenu);
        nameItemMenu.setText(entry.getLable());

        return convertView;
    }

}