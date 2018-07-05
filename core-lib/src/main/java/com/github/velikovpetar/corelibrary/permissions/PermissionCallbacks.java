package com.github.velikovpetar.corelibrary.permissions;

/**
 * Callbacks used when asking for runtime permissions.
 */
public interface PermissionCallbacks {

  /**
   * Called when a single permission is granted.
   *
   * @param permission the granted permission
   */
  void onSinglePermissionGranted(String permission);

  /**
   * Called after the user grants one or more of the requested permissions.
   *
   * @param permissions the granted permissions
   */
  void onPermissionsGranted(String[] permissions);

  /**
   * Called when a rationale should be shown for a denied permission.
   *
   * @param permission the permission for which a rationale should be shown
   */
  void onShouldShowPermissionRationale(String permission);

  /**
   * Called when a requested permission is denied and the user chose
   * 'Don't ask again'.
   *
   * @param permission the denied permission
   */
  void onPermissionDenied(String permission);
}
