package com.example.onlineexhibitionplatform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.onlineexhibitionplatform.databinding.ActivityMainBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerAdapter poemAdapter;
    private ActivityMainBinding binding;
    private ResponseReadList dataList;
    private ArrayList<ResponseReadList.Data> dataInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 바인딩
        binding = ActivityMainBinding.inflate(getLayoutInflater()); // 1



        setContentView(binding.getRoot()); // 2
//        setContentView(R.layout.activity_main);

        initAdapter();

        //플로팅 버튼
        binding.fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CreateActivity.class); //그룹 만들기 화면으로 연결
                startActivity(intent); //액티비티 열기
            }
        });

    }

    private void initAdapter() {
        // recycler view


//        ArrayList<UserData> list = new ArrayList<>();
//        list.add(new UserData("그저께","새벽"));
//        list.add(new UserData("어제","아침"));
//        list.add(new UserData("오늘","점심"));
//        list.add(new UserData("내일","저녁"));
//        list.add(new UserData("모레","밤"));

        // retrofit2
        dataInfo = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        binding.rvPoem.setLayoutManager(layoutManager);

        Call<ResponseReadList> call = ServiceCreator.getApiService().getPosts();

        call.enqueue(new Callback<ResponseReadList>() {
            @Override
            public void onResponse(Call<ResponseReadList> call, Response<ResponseReadList> response) {
                Toast.makeText(getApplicationContext(), "MainActivity", Toast.LENGTH_SHORT).show();
                // 서버로 부터 응답 성공
                if(response.isSuccessful() && response.body() != null) {
                    Toast.makeText(getApplicationContext(), "Response Success", Toast.LENGTH_SHORT).show();

                    dataList = response.body();
                    Log.d("MainActivity", "[dataList]" + dataList.toString());
                    Log.d("MainActivity", "[dataList.massage]" + dataList.message);
                    Log.d("MainActivity", "[dataList.databody]" + dataList.databody);

                    // Adapter
                    dataInfo = dataList.databody;
                    Log.d("MainActivity", "dataInfo : " + dataInfo.toString());
                    poemAdapter = new RecyclerAdapter(dataInfo);

                    binding.rvPoem.setAdapter(poemAdapter);

                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.rvPoem.getContext(),
                            new LinearLayoutManager(MainActivity.this).getOrientation());
                    binding.rvPoem.addItemDecoration(dividerItemDecoration);

                    poemAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<ResponseReadList> call, Throwable t) {
                Log.d("MainActivity", t.toString());
            }
        });


//        // Adapter
//        poemAdapter = new PoemAdapter(list);
//
//        binding.rvPoem.setAdapter(poemAdapter);
//
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.rvPoem.getContext(),
//                new LinearLayoutManager(this).getOrientation());
//        binding.rvPoem.addItemDecoration(dividerItemDecoration);
//
//        poemAdapter.notifyDataSetChanged();



    }






}