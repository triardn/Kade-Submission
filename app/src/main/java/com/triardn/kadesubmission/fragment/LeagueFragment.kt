package com.triardn.kadesubmission.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triardn.kadesubmission.DetailLeagueActivity
import com.triardn.kadesubmission.R
import com.triardn.kadesubmission.RecyclerViewAdapter
import com.triardn.kadesubmission.model.Item
import org.jetbrains.anko.support.v4.intentFor

class LeagueFragment : Fragment() {
    private var items: MutableList<Item> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_league_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = view.findViewById<RecyclerView>(R.id.league_list)
        initData()

        list.layoutManager = LinearLayoutManager(this.context)
        list.adapter = RecyclerViewAdapter(this.context!!, items) {
            startActivity(intentFor<DetailLeagueActivity>("league" to it))
        }
    }

    private fun initData(){
        val id = resources.getIntArray(R.array.league_id)
        val name = resources.getStringArray(R.array.league_name)
        val image = resources.obtainTypedArray(R.array.league_image)
        items.clear()
        for (i in name.indices) {
            items.add(
                Item(
                    id[i], name[i],
                    image.getResourceId(i, 0)
                )
            )
        }

        //Recycle the typed array
        image.recycle()
    }
}