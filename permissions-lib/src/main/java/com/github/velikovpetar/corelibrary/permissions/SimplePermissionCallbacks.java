package com.github.velikovpetar.corelibrary.permissions;

/**
 * Simple implementation of the {@link PermissionCallbacks} interface with stub implementations of
 * each method.
 * Extend this if you do not intend to override every method of {@link PermissionCallbacks}.
 */
public class SimplePermissionCallbacks implements PermissionCallbacks {

  /**
   * {@inheritDoc}
   */
  @Override
  public void onSinglePermissionGranted(String permission) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onPermissionsGranted(String[] permissions) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onShouldShowPermissionRationale(String permission) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onPermissionDenied(String permission) {

  }
}
