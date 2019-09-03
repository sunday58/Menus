package com.example.android.menus;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    androidx.appcompat.view.ActionMode mActionMode;

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.actionmode_context_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

           switch (menuItem.getItemId()){
               case R.id.actionmodemenu1:
                   Toast.makeText(MainActivity.this, "You are On Planet Mars",
                           Toast.LENGTH_SHORT).show();
                   actionMode.finish();
                   return true;
               case R.id.actionmodemenu2:
                   Toast.makeText(MainActivity.this, "you missed Jupital Flight",
                           Toast.LENGTH_SHORT).show();
                   actionMode.finish();
                   return true;
               default:
                   return false;
           }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            mActionMode = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt = findViewById(R.id.txtTitle);
        this.registerForContextMenu(txt);

        Button button = findViewById(R.id.button);
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mActionMode != null){
                    return false;
                }
                mActionMode = startSupportActionMode((androidx.appcompat.view.ActionMode.Callback) mActionModeCallback);
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //handling options menu item click
        switch (item.getItemId()){
            case R.id.menuItem1:
                Toast.makeText(this, "Menu Item 1 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuItem2:
                Toast.makeText(this, "Menu Item 2 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuItem3:
                Toast.makeText(this, "Menu Item 3 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.floating_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.contextmenu1:
                Toast.makeText(this, "Welcome to France", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contextmenu2:
                Toast.makeText(this, "Welcome to Spain", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.popupmenu1:
                Toast.makeText(this, "Pop up Menu Loaded", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.popupmenu2:
                Toast.makeText(this, "Pop up menu Visible", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }
}
