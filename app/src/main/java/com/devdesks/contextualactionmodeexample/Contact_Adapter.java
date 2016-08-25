package com.devdesks.contextualactionmodeexample;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Devdesk on 8/24/2016.
 */
public class Contact_Adapter extends RecyclerView.Adapter<Contact_Adapter.Contact_ViewHolder> {

    ArrayList<Contact> adapter_list = new ArrayList<>();
    MainActivity main_activity;
    Context ctx;

    public Contact_Adapter(ArrayList<Contact> adapterlist, Context ctxx)
    {
        this.adapter_list = adapterlist;
        this.ctx = ctxx;
        this.main_activity = (MainActivity) ctxx;
    }


    @Override
    public Contact_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cardview, parent, false);
        Contact_ViewHolder contact_viewHolder = new Contact_ViewHolder(view, main_activity);

        return contact_viewHolder;
    }

    @Override
    public void onBindViewHolder(Contact_ViewHolder holder, int position)
    {
        holder.img.setImageResource(adapter_list.get(position).getImg_id());
        holder.tv_name.setText(adapter_list.get(position).getName());

        if(!MainActivity.is_in_action_mode)
        {
            holder.chkbox.setVisibility(View.GONE);
        }
        else
        {
            holder.chkbox.setVisibility(View.VISIBLE);
            holder.chkbox.setChecked(false);
        }
    }

    @Override
    public int getItemCount()
    {
        return adapter_list.size();
    }

    public static class Contact_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView img;
        TextView tv_name;
        TextView tv_email;
        CheckBox chkbox;
        MainActivity main_activity;
        CardView card_view;


        public Contact_ViewHolder(View itemView, MainActivity mainactivity )
        {
            super(itemView);
            img =  (ImageView) itemView.findViewById(R.id.img_view);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            chkbox = (CheckBox) itemView.findViewById(R.id.chkbox);
            this.main_activity = mainactivity;
            card_view =  (CardView) itemView.findViewById(R.id.card_view);
            card_view.setOnLongClickListener(main_activity);
            chkbox.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            main_activity.prepareSelection(view, getAdapterPosition());
        }
    }

    public void update_Adapter(ArrayList<Contact> list)
    {
        for(Contact contact : list)
        {
            Log.v(Contact.LOG, "contact update_Adapter" + contact.getName());
            adapter_list.remove(contact);
        }

        for(Contact contact : adapter_list)
        {
            Log.i(Contact.LOG, "contact adapter_list" + contact.getName());

        }

        notifyDataSetChanged();
    }

}
