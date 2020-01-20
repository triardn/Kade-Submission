package com.triardn.kadesubmission

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triardn.kadesubmission.fragment.FavoriteFragment
import com.triardn.kadesubmission.fragment.LeagueFragment
import com.triardn.kadesubmission.model.Item
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity() {
    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val list = findViewById<RecyclerView>(R.id.league_list)
//        initData()
//
//        list.layoutManager = LinearLayoutManager(this)
//        list.adapter = RecyclerViewAdapter(this, items) {
//            startActivity(intentFor<DetailLeagueActivity>("league" to it))
//        }

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.leagues -> {
                    loadLeaguesFragment(savedInstanceState)
                }
                R.id.favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }

        bottom_navigation.selectedItemId = R.id.leagues
    }

    private fun loadLeaguesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, LeagueFragment(), LeagueFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                .commit()
        }
    }

//    private fun initData(){
//        val id = resources.getIntArray(R.array.league_id)
//        val name = resources.getStringArray(R.array.league_name)
//        val image = resources.obtainTypedArray(R.array.league_image)
//        items.clear()
//        for (i in name.indices) {
//            items.add(
//                Item(
//                    id[i], name[i],
//                    image.getResourceId(i, 0)
//                )
//            )
//        }
//
//        //Recycle the typed array
//        image.recycle()
//    }
}
