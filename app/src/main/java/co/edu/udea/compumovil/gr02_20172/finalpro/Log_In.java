package co.edu.udea.compumovil.gr02_20172.finalpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Log_In extends AppCompatActivity {
    TextView btnregistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);
        btnregistro=(TextView)findViewById(R.id.btnregistro);
        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg=new Intent(getApplication(), registro.class);
                startActivity(reg);
            }
        });

    }
}
