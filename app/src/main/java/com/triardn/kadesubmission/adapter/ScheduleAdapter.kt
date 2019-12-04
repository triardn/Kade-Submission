package com.triardn.kadesubmission.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.triardn.kadesubmission.R
import com.triardn.kadesubmission.model.Schedule
import org.jetbrains.anko.*

class ScheduleAdapter (private val schedules: List<Schedule>) : RecyclerView.Adapter<ScheduleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class ScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val teamName: TextView = view.find(R.id.team_name)

    fun bindItem(schedules: Schedule) {

    }
}

class ScheduleUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                textView {
                    id = R.id.team_name
                    textSize = 16f
                }.lparams {
                    margin = dip(15)
                }
            }
        }
    }

}