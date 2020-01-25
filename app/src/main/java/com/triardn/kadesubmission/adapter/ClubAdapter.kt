package com.triardn.kadesubmission.adapter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.triardn.kadesubmission.DetailTeamActivity
import com.triardn.kadesubmission.R
import com.triardn.kadesubmission.model.Standing
import com.triardn.kadesubmission.model.Team
import org.jetbrains.anko.*
import org.w3c.dom.Text

class ClubAdapter (private val clubLists: List<Team>) : RecyclerView.Adapter<ClubListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubListViewHolder {
        return ClubListViewHolder(ClubListUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = clubLists.size

    override fun onBindViewHolder(holder: ClubListViewHolder, position: Int) {
        holder.bindItem(clubLists[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailTeamActivity::class.java)
            val bundle = Bundle()
            val parcel = Team(clubLists[position].idTeam,
                clubLists[position].strTeam,
                clubLists[position].strStadium,
                clubLists[position].strStadiumThumb,
                clubLists[position].strStadiumDescription,
                clubLists[position].strStadiumLocation,
                clubLists[position].intStadiumCapacity,
                clubLists[position].strDescriptionEN,
                clubLists[position].strTeamBadge,
                clubLists[position].strTeamJersey)

            bundle.putParcelable("team", parcel)
            intent.putExtra("Bundle", bundle)
            holder.itemView.context.startActivity(intent)
        }
    }
}

class ClubListUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.club_badge
                }.lparams{
                    height = dip(20)
                    width = dip(20)
                }

                textView {
                    id = R.id.club_name
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }
            }
        }
    }
}

class ClubListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val clubName: TextView = view.find(R.id.club_name)
    private val clubBadge: ImageView = view.find(R.id.club_badge)

    fun bindItem(club: Team) {
        Picasso.get().load(club.strTeamBadge).into(clubBadge)
        clubName.text = club.strTeam
    }
}