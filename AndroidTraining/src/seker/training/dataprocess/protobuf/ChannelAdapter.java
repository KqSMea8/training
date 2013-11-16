/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training.dataprocess.protobuf;

import seker.training.R;
import seker.training.dataprocess.protobuf.ChannelProto.Channel;
import seker.training.dataprocess.protobuf.ChannelProto.Channel.Item;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 
 * @author seker
 * @since 2013年11月9日
 */
public class ChannelAdapter extends BaseAdapter implements OnItemClickListener {
    
    private final Channel mChannel;
    
    public ChannelAdapter(Channel channel) {
        mChannel = channel;
    }

    @Override
    public int getCount() {
        return mChannel.getItemList().size();
    }

    @Override
    public Object getItem(int position) {
        return mChannel.getItemList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (null == convertView) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_item, parent, false);
            
            ViewHolder holder = new ViewHolder();
            view.setTag(holder);
            
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.content = (TextView) view.findViewById(R.id.content);
            holder.url = (TextView) view.findViewById(R.id.url);
            holder.date = (TextView) view.findViewById(R.id.date);
        } else {
            view = convertView; 
        }
        
        buildView(view, position);
        return view;
    }
    
    private void buildView(View view, int position) {
        Item item = mChannel.getItemList().get(position);
        
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.title.setText(item.getTitle());
        holder.content.setText(Html.escapeHtml(item.getDescription()));
        holder.content.setVisibility(View.GONE);
        holder.url.setText(item.getGuid().getUrl());
        holder.date.setText(item.getPubDate());
    }
    
    class ViewHolder {
        TextView title;
        TextView content;
        TextView url;
        TextView date;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Item item = mChannel.getItemList().get(position);
        
        Uri uri = Uri.parse(item.getGuid().getUrl());  
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);  
        parent.getContext().startActivity(intent);
    }

}
