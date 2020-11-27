package com.squareboat.iconfinder.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Environment
import android.view.Gravity
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.squareboat.iconfinder.constants.ConstantsApp
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * Created by Atif Qamar on 26-11-2020.
 */

class Utils {

    companion object {
        private const val REQUEST_CODE = 2

        fun toast(message: String, context: Context) {
            val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()

        }

        fun askForPermission(activity: Activity) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_CODE
            )
        }

        fun isPermissionGranted(context: Context): Boolean {
            return (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
                    == PackageManager.PERMISSION_GRANTED)
        }


        fun downloadImage(
            url: String?,
            fileFormat: String,
            context: Context
        ) {
            Picasso.get().load(url).into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
                    try {
                        toast("Download Started", context)
                        val mydir = File(
                            Environment.getExternalStorageDirectory().toString() + "/"+ ConstantsApp.FOLDER_NAME
                        )
                        if (!mydir.exists()) {
                            mydir.mkdirs()
                        }

                        val fileUri =
                            mydir.absolutePath + File.separator + System.currentTimeMillis() + "."+fileFormat
                        val outputStream = FileOutputStream(fileUri)
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                        outputStream.flush()
                        outputStream.close()
                        toast("Download Completed", context)
                    } catch (e: IOException) {
                        e.printStackTrace()
                        toast("Failed To Download", context)
                    }

                }

                override fun onBitmapFailed(e: Exception, errorDrawable: Drawable) {
                    toast("Failed To Download", context)
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable) {
                    toast("Download Started", context)
                }
            })
        }
    }
}

