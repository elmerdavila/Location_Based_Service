package unsa.epis.locationbasedservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int REQUEST_CODE_PERMISSION = 2;
    Button btnShowLocation;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    Location_Tracker gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            if(ActivityCompat.checkSelfPermission(this,mPermission)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{mPermission},REQUEST_CODE_PERMISSION);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        btnShowLocation = (Button) findViewById(R.id.btn_localizacion);
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gps=new Location_Tracker(MainActivity.this);
                if(gps.canGetLocation()){
                    double latitude= gps.getLatitude();
                    double longitude=gps.getLongitude();
                    Toast.makeText(getApplicationContext(),"Localizacion:\nLongitud: "+longitude+"\nLatitud: "+latitude,Toast.LENGTH_SHORT).show();
                }
                else {
                    gps.showSettings();
                }
            }
        });
    }
}