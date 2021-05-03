package com.nishidhpatel.mvvm.activities

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.nishidhpatel.mvvm.ApplicationClass
import com.nishidhpatel.mvvm.Fragment.HomeFragment
import com.nishidhpatel.mvvm.LocationService.LocationService
import com.nishidhpatel.mvvm.R
import com.nishidhpatel.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ApplicationClass.setmInstance(application as ApplicationClass)
        ApplicationClass.mInstance!!.setActity(this)
        OpenFragment()



        //StartLocationService
//        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            buildAlertMessageNoGps()
//        }
//
//        if(Utils.checkPermissionForLocation(this)){
//            startLocation()
//        }

    }
    fun OpenFragment() {
        val mainfragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.framelayout, mainfragment)
        transaction.commit()


    }

    private fun buildAlertMessageNoGps() {

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                startActivityForResult(
                    Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    , 11)
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.cancel()
                finish()
            }
        val alert: AlertDialog = builder.create()
        alert.show()


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 15) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocation()
            } else {
                Toast.makeText(this@MainActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun startLocation(){
        val serviceIntent = Intent(this, LocationService::class.java)
        startService(serviceIntent)
    }
}
