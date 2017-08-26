package co.edu.udea.compumovil.gr02_20172.finalpro;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import gun0912.tedbottompicker.TedBottomPicker;

public class Registro extends AppCompatActivity {
  Button btn_registrar;
  Button btn_fecha;
  EditText txt_fecha;
  AutoCompleteTextView  autocomplete;
  String[] cities = { "Bogotá", "Medellín","Cali", "Armenia", "Pasto"};
  ImageView imageHolder;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_registro);
    initViewElements();
    setAutocomplete();

    btn_registrar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent log=new Intent(getApplicationContext(), LogIn.class);
        startActivity(log);
      }
    });
  }

  @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.N)
  public void setBrithDate(View v) {
    Calendar callendar = Calendar.getInstance();
    int dia = callendar.get(Calendar.DAY_OF_MONTH);
    int mes = callendar.get(Calendar.MONTH);
    int año = callendar.get(Calendar.YEAR);
    DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
      @Override
      public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        txt_fecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
      }
    }, año, mes, dia);
    datePickerDialog.show();
  }

  private void setAutocomplete() {
    autocomplete = (AutoCompleteTextView) findViewById(R.id.city);
    ArrayAdapter<String> adapter = new ArrayAdapter (this, android.R.layout.select_dialog_item, cities);
    autocomplete.setThreshold(2);
    autocomplete.setAdapter(adapter);
  }

  private void initViewElements() {
    txt_fecha = (EditText) findViewById(R.id.txtfecha);
    btn_fecha = (Button) findViewById(R.id.btnfecha);
    btn_registrar =(Button)findViewById(R.id.btnregistrar);
    imageHolder = (ImageView) findViewById(R.id.profileImageHolder);
  }

  public void setImageUser(View v) {
    PermissionListener permissionlistener = new PermissionListener() {
      @Override
      public void onPermissionGranted() {
//        Toast.makeText(Registro.this, "Permission Granted", Toast.LENGTH_SHORT).show();
        callSetImageUser();
      }

      @Override
      public void onPermissionDenied(ArrayList<String> deniedPermissions) {
        Toast.makeText(Registro.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
      }
    };
    TedPermission.with(this)
        .setPermissionListener(permissionlistener)
        .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        .check();
  }
  private void callSetImageUser() {
    TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(this)
        .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
          @Override
          public void onImageSelected(Uri uri) {
            setImageHolder();
            imageHolder.setImageURI(uri);
          }
        }).create();
    tedBottomPicker.show(getSupportFragmentManager());
  }

  @Override
  protected void onSaveInstanceState(Bundle savedInstance) {
    super.onSaveInstanceState(savedInstance);
    BitmapDrawable drawable = (BitmapDrawable) imageHolder.getDrawable();
    if(drawable != null) {
      Bitmap bitmap = drawable.getBitmap();
      savedInstance.putParcelable("image", bitmap);
    }
  }

  @Override
  public void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    if(savedInstanceState.containsKey("image")) {
      Bitmap bitmap = savedInstanceState.getParcelable("image");
      setImageHolder();
      imageHolder.setImageBitmap(bitmap);
    }
  }

  private void setImageHolder() {
    ViewGroup.LayoutParams params = imageHolder.getLayoutParams();
    params.height = 500;
    imageHolder.setLayoutParams(params);
  }

}
