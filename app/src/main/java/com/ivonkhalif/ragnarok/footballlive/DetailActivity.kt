package com.ivonkhalif.ragnarok.footballlive

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ivonkhalif.ragnarok.footballlive.Model.Event
import com.ivonkhalif.ragnarok.footballlive.Model.Team
import com.ivonkhalif.ragnarok.footballlive.Model.TeamList
import com.ivonkhalif.ragnarok.footballlive.rest.ApiInterface
import com.ivonkhalif.ragnarok.footballlive.rest.ApiRepository
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.ctx
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailActivity : AppCompatActivity(), AnkoLogger {

    private lateinit var idHome: String
    private lateinit var idAway: String
    private lateinit var goalHome: String
    private lateinit var goalAway: String
    private lateinit var shotHome: String
    private lateinit var shotAway: String
    private lateinit var gkHome: String
    private lateinit var gkAway: String
    private lateinit var dfHome: String
    private lateinit var dfAway: String
    private lateinit var mfHome: String
    private lateinit var mfAway: String
    private lateinit var fwHome: String
    private lateinit var fwAway: String
    private lateinit var subsHome: String
    private lateinit var subsAway: String
    private lateinit var index: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val bundle = intent.getBundleExtra("myBundle")
        val detail: Event = bundle.getParcelable("selected_match")

        home_detail.text = detail.strHomeTeam
        home_detail.isSelected = true
        away_detail.text= detail.strAwayTeam
        away_detail.isSelected = true
        date_detail.text= detail.dateEvent
        idHome = detail.idHomeTeam.toString()
        idAway = detail.idAwayTeam.toString()

        if (detail.intHomeScore.equals(null)) scoreHome.text = "-"
        else scoreHome.text = detail.intHomeScore
        if (detail.intAwayScore.equals(null)) scoreAway.text= "-"
        else scoreAway.text = detail.intAwayScore
        if (detail.intHomeShots.equals(null)) shot_home.text = "-"
        else shot_home.text = detail.intHomeShots
        if (detail.intAwayShots.equals(null)) shot_away.text = "-"
        else shot_away.text = detail.intAwayShots

        goalHome = detail.strHomeGoalDetails.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strHomeGoalDetails.equals(null)) goal_home.text ="-"
        else goal_home.text = goalHome.trim()

        goalAway = detail.strAwayGoalDetails.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strAwayGoalDetails.equals(null) || detail.strAwayGoalDetails.equals("")) goal_away.text ="-"
        else goal_away.text = goalAway.trim()

        gkHome = detail.strHomeLineupGoalkeeper.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strHomeLineupGoalkeeper.equals(null) || detail.strHomeLineupGoalkeeper.equals("")) gk_home.text = "-"
        else gk_home.text= gkHome.trim()

        gkAway = detail.strAwayLineupGoalkeeper.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strAwayLineupGoalkeeper.equals(null) || detail.strAwayLineupGoalkeeper.equals("")) gk_away.text = "-"
        else gk_away.text= gkAway.trim()

        dfHome = detail.strHomeLineupDefense.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strHomeLineupDefense.equals(null) || detail.strHomeLineupDefense.equals("")) defend_home.text = "-"
        else defend_home.text= dfHome.trim()

        dfAway = detail.strAwayLineupDefense.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strAwayLineupDefense.equals(null) || detail.strAwayLineupDefense.equals("")) defense_away.text = "-"
        else defense_away.text= dfAway.trim()

        mfHome = detail.strHomeLineupMidfield.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strHomeLineupMidfield.equals(null) || detail.strHomeLineupMidfield.equals("")) mid_home.text = "-"
        else mid_home.text= mfHome.trim()

        mfAway = detail.strAwayLineupMidfield.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strAwayLineupMidfield.equals(null) || detail.strAwayLineupMidfield.equals("")) mid_away.text = "-"
        else mid_away.text= mfHome.trim()

        fwHome = detail.strHomeLineupForward.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strHomeLineupForward.equals(null) || detail.strHomeLineupForward.equals("")) fw_home.text = "-"
        else fw_home.text= fwHome.trim()

        fwAway = detail.strAwayLineupForward.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strAwayLineupForward.equals(null) || detail.strAwayLineupForward.equals("")) fw_away.text = "-"
        else fw_away.text= fwAway.trim()

        subsHome = detail.strHomeLineupSubstitutes.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strHomeLineupSubstitutes.equals(null) || detail.strHomeLineupSubstitutes.equals("")) subs_home.text = "-"
        else subs_home.text= subsHome.trim()

        subsAway = detail.strAwayLineupSubstitutes.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strHomeLineupSubstitutes.equals(null) || detail.strAwayLineupSubstitutes.equals("")) subs_away.text = "-"
        else subs_away.text= subsAway.trim()

        loadGambarHome()
        loadGambarAway()
    }

    private fun loadGambarHome() {
        val apiSearch = ApiRepository.getRetrofit().create(ApiInterface::class.java)
        val call = apiSearch.getIdTeamHome(idHome)

        call.enqueue(object : Callback<TeamList>{
            override fun onFailure(call: Call<TeamList>, t: Throwable) {}

            override fun onResponse(call: Call<TeamList>, response: Response<TeamList>) {
                if (response.isSuccessful){
                    val team: List<Team> = response.body()?.teams!!
                    for (item : Team? in team.iterator()){
                        Glide.with(ctx).load(item?.strTeamBadge).into(logo_Home)
                    }
                }
            }

        })
    }

    private fun loadGambarAway() {
        val apiSearch = ApiRepository.getRetrofit().create(ApiInterface::class.java)
        val call = apiSearch.getIdTeamAway(idAway)

        call.enqueue(object : Callback<TeamList>{
            override fun onFailure(call: Call<TeamList>, t: Throwable) {}

            override fun onResponse(call: Call<TeamList>, response: Response<TeamList>) {
                if (response.isSuccessful){
                    val team: List<Team> = response.body()?.teams!!
                    for (item : Team? in team.iterator()){
                        Glide.with(ctx).load(item?.strTeamBadge).into(logo_Away)
                    }
                }
            }

        })
    }

}
