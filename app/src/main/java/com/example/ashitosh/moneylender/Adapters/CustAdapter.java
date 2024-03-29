package com.example.ashitosh.moneylender.Adapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ashitosh.moneylender.CollectMoneyFragment;
import com.example.ashitosh.moneylender.Fragments.CustCollectionFragment;
import com.example.ashitosh.moneylender.Fragments.CustDetailFragment;
import com.example.ashitosh.moneylender.Models.AgentModel;
import com.example.ashitosh.moneylender.R;
import com.example.ashitosh.moneylender.Models.custModel;

import java.util.ArrayList;
import java.util.List;

//********************************************************p***************
public class CustAdapter extends RecyclerView.Adapter<CustAdapter.ViewHolder>
{

    ArrayList<custModel> list;
    FragmentManager manager;
    String frag,agentEmail;

    public CustAdapter(ArrayList<custModel> list, FragmentManager supportFragmentManager, String agentHome) {
        this.list = list;
        manager=supportFragmentManager;
        this.frag=agentHome;
    }

    public CustAdapter(ArrayList<custModel> list, String email, FragmentManager supportFragmentManager, String agentHome) {
        this.list = list;
        manager=supportFragmentManager;
        this.frag=agentHome;
        this.agentEmail=email;
    }


    @NonNull
    @Override
    public CustAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.cust_head_layout,parent,false);

        ViewHolder holder=new ViewHolder(v);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull CustAdapter.ViewHolder holder, final int position) {

        holder.name.setText(list.get(position).getCustName());
        holder.address.setText(list.get(position).getCustAddr());
        holder.account.setText(list.get(position).getAccountNo());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle data = new Bundle();

                data.putString("CustName", list.get(position).getCustName());
                data.putString("Address", list.get(position).getCustAddr());
                data.putString("Phone", list.get(position).getCustPhone());
                data.putString("CustEmail", list.get(position).getCustEmail());
                data.putString("Account", list.get(position).getAccountNo());
                data.putString("TotalLoans", list.get(position).getCustTotalLoan());


                data.putString("CustAdharId", list.get(position).getCustAdharId());
                data.putString("CustDob", list.get(position).getCustDob());
                data.putString("GuarantorName", list.get(position).getGuarantorName());
                data.putString("GuarantorMob", list.get(position).getGuarantorMob());
                data.putString("GuarantorAddr", list.get(position).getGuarantorAddr());


                if (frag.equals("ActiveCustCollection")) {
                    CollectMoneyFragment fragment = new CollectMoneyFragment();
                    fragment.setArguments(data);

                    android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction().add(fragment, "ActiveCusthead").addToBackStack("Activehead");

                    fragmentTransaction.replace(R.id.AgentmainFrame, fragment);

                    fragmentTransaction.commit();

                }
                else if (frag.equals("ClientsFragment"))
                {

                    data.putString("fragment", "Owner");
                    if (!agentEmail.isEmpty()) {

                        data.putString("AgentEmail", agentEmail);
                    }



                    CustDetailFragment fragment = new CustDetailFragment();

                    fragment.setArguments(data);

                    android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction().add(fragment, "Custhead").addToBackStack("head");

                    fragmentTransaction.replace(R.id.mainFrame, fragment);

                    fragmentTransaction.commit();
                }
                else {
                    if (frag.equals("Owner")) {
                        data.putString("fragment", "Owner");

                    } else {
                        data.putString("fragment", "Agent");
                        data.putString("AgentEmail",agentEmail);
                    }

                    CustDetailFragment fragment = new CustDetailFragment();

                    fragment.setArguments(data);

                    android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction().add(fragment, "Custhead").addToBackStack("head");

                    if (frag.equals("Owner")) {
                        fragmentTransaction.replace(R.id.mainFrame, fragment);

                    } else {
                        fragmentTransaction.replace(R.id.AgentmainFrame, fragment);

                    }
                    fragmentTransaction.commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

            TextView name,address,account;
            CardView card;


        public ViewHolder(View itemView) {
            super(itemView);

            card=itemView.findViewById(R.id.CustHeadCard);
            name=itemView.findViewById(R.id.CustHeadName);
            address=itemView.findViewById(R.id.CustHeadAddr);
            account=itemView.findViewById(R.id.CustHeadAccNo);
        }
    }

    public void filterList(ArrayList<custModel> filteredList) {
        list=filteredList;
        notifyDataSetChanged();
    }

}
