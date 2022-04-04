package com.sportsclub.fizanyatik.Model;

public class MatchList {

    private String team1_img;
    private String team1_name;
    private String team1_score;
    private String team1_over;
    private String team2_img;
    private String team2_name;
    private String team2_score;
    private String team2_over;
    private String match_result;
    private String more_btn;

    public String getMore_btn() {
        return more_btn;
    }

    public void setMore_btn(String more_btn) {
        this.more_btn = more_btn;
    }

    public MatchList(String team1_img, String team1_name, String team1_score, String team1_over, String team2_img, String team2_name, String team2_score, String team2_over, String match_result, String more_btn) {
        this.team1_img = team1_img;
        this.team1_name = team1_name;
        this.team1_score = team1_score;
        this.team1_over = team1_over;
        this.team2_img = team2_img;
        this.team2_name = team2_name;
        this.team2_score = team2_score;
        this.team2_over = team2_over;
        this.match_result = match_result;
        this.more_btn = more_btn;
    }

    public String getTeam1_img() {
        return team1_img;
    }

    public void setTeam1_img(String team1_img) {
        this.team1_img = team1_img;
    }

    public String getTeam1_name() {
        return team1_name;
    }

    public void setTeam1_name(String team1_name) {
        this.team1_name = team1_name;
    }

    public String getTeam1_score() {
        return team1_score;
    }

    public void setTeam1_score(String team1_score) {
        this.team1_score = team1_score;
    }

    public String getTeam1_over() {
        return team1_over;
    }

    public void setTeam1_over(String team1_over) {
        this.team1_over = team1_over;
    }

    public String getTeam2_img() {
        return team2_img;
    }

    public void setTeam2_img(String team2_img) {
        this.team2_img = team2_img;
    }

    public String getTeam2_name() {
        return team2_name;
    }

    public void setTeam2_name(String team2_name) {
        this.team2_name = team2_name;
    }

    public String getTeam2_score() {
        return team2_score;
    }

    public void setTeam2_score(String team2_score) {
        this.team2_score = team2_score;
    }

    public String getTeam2_over() {
        return team2_over;
    }

    public void setTeam2_over(String team2_over) {
        this.team2_over = team2_over;
    }

    public String getMatch_result() {
        return match_result;
    }

    public void setMatch_result(String match_result) {
        this.match_result = match_result;
    }

}
