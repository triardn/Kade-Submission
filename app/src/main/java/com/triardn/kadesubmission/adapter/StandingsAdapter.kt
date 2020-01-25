package com.triardn.kadesubmission.adapter

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.triardn.kadesubmission.R
import com.triardn.kadesubmission.model.Standing
import org.jetbrains.anko.*

class StandingsAdapter(private val standings: List<Standing>) : RecyclerView.Adapter<StandingsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingsViewHolder {
        return StandingsViewHolder(StandingsUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = standings.size

    override fun onBindViewHolder(holder: StandingsViewHolder, position: Int) {
        holder.bindItem(standings[position])
    }
}

class StandingsViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val clubName: TextView = view.find(R.id.club_name)
    private val clubMatch: TextView = view.find(R.id.club_match)
    private val clubMatchWin: TextView = view.find(R.id.club_match_win)
    private val clubMatchLoss: TextView = view.find(R.id.club_match_loss)
    private val clubMatchDraw: TextView = view.find(R.id.club_match_draw)
    private val clubPoint: TextView = view.find(R.id.club_point)
    private val clubGoals: TextView = view.find(R.id.club_goals)
    private val clubConceded: TextView = view.find(R.id.club_conceded)

    fun bindItem(standing: Standing) {
        clubName.text = standing.name
        clubMatch.text = "P: " + standing.played.toString()
        clubMatchWin.text = "W: " + standing.win.toString()
        clubMatchDraw.text = "D: " + standing.draw.toString()
        clubMatchLoss.text = "L: " + standing.loss.toString()
        clubPoint.text = "Pts: " + standing.total.toString()
        clubGoals.text = "G: " + standing.goalsfor.toString()
        clubConceded.text = "GC: " + standing.goalsagainst.toString()
    }
}

class StandingsUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                textView {
                    id = R.id.club_name
                    textSize = 12f
                }.lparams{
                    margin = dip(5)
                }

                textView {
                    id = R.id.club_match
                    textSize = 12f
                }.lparams {
                    margin = dip(5)
                }

                textView {
                    id = R.id.club_match_win
                    textSize = 12f
                }.lparams {
                    margin = dip(5)
                }

                textView {
                    id = R.id.club_match_loss
                    textSize = 12f
                }.lparams {
                    margin = dip(5)
                }

                textView {
                    id = R.id.club_match_draw
                    textSize = 12f
                }.lparams {
                    margin = dip(5)
                }

                textView {
                    id = R.id.club_goals
                    textSize = 12f
                }.lparams {
                    margin = dip(5)
                }

                textView {
                    id = R.id.club_conceded
                    textSize = 12f
                }.lparams {
                    margin = dip(5)
                }

                textView {
                    id = R.id.club_point
                    textSize = 12f
                }.lparams {
                    margin = dip(5)
                }
            }
        }
    }
}