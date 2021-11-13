package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import Adapter.AdapterListPhoto;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.Photos;

public class ListPhotoActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final List<Photos> viewItems = new ArrayList<>();

    @BindView(R.id.lst_photos)
    RecyclerView lstPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_photo);
        ButterKnife.bind(this);

        lstPhotos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        lstPhotos.setHasFixedSize(true);
        lstPhotos.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        AdapterListPhoto adapaterListPhoto = new AdapterListPhoto(this, viewItems);
        lstPhotos.setAdapter(adapaterListPhoto);

        addItemsFromJSON();
    }

    private void addItemsFromJSON(){
        try {
            String jsonDataString = readJSONdataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject itemObj = jsonArray.getJSONObject(i);

                Photos photos = new Photos();
                photos.setAlbumId(itemObj.getInt("albumId"));
                photos.setId(itemObj.getInt("id"));
                photos.setTitle(itemObj.getString("title"));
                photos.setUrl(itemObj.getString("url"));
                photos.setThumbnailUrl(itemObj.getString("thumbnailUrl"));

                viewItems.add(photos);

            }
        }catch (IOException | JSONException e){
            Log.d(TAG, "addItemsFromJSON", e);
        }
    }

    private String readJSONdataFromFile() throws IOException{
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonString;
            inputStream = getResources().openRawResource(R.raw.list_photos);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            while ((jsonString = bufferedReader.readLine()) != null){
                builder.append(jsonString);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                inputStream.close();
            }
        }
        return new String(builder);
    }
}