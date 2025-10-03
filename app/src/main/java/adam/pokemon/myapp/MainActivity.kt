package adam.pokemon.myapp

import adam.pokemon.myapp.ui.fragment.FavoriteFragment
import adam.pokemon.myapp.ui.fragment.HomeFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    private lateinit var bottomNavView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        if (savedInstanceState == null) {
            loadFragmentTransaction(HomeFragment())
            bottomNavView.selectedItemId = R.id.nav_home
        }
    }

    fun initViews(){
        bottomNavView = findViewById(R.id.bottomNavigationView)
        navigateFragment()
    }

    fun navigateFragment(){
        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragmentTransaction(HomeFragment())
                    true
                }
                R.id.nav_favorites -> {
                    loadFragmentTransaction(FavoriteFragment())
                    true
                }
                else -> false
            }
        }
    }

    fun loadFragmentTransaction(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.navigation_fragment_container, fragment)
            .commit()
    }

}

