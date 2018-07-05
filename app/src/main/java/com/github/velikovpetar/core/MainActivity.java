package com.github.velikovpetar.core;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.github.velikovpetar.corelibrary.permissions.PermissionCallbacks;
import com.github.velikovpetar.corelibrary.permissions.PermissionService;

public class MainActivity extends AppCompatActivity {

  private PermissionCallbacks permissionCallbacks = new PermissionCallbacks() {
    @Override
    public void onSinglePermissionGranted(String permission) {
      Toast.makeText(MainActivity.this, "Single permission granted " + permission, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsGranted(String[] permissions) {
      Toast.makeText(MainActivity.this, "Permission done granting. [See log.]", Toast.LENGTH_SHORT).show();
      for (String permission : permissions) {
        Log.d("MainActivity", permission);
      }
    }

    @Override
    public void onShouldShowPermissionRationale(String permission) {
      AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
          .setTitle("Rationale")
          .setMessage("Explanation for " + permission)
          .setPositiveButton("Ok", (dialogInterface, i) -> {
            PermissionService.requestPermissions(MainActivity.this, new String[]{permission}, 10001);
          })
          .setNegativeButton("Cancel", (dialog1, which) -> {
            Toast.makeText(MainActivity.this, "Permission " + permission + "denied.", Toast.LENGTH_LONG).show();
          })
          .setCancelable(false)
          .create();
      dialog.show();
    }

    @Override
    public void onPermissionDenied(String permission) {
      Toast.makeText(MainActivity.this,
          "You can enable " + permission + " in the app settings.", Toast.LENGTH_LONG).show();
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    requestPermissions();
  }

  private void requestPermissions() {
    PermissionService.requestPermissions(this, new String[]{
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.ACCESS_FINE_LOCATION
    }, 10001);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    PermissionService.onRequestPermissionsResult(this, requestCode, permissions, grantResults, permissionCallbacks);
  }
}
