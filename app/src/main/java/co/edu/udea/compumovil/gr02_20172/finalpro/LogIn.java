package co.edu.udea.compumovil.gr02_20172.finalpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class LogIn extends AppCompatActivity {
  TextView btn_registro;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_log_in);
    btn_registro = (TextView) findViewById(R.id.btn_registro);
    btn_registro.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent reg = new Intent(getApplication(), Registro.class);
        startActivity(reg);
      }
    });

  }
}
