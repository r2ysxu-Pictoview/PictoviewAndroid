package com.viewer.view.controller;

import android.widget.ExpandableListView;

/**
 * Created by ArthurXu on 13/04/2015.
 */
public class AlbumExpandableListListener implements ExpandableListView.OnGroupExpandListener {

    private int pGroup = -1;
    private ExpandableListView expandableListView;

    public AlbumExpandableListListener(ExpandableListView expandableListView) {
        this.expandableListView = expandableListView;
    }

    public int getSelectedGroup() {
        return pGroup;
    }

    @Override
    public void onGroupExpand(int i) {
        if (i != pGroup)
            expandableListView.collapseGroup(pGroup);
        pGroup = i;
    }
}
