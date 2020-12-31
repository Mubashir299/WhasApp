package com.example.whasapp.menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whasapp.Adapter.ChatListAdapter;
import com.example.whasapp.Model.ChatList;
import com.example.whasapp.R;

import java.util.ArrayList;
import java.util.List;


public class ChatsFragment extends Fragment {


    // TODO: Rename and change types of parameters


    public ChatsFragment() {
        // Required empty public constructor
    }

    private List<ChatList> list = new ArrayList<>();
    private RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_chats, container, false );
         List<ChatList> list = new ArrayList<>();
        recyclerView = view.findViewById( R.id.recyclerView );
        recyclerView.setLayoutManager( new LinearLayoutManager( getContext()));
        list.add (new ChatList( "11",
                "Usman",
                "WhatsApp",
                "12/29/2020",
                "https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510__340.jpg"));
        list.add (new ChatList( "22",
                "Usman",
                "WhatsApp",
                "12/29/2020",
                "https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510__340.jpg"));
        list.add (new ChatList( "33",
                "Usman",
                "WhatsApp",
                "12/29/2020",
                "https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510__340.jpg"));
        list.add (new ChatList( "44",
                "Usman",
                "WhatsApp",
                "12/29/2020",
                "https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510__340.jpg"));

        recyclerView.setAdapter( new ChatListAdapter( list,getContext()));

        return view;
    }


}