package com.example.dairyproductscompanyapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.dairyproductscompanyapp.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)





        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController
        binding?.bottomNavigation?.setupWithNavController(navController)

        Log.e("TAG", "check: ${Firebase.auth.currentUser?.uid}", )
        if (Firebase.auth.currentUser?.uid == null){
            binding?.bottomNavigation?.menu?.findItem(R.id.addFragment)?.setOnMenuItemClickListener {
                Log.e("TAG", "onCreate: ${Firebase.auth.currentUser?.uid}", )
                Log.e("TAG", "onCreate: here ", )

                false
            }


        }
        navController.addOnDestinationChangedListener{_, destination, _ ->

            if (destination.id == R.id.orderProductCompanyFragment || destination.id == R.id.detailCompanyFragment || destination.id == R.id.mapsFragment || destination.id == R.id.editProductFragment || destination.id == R.id.editProfileFragment){
                binding?.bottomNavigation?.visibility = View.GONE
            }else{
                binding?.bottomNavigation?.visibility = View.VISIBLE
            }

        }
    }



//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp()|| super.onSupportNavigateUp()
//    }

}