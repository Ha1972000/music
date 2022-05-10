package com.example.music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText ten,mk;
    private TextView ok;
    private CheckBox check;
//    private ImageView imageView1,imageView2,imageView3;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
sharedPreferences=getSharedPreferences("datalogin",MODE_PRIVATE);
//lấy giá trị share
        ten.setText(sharedPreferences.getString("taikhoan",""));
        mk.setText(sharedPreferences.getString("matkhau",""));
       check.setChecked(sharedPreferences.getBoolean("checked",false));
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=ten.getText().toString().trim();
                String pass=mk.getText().toString().trim();
                if( name.equals("ha")&pass.equals("123")){
                    Toast.makeText(MainActivity.this,"đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    Intent chuyenman = new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(chuyenman);

//NÊú check
                    if(check.isChecked()){
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("taikhoan",name);
                        editor.putString("matkhau",pass);
                        editor.putBoolean("checked",true);
                        editor.commit();
                    }
                    else {
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.remove("taikhoan");
                        editor.remove("matkhau");
                        editor.remove("checked");
                        editor.commit();

                    }
                }else {
                    Toast.makeText(MainActivity.this,"đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                }
            }
        });
//        imageView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String url="https://facebook.com.vn";
//            }
//        });
//        imageView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String url="https://google.com.vn";
//            }
//        });
//        imageView3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String url="https://twiter.com.vn";
//            }
//        });
 }

    private void Anhxa() {

        ten=findViewById(R.id.editTextTextPersonName);
        mk=findViewById(R.id.editTextTextPassword);
        ok=findViewById(R.id.send);
//      imageView1=findViewById(R.id.fb);
//      imageView2=findViewById(R.id.gg);
//       imageView3=findViewById(R.id.twi);
    }
}