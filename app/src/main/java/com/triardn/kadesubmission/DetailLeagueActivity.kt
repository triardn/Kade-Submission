package com.triardn.kadesubmission

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import org.jetbrains.anko.*

class DetailLeagueActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val league = intent.getParcelableExtra<Item>("league")

        val actionbar = supportActionBar
        actionbar!!.title = league.name
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        DetailLeagueActivityUI(league).setContentView(this)
    }

    class DetailLeagueActivityUI(var item: Item) : AnkoComponent<DetailLeagueActivity> {
        override fun createView(ui: AnkoContext<DetailLeagueActivity>) = with(ui) {
            scrollView {
                verticalLayout(){

                    imageView(item.image).
                        lparams(height = 500, width= 500) {
                            padding = dip(20)
                            margin = dip(15)
                            gravity = Gravity.CENTER_HORIZONTAL
                        }

                    textView{
                        text = item.name
                        textSize = sp(10).toFloat()
                        gravity = Gravity.CENTER_HORIZONTAL
                        padding = dip(10)
                    }

                    textView{
                        text = item.desc
                        padding = dip(10)
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}