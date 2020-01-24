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
import com.triardn.kadesubmission.model.Team
import com.triardn.kadesubmission.model.TeamFavorite
import org.jetbrains.anko.*

class FavoriteTeamAdapter(private val favorite: List<TeamFavorite>, private val listener: (TeamFavorite) -> Unit):
    RecyclerView.Adapter<FavoriteTeamAdapter.FavoriteTeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamViewHolder {
        return FavoriteTeamViewHolder(FavTeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteTeamViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailTeamActivity::class.java)
            val bundle = Bundle()
            val parcel = Team(
                favorite[position].idTeam,
                favorite[position].strTeam,
                favorite[position].strStadium,
                favorite[position].strStadiumThumb,
                favorite[position].strStadiumDescription,
                favorite[position].strStadiumLocation,
                favorite[position].intStadiumCapacity,
                favorite[position].strDescriptionEN,
                favorite[position].strTeamBadge,
                favorite[position].strTeamJersey
            )

            bundle.putParcelable("team", parcel)
            intent.putExtra("Bundle", bundle)
            holder.itemView.context.startActivity(intent)
        }
    }

    class FavoriteTeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val clubName: TextView = view.find(R.id.club_name)
        private val clubBadge: ImageView = view.find(R.id.club_badge)

        fun bindItem(favorite: TeamFavorite, listener: (TeamFavorite) -> Unit) {
            Picasso.get().load(favorite.strTeamBadge).into(clubBadge)
            clubName.text = favorite.strTeam
            itemView.setOnClickListener { listener(favorite) }
        }
    }

    class FavTeamUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.club_badge
                    }.lparams{
                        height = dip(50)
                        width = dip(50)
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
}