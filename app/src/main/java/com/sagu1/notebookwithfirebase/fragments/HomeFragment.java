package com.sagu1.notebookwithfirebase.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sagu1.notebookwithfirebase.R;
import com.sagu1.notebookwithfirebase.adapters.PostAdapter;
import com.sagu1.notebookwithfirebase.models.PostModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements PostAdapter.RecyclerViewItemClikcListener {

    RecyclerView recyclerView;
    ArrayList<PostModel> postList ;
    PostAdapter adapter;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = v.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        postList = new ArrayList<>();
        postList.add(new PostModel(R.drawable.foto1,"Trabzon","Karadeniz'in incisi olarak tabir edilen, eşsiz doğasıyla nefes kesen bir şehir Trabzon."));
        postList.add(new PostModel(R.drawable.foto2,"Mardin","Dicle ve Fırat nehirleri arasında yer alan Mardin Güneydoğu Anadolu Bölgesi'nin en çok merak edilen şehirlerinden biridir."));
        postList.add(new PostModel(R.drawable.foto3,"İzmir","ürkiye'nin batısında, Ege Denizi'nin kıyısında yer alan Ege'nin İncisi İzmir, Türkiye'nin 3'üncü büyük kentidir."));
        postList.add(new PostModel(R.drawable.foto4,"İstanbul","Avrupa ve Asya'yı birbirine bağlayan, çok sayıda medeniyetin izlerini taşıyan istanbul"));

        adapter = new PostAdapter(postList,getContext(),this);
        recyclerView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onPostClicked(PostModel postModel) {

        Toast.makeText(getContext(), "tıklanan item = "+postModel.getPostName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onitemDeleted(int position) {
        postList.remove(position);
        adapter.notifyDataSetChanged();
    }
}
