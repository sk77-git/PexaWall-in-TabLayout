package com.example.defaulttabs.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.defaulttabs.R;
import com.example.defaulttabs.WallpaperAdapter;
import com.example.defaulttabs.WallpaperModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment4 extends Fragment {

    RecyclerView recyclerView4;
    WallpaperAdapter4 wallpaperAdapter4;
    List<WallpaperModel> wallpaperModelList4;

    int pageNumber=1;
    Boolean isScrolling= false;
    int currentItem,totalItems,scrollOutItems;



    String natureUrl="https://api.pexels.com/v1/search?query=romance&per_page=80";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_4, container, false);
        recyclerView4=view.findViewById(R.id.recyclerView);

        wallpaperModelList4= new ArrayList<>();
        wallpaperAdapter4 = new WallpaperAdapter4(this,wallpaperModelList4);

        recyclerView4.setAdapter(wallpaperAdapter4);

        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),2);
        recyclerView4.setLayoutManager(gridLayoutManager);

        recyclerView4.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling= true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItem = gridLayoutManager.getChildCount();
                totalItems = gridLayoutManager.getItemCount();
                scrollOutItems = gridLayoutManager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItem+scrollOutItems==totalItems)){
                    isScrolling = false;
                    fetchWallpaper();
                }
            }
        });
        fetchWallpaper();


        return view;
    }
    private void fetchWallpaper() {



        StringRequest request= new StringRequest(Request.Method.GET, natureUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //On response
                try {
                    JSONObject jsonObject= new JSONObject(response);

                    JSONArray jsonArray= jsonObject.getJSONArray("photos");

                    int length=jsonArray.length();
                    for(int i= 0;i<length;i++)
                    {
                        JSONObject object= jsonArray.getJSONObject(i);
                        int id=object.getInt("id");
                        JSONObject objectImages = object.getJSONObject("src");
                        String originalUrl= objectImages.getString("original");
                        String mediumUrl= objectImages.getString("medium");

                        WallpaperModel wallpaperModel= new WallpaperModel(id,originalUrl,mediumUrl);
                        wallpaperModelList4.add(wallpaperModel);


                    }

                    wallpaperAdapter4.notifyDataSetChanged();
                    pageNumber++;

                }catch (JSONException e){

                }

            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //On error response

            }}){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Authorization","563492ad6f917000010000013cfdfe86ffb9449fa112c24047cae13d");


                return params;

            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }
}