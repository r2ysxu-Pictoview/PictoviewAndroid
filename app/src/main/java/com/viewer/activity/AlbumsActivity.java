package com.viewer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.viewer.R;
import com.viewer.model.AlbumInfo;
import com.viewer.requests.AlbumRequestHandler;
import com.viewer.view.adapters.AlbumExpandableListAdapter;
import com.viewer.view.controller.AlbumExpandableListListener;

import java.util.ArrayList;
import java.util.List;

public class AlbumsActivity extends Activity {

    private AlbumExpandableListAdapter albumListAdapter;
    private AlbumExpandableListListener albumListListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        fillAlbumList();
    }

    private void fillAlbumList() {
        final ExpandableListView albumList = (ExpandableListView) findViewById(R.id.albums_expandableListView);
        AlbumRequestHandler handler = new AlbumRequestHandler(this);

        // Fill List
        List<AlbumInfo> albumInfos = new ArrayList<AlbumInfo>();
        albumListAdapter = new AlbumExpandableListAdapter(this, albumInfos);
        albumList.setAdapter(albumListAdapter);
        handler.loadAlbumList(albumListAdapter);

        //albumListListener = new AlbumExpandableListListener(albumList);
    }
}
