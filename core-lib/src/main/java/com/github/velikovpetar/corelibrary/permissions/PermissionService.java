package com.github.velikovpetar.corelibrary.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for handling runtime permissions.
 */
public final class PermissionService {

  /**
   * Hide public constructor.
   */
  private PermissionService() {

  }

  /**
   * Checks if a permission is granted.
   *
   * @param context    the context from where the method is called
   * @param permission the permission that is checked
   * @return <code>true</code> if the permission is granted, false otherwise.
   */
  public static boolean hasPermission(final Context context,
                                      final String permission) {
    return ContextCompat.checkSelfPermission(context, permission) ==
        PackageManager.PERMISSION_GRANTED;
  }

  /**
   * Request  permissions.
   *
   * @param activity    the activity from where the method is called
   * @param permissions the requested permissions
   * @param requestCode the request code
   */
  public static void requestPermissions(final Activity activity,
                                        final String[] permissions,
                                        final int requestCode) {
    ActivityCompat.requestPermissions(activity, permissions, requestCode);
  }

  /**
   * Method handling the results from a permission request.
   *
   * @param activity            the activity from where the method is called
   * @param requestCode         the request code
   * @param permissions         the requested permissions
   * @param grantResults        the grant results for the requested permissions
   * @param permissionCallbacks the callbacks handling the results
   */
  public static void onRequestPermissionsResult(final Activity activity,
                                                final int requestCode,
                                                final @NonNull String[] permissions,
                                                final @NonNull int[] grantResults,
                                                final @Nullable PermissionCallbacks permissionCallbacks) {
    if (permissionCallbacks == null) {
      return;
    }
    List<String> deniedPermissions = new ArrayList<>();
    List<String> grantedPermissions = new ArrayList<>();
    for (int i = 0; i < permissions.length; ++i) {
      if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
        grantedPermissions.add(permissions[i]);
        permissionCallbacks.onSinglePermissionGranted(permissions[i]);
      } else {
        deniedPermissions.add(permissions[i]);
      }
    }
    if (grantedPermissions.size() > 0) {
      permissionCallbacks.onPermissionsGranted(grantedPermissions.toArray(new String[0]));
    }
    for (String permission : deniedPermissions) {
      if (shouldShowPermissionRationale(activity, permission)) {
        permissionCallbacks.onShouldShowPermissionRationale(permission);
      } else {
        permissionCallbacks.onPermissionDenied(permission);
      }
    }
  }

  private static boolean shouldShowPermissionRationale(final Activity activity,
                                                       final @NonNull String permission) {
    return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
  }
}
