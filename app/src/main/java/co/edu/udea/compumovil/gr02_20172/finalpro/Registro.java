package co.edu.udea.compumovil.gr02_20172.finalpro;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

public class Registro extends AppCompatActivity implements View.OnClickListener {
  Button btnregistrar;

  //variables fecha
  Button btnfecha;
  EditText txtfecha;
  private int dia, mes, año;


  //variables galeria
  private static final int SELECTED_PICTURE=1;
  //variables camara
  private final int REQUEST_IMAGE_CAPTURE=1;


  ImageView imageView;
  private Button btntomafoto;
  private Button btngaleria;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_registro);
    //configuración camara

    //  magicalCamera=new MagicalCamera(this,RESIZE_PHOTO_PIXELS_PERCENTAGE);

    imageView =(ImageView)findViewById(R.id.imgfoto);
    btngaleria=(Button)findViewById(R.id.btngaleria);
    btntomafoto=(Button)findViewById(R.id.btntomarfoto);

    btntomafoto.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        foto();
      }
    });
    btngaleria.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent gall=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gall, SELECTED_PICTURE);
      }
    });

    //Configuración botón fecha
    txtfecha=(EditText) findViewById(R.id.txtfecha);
    btnfecha=(Button)findViewById(R.id.btnfecha);
    btnfecha.setOnClickListener(this);

    //configuración botón registrar
    btnregistrar=(Button)findViewById(R.id.btnregistrar);
    btnregistrar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent log=new Intent(getApplicationContext(), LogIn.class);
        startActivity(log);
      }
    });


  }

  private void foto() {

    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
      startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }
  }
  //para tomar fotos
  @Override
  public void onActivityResult(int requestCode, int resultCode,Intent data){
    if(requestCode==REQUEST_IMAGE_CAPTURE&&resultCode==RESULT_OK){
      Bundle extras=data.getExtras();
      Bitmap imageBitmap=(Bitmap)extras.get("data");
      imageView.setImageBitmap(imageBitmap);

    }
    if(requestCode==SELECTED_PICTURE&&resultCode==RESULT_OK){
      Uri path=data.getData();
      imageView.setImageURI(path);

    }
  }
  @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.N)
  @Override
  public void onClick(View v) {
    if (v == btnfecha) {
      final Calendar c = Calendar.getInstance();
      dia = c.get(Calendar.DAY_OF_MONTH);
      mes = c.get(Calendar.MONTH);
      año = c.get(Calendar.YEAR);
      DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
          txtfecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        }
      }, dia, mes, año);
      datePickerDialog.show();


    }

  }
}
