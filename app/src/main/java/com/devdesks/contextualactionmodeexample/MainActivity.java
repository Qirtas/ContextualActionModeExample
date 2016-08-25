package com.devdesks.contextualactionmodeexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {
    Toolbar toolbar;
    RecyclerView recycler_view;
    RecyclerView.LayoutManager layountManager;
    RecyclerView.Adapter adapter;
    int[] imag_id = {R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img };
    ArrayList<Contact> Contacts = new ArrayList<>();
    static boolean is_in_action_mode = false;
    TextView counter_textview;
    ArrayList<Contact> selectionList = new ArrayList<>();
    int counter = 0;
    Contact_Adapter contac_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.w(Contact.LOG, "&&&&&&&&&&&&&&&&&&&&&&&&&");


        counter_textview = (TextView) findViewById(R.id.selected_item_counter);
        counter_textview.setVisibility(View.GONE);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        layountManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(layountManager);
        recycler_view.setHasFixedSize(true);

        String[] Name;
        Name = getResources().getStringArray(R.array.name);
        int i=0;

        for(String NAME : Name)
        {
            Contact contact = new Contact(imag_id[i], NAME);
            Contacts.add(contact);
            i++;
        }

        adapter = new Contact_Adapter(Contacts, MainActivity.this);
        recycler_view.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_normal, menu);
        return true;
    }

    @Override
    public boolean onLongClick(View view)
    {
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_actionmode);
        counter_textview.setVisibility(View.VISIBLE);
        is_in_action_mode = true;
        adapter.notifyDataSetChanged();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return true;
    }

    public void prepareSelection(View view, int position)
    {
        if(((CheckBox) view).isChecked())
        {
            selectionList.add(Contacts.get(position));
            counter = counter + 1;
            update_Counter(counter);
        }
        else
        {
            selectionList.remove(Contacts.get(position));
            counter = counter - 1;
            update_Counter(counter);
        }
    }

    public void update_Counter(int counter)
    {
        if(counter == 0)
        {
            counter_textview.setText("0 item selected");
        }
        else
        {
            counter_textview.setText(counter +" item selected");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.item_delete)
        {
            Log.w(Contact.LOG, "item_delete");
            Contact_Adapter contact_adapter = (Contact_Adapter) adapter;
            contact_adapter.update_Adapter(selectionList);
            clear_action_mode();
        }
        else if(item.getItemId() == android.R.id.home)
        {
            clear_action_mode();
            adapter.notifyDataSetChanged();
        }

        return true;
    }

    public  void clear_action_mode()
    {
        is_in_action_mode = false;
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_normal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        counter_textview.setVisibility(View.GONE);
        counter_textview.setText("0 ITEM SELECTED");
        counter = 0;
        selectionList.clear();
    }

    @Override
    public void onBackPressed()
    {
        if(is_in_action_mode)
        {
            clear_action_mode();
            adapter.notifyDataSetChanged();
        }
        else
        {
            super.onBackPressed();
        }
    }
}
