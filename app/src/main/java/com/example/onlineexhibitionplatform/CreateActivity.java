package com.example.onlineexhibitionplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.onlineexhibitionplatform.databinding.ActivityCreateBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
시 작성할 데이터 입력하기
툴바 기능
    1. 화면 닫기
    2. 저장하기
*/
public class CreateActivity extends AppCompatActivity {

    private ActivityCreateBinding binding;

    private ResponseReadOneData data;
    private ResponseReadOneData.Data dataInfo;

    // editText에 내용이 작성됐는지 확인하는 변수
    Boolean title_boolean = false;
    Boolean author_boolean = false;
    Boolean content_boolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 바인딩
        binding = ActivityCreateBinding.inflate(getLayoutInflater()); // 1
//        setContentView(binding.getRoot()); // 2
//        setContentView(R.layout.activity_create);

        // Toolbar 붙이기
        setSupportActionBar(binding.toolbar);

        //Toolbar의 왼쪽에 버튼을 추가하고 버튼의 아이콘을 바꾼다.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24); // X 아이콘

        //해당 액티비티의 툴바에 있는 타이틀을 공백으로 처리
        getSupportActionBar().setTitle("");


        // 제목 editText에 내용 작성됐는지 확인
        binding.titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    title_boolean = true;
                } else {
                    title_boolean = false;
                }
            }
        });

        // 글쓴이 editText에 내용 작성됐는지 확인
        binding.authorEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    author_boolean = true;
                } else {
                    author_boolean = false;
                }
            }
        });

        // 내용 editText에 내용 작성됐는지 확인
        binding.contentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    content_boolean = true;
                } else {
                    content_boolean = false;
                }
            }
        });

        // 저장 버튼 클릭시 이벤트 발생
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Toolbar Button Clicked!", Toast.LENGTH_SHORT).show();
                // 모든 editText에 내용이 작성됐을 경우
                if(title_boolean && author_boolean && content_boolean){
                    /* 저장하기 Logic*/
//                    Toast.makeText(getApplicationContext(), "저장하였습니다.", Toast.LENGTH_SHORT).show();


                    // 서버에 데이터 전송
                    initNetwork();


                }
                else{
                    if (!title_boolean) {
                        Toast.makeText(getApplicationContext(), "제목을 작성하여주세요.", Toast.LENGTH_SHORT).show();
                    }
                    else if (!author_boolean) {
                        Toast.makeText(getApplicationContext(), "글쓴이를 작성하여주세요.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "내용을 작성하여주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        setContentView(binding.getRoot()); // 2
    }// onCreate() 끝

    // X 아이콘 클릭시
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // close this activity as oppose to navigating up

        return false;
    }

    private void initNetwork() {
        Log.d("CreatActivity", "[binding.titleEditText.toString()]"+binding.titleEditText.toString());
        RequestCreateData requestCreateData = new RequestCreateData(
                binding.titleEditText.getText().toString(), binding.authorEditText.getText().toString(), binding.contentEditText.getText().toString()
        );

        Call<ResponseReadOneData> call = ServiceCreator.getApiService().createPost(requestCreateData);

        call.enqueue(new Callback<ResponseReadOneData>() {
            @Override
            public void onResponse(Call<ResponseReadOneData> call, Response<ResponseReadOneData> response) {
                // 서버로 부터 응답 성공
                if(response.isSuccessful() && response.body() != null) {
                    ResponseReadOneData data = response.body();
                    dataInfo = data.databody;

                    Log.d("CreateActivity", "[data]" + data.toString());
                    Log.d("CreateActivity", "[dataInfo]" + dataInfo.toString());
                    Log.d("CreateActivity", "[dataInfo.title]" + dataInfo.title);
                    Toast.makeText(getApplicationContext(), dataInfo.title + " 저장 성공", Toast.LENGTH_SHORT).show();

                    // 버튼 클릭시 열리는 화면(ReadOneActivity.class)
                    Intent intent = new Intent(CreateActivity.this, ReadOneActivity.class);


                    intent.putExtra("contact_title", dataInfo.title);
                    intent.putExtra("contact_author", dataInfo.author);
                    intent.putExtra("contact_content", dataInfo.content);

                    // 다른 액티비티에 데이터 전달하기
                    // 데이터를 받는 쪽에서는 intent.getStringExtra("contact_title")처럼 변수명으로 데이터를 가져옴
//                    intent.putExtra("contact_title", binding.titleEditText.getText().toString());
//                    intent.putExtra("contact_author", binding.authorEditText.getText().toString());
//                    intent.putExtra("contact_content", binding.contentEditText.getText().toString());

                    // ReadOneActivity.class 시작
                    startActivity(intent);

                    // CreateAcitivity 종료
                    // ReadOneActivity에서 백스탭하면 MainActivity로 이동
                    finish();

                }
                else {
                    Toast.makeText(getApplicationContext(), "저장에 실패하였습니다", Toast.LENGTH_SHORT).show();
                    Log.d("NetworkTest", response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseReadOneData> call, Throwable t) {
                Log.d("NetworkTest", "error: "+ t);
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), ""+t, Toast.LENGTH_SHORT).show();

            }
        });

    }

}