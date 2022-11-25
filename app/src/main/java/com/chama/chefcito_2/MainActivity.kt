package com.chama.chefcito_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

//imports de horizontalscroll view


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private var _binding: inside_recipe_fragment? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navController)
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)

        navController.addOnDestinationChangedListener { _, _, arguments ->
            navView.isVisible = arguments?.getBoolean("ShowNavView", false) == true
        }

        //ejecuta el metodo para iniciar el listManager
        listManager(findViewById(R.id.horizontalScroll1), ArrayList())
    }

    private fun listManager(myListView: HorizontalScrollView,
                            myList: ArrayList<String>) {

        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
            requireActivity(),
            //this@MainActivity,
            android.R.layout.simple_list_item_1,
            myList as List<String?>
        )

        myListView.adapter = adapter

        /*
        addButton.setOnClickListener {
            val item = myEditText.text.toString()
            if (item.isNotEmpty()) {
                myEditText.setText("")
                myList.add(item)
                adapter.notifyDataSetChanged()
            }
        }*/

        /*
        myListView.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, index, _ ->
            myList.removeAt(index)
            Toast.makeText(applicationContext, "$typeList removed", Toast.LENGTH_SHORT).show()
            adapter.notifyDataSetChanged()
            true
        }*/
    }
}

