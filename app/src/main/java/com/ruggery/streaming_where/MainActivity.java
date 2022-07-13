package com.ruggery.streaming_where;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText et_ShowName = (EditText) findViewById(R.id.et_ShowName);
        Button searchShow = (Button) findViewById(R.id.searchShow);

        searchShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                        //api key: 38b4e8be946536c96c2ebffa3cbd988d
                retrieveID();
            }
        });
    }

    public void retrieveID(){
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/search/movie?api_key=38b4e8be946536c96c2ebffa3cbd988d&query=Jack+Reacher")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    ResponseBody responseBody = response.body();
                    Log.d("demo","onResponse: " + responseBody.string());
                }
            }
        });
    }
}