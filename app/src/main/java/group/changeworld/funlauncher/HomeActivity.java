package group.changeworld.funlauncher;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Toast.makeText(HomeActivity.this,"drawer1",Toast.LENGTH_LONG).show();

        Button drawButton=(Button)findViewById(R.id.drawer_button);
        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this,DrawerActivity.class);
                //Toast.makeText(HomeActivity.this,"drawer2",Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });
        //setting wallpaper
        Drawable wallPaper= WallpaperManager.getInstance(this).getDrawable();
        this.getWindow().setBackgroundDrawable(wallPaper);

        // 将activity设置为全屏显示
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                //WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    public void showDrawer(){
        //Intent i=new Intent(this,DrawerActivity.class);
        Intent i=new Intent(HomeActivity.this,DrawerActivity.class);
        Toast.makeText(HomeActivity.this,"drawer2",Toast.LENGTH_LONG).show();
        startActivity(i);
    }
}
