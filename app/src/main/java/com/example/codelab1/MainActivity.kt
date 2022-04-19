package com.example.codelab1

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.codelab1.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var mReciever: CustomReciever = CustomReciever()
    private val ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        this.registerReceiver(mReciever, intentFilter)

        LocalBroadcastManager.getInstance(this).registerReceiver(mReciever,
            IntentFilter(ACTION_CUSTOM_BROADCAST))
    }

    override fun onDestroy() {
        super.onDestroy()

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReciever)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun sendCustomBroadcast(view: View){
        val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)
        customBroadcastIntent.putExtra("randNumber", Random.nextInt(0, 20))
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent)
    }

}