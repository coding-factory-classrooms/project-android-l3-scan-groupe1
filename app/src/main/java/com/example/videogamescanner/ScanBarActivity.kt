package com.example.videogamescanner

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.videogamescanner.api.ApiActivity
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView


private const val TAG = "ScanBarActivity"

class ScanBarActivity : AppCompatActivity(), ZBarScannerView.ResultHandler {
    private lateinit var mScannerView: ZBarScannerView
    private val SECOND_ACTIVITY_REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mScannerView = ZBarScannerView(this)
        setContentView(mScannerView)

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 0)
        }
    }

    override fun onResume() {
        super.onResume()
        mScannerView.startCamera()
        mScannerView.setResultHandler(this)
        Log.i(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()
    }

    override fun handleResult(result: Result?) {
        Toast.makeText(this, result?.contents, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ApiActivity::class.java)
        intent.putExtra("gtin", result?.contents)
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
        mScannerView.resumeCameraPreview(this)
    }

    // This method is called when the second activity finishes
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                // Get String data from Intent
                val returnString = data!!.getStringExtra("title")
                Log.i(TAG, "onActivityResult: $returnString")
            }
        }
    }
}