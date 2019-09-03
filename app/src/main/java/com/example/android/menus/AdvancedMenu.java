package com.example.android.menus;

import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class AdvancedMenu extends AppCompatActivity {

    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;
    private ArrayList<String> planetList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_menu);

        mainListView = findViewById(R.id.mainListView);

        //create and populate list of planets
        String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
        "Jupital", "Satune", "Neptune", "Uranus"};
        planetList = new ArrayList<>();
        planetList.addAll(Arrays.asList(planets));

        //create an array Adapter
        listAdapter = new ArrayAdapter<>(this,R.layout.list_items, planetList);

        //set the array adapter
        mainListView.setAdapter(listAdapter);

        //Allows to select more than one item at a time
        mainListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        mainListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                //To do something when an item is selected or deselected
                mainListView.setBackgroundColor(Color.RED);
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                //inflate the menu item
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.listview_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.listDelete:
                        deleteSelectedItem();
                        actionMode.finish();
                        return true;
                    case R.id.listRefresh:
                        return true;
                    default:
                        return false;
                }

            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });

    }

    //delete selected item from list
    private void deleteSelectedItem(){

        //geting the checked item from the list
        SparseBooleanArray checkedItemPosition = mainListView.getCheckedItemPositions();
        int itemCount = mainListView.getCount();

        for (int i = itemCount - 1; i>=0; i--){
            if (checkedItemPosition.get(i)){
                listAdapter.remove(planetList.get(i));
            }
        }
        checkedItemPosition.clear();
        listAdapter.notifyDataSetChanged();
    }
}
