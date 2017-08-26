package co.edu.udea.compumovil.gr02_20172.finalpro;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

  private final int SPLASH_DISPLAY_LENGTH = 4000;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(Splash.this, LogIn.class);
        Splash.this.startActivity(intent);
      }
    }, SPLASH_DISPLAY_LENGTH);
  }
}
