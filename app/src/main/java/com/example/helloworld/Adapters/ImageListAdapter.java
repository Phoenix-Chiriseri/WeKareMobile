package com.example.helloworld.Adapters;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.Models.ListItem;
import com.example.helloworld.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ImageListAdapter extends BaseAdapter {
    private Context context;
    private List<ListItem> itemList;

    public ImageListAdapter(Context context, List<ListItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.list_item, null);
        }

        CircleImageView itemImage = convertView.findViewById(R.id.itemImage);
        TextView itemTitle = convertView.findViewById(R.id.itemTitle);

        ListItem item = itemList.get(position);

        itemImage.setImageResource(item.getImageResource());
        itemTitle.setText(item.getTitle());

        return convertView;
    }

    public static class SaveNotes {
    }
}
