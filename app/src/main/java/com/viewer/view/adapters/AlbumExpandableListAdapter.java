package com.viewer.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.viewer.R;
import com.viewer.model.AlbumInfo;

import java.util.List;

/**
 * Created by ArthurXu on 13/04/2015.
 */
public class AlbumExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<AlbumInfo> albumInfos;

    public AlbumExpandableListAdapter(Context context, List<AlbumInfo> albumInfos) {
        this.context = context;
        this.albumInfos = albumInfos;
    }

    public List<AlbumInfo> getAlbumInfos() {
        return albumInfos;
    }

    public void setAlbumInfos(List<AlbumInfo> albumInfos) {
        this.albumInfos = albumInfos;
    }

    @Override
    public int getGroupCount() {
        return albumInfos.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i2) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i2) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_albumlist_group, null);
        }

        TextView nameText = (TextView) view.findViewById(R.id.list_albumlist_group_name);
        TextView subText = (TextView) view.findViewById(R.id.list_albumlist_group_subtitle);
        ImageView thumbnail = (ImageView) view.findViewById(R.id.list_albumlist_group_thumbnail);

        AlbumInfo albumInfo = albumInfos.get(i);

        nameText.setText(albumInfo.getName());
        subText.setText(albumInfo.getSubtitle());
        thumbnail.setImageBitmap(albumInfo.getBitmap());

        return view;
    }

    @Override
    public View getChildView(int i, int i2, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_albumlist_child, null);
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return false;
    }
}
