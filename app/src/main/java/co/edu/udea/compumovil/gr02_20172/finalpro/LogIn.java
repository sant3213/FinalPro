package co.edu.udea.compumovil.gr02_20172.finalpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
    setSupportActionBar(myToolbar);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.update_profile:
        Intent registroIntent = new Intent(getApplicationContext(), Registro.class);
        startActivity(registroIntent);
        return true;
      case R.id.get_out:
        Intent exitIntent = new Intent(Intent.ACTION_MAIN);
        exitIntent.addCategory(Intent.CATEGORY_HOME);
        exitIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(exitIntent);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu, menu);
    return true;
  }
}
