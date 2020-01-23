package com.triardn.kadesubmission.adapter

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.triardn.kadesubmission.DetailMatchActivity
import com.triardn.kadesubmission.R
import com.triardn.kadesubmission.model.Schedule
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.constraintLayout

class ScheduleAdapter (private val schedules: List<Schedule>) : RecyclerView.Adapter<ScheduleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        return ScheduleViewHolder(ScheduleUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = schedules.size

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bindItem(schedules[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailMatchActivity::class.java)
            val bundle = Bundle()
            val parcel = Schedule(schedules[position].idEvent, schedules[position].strEvent, schedules[position].idHomeTeam, schedules[position].idAwayTeam, schedules[position].intHomeScore, schedules[position].intAwayScore, schedules[position].strDate, schedules[position].strTime, schedules[position].strHomeGoalDetails, schedules[position].strAwayGoalDetails, schedules[position].strHomeYellowCards, schedules[position].strAwayYellowCards)

            bundle.putParcelable("match", parcel)
            intent.putExtra("Bundle", bundle)
            holder.itemView.context.startActivity(intent)
        }
    }

}

class ScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val matchTitle: TextView = view.find(R.id.match_title)
    private val homeTeamScore: TextView = view.find(R.id.home_team_score)
    private val awayTeamScore: TextView = view.find(R.id.away_team_score)
    private val matchDate: TextView = view.find(R.id.match_date)
    private val matchTime: TextView = view.find(R.id.match_time)

    fun bindItem(schedule: Schedule) {
        matchTitle.text = schedule.strEvent
        homeTeamScore.text = schedule.intHomeScore
        awayTeamScore.text = schedule.intAwayScore
        matchDate.text = "Date: " + schedule.strDate
        matchTime.text = "Time: " + schedule.strTime
    }
}

class ScheduleUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(8)

                cardView {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)

                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER

                        textView {
                            textSize = 20f
                            id = R.id.match_title
                            text = resources.getString(R.string.no_goal)
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams {
                            marginEnd = dip(8)
                        }

                        constraintLayout {
                            lparams(width = matchParent, height = wrapContent)
                            padding = dip(8)
                            id = R.id.match_item

                            textView {
                                textSize = 20f
                                id = R.id.home_team_score
                                text = resources.getString(R.string.no_goal)
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                topToTop = R.id.match_item
                                endToStart = R.id.versus
                                marginEnd = dip(8)
                            }

                            textView {
                                id = R.id.versus
                                textSize = 14f
                                text = resources.getString(R.string.versus)
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                topToTop = R.id.match_item
                                startToStart = R.id.match_item
                                endToEnd = R.id.match_item
                            }

                            textView {
                                textSize = 20f
                                id = R.id.away_team_score
                                text = resources.getString(R.string.no_goal)
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                topToTop = R.id.match_item
                                startToEnd = R.id.versus
                                marginStart = dip(8)
                            }
                        }

                        tableLayout {
                            tableRow {
                                textView {
                                    id = R.id.match_date
                                    textSize = 14f
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }.lparams {
                                    width = matchParent
                                    height = wrapContent
                                    rightMargin = dip(10)
                                    leftMargin = dip(30)
                                }
                                textView {
                                    id = R.id.match_date
                                    textSize = 14f
                                    gravity = Gravity.CENTER_HORIZONTAL
                                    text = "-"
                                }.lparams {
                                    width = matchParent
                                    height = wrapContent
                                    rightMargin = dip(5)
                                    leftMargin = dip(5)
                                }
                                textView {
                                    id = R.id.match_time
                                    textSize = 12f
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }.lparams {
                                    width = matchParent
                                    height = wrapContent
                                    rightMargin = dip(30)
                                    leftMargin = dip(10)
                                }
                            }
                        }.lparams{
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                    }
                }
            }
        }
    }
}