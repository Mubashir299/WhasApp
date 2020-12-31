package com.example.whasapp.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whasapp.Adapter.CallListAdapter;
import com.example.whasapp.Adapter.ChatListAdapter;
import com.example.whasapp.Model.CallList;
import com.example.whasapp.R;

import java.util.ArrayList;
import java.util.List;


public class CallsFragment extends Fragment {


    public CallsFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate( R.layout.fragment_calls, container, false );
        RecyclerView recyclerView = view.findViewById( R.id.recyclerView );
        recyclerView.setLayoutManager( new LinearLayoutManager( getContext()));
        List<CallList> list = new ArrayList<>();
        list.add( new CallList("001",
                "Rao Touseef",
                "12/30/2020, 9:24pm",
                "https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510__340.jpg",
                "income") );
        list.add( new CallList("001",
                "Rao Touseef",
                "12/30/2020, 9:28pm",
                "https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510__340.jpg",
                "missed") );
        list.add( new CallList(
                "001",
                "Rao Touseef",
                "12/30/2020, 9:32pm",
                "https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510__340.jpg",
                "out") );
        list.add( new CallList(
                "001",
                "Rao Touseef",
                "12/30/2020, 9:40pm",
                "https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510__340.jpg",
                "income") );
        recyclerView.setAdapter( new CallListAdapter( list,getContext()));       /* getCallList();*/
       return view;
    }
}