import models.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //get: show new post form
        get("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());
        //post: process new post form
        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Team> teams = Team.getAllTeams();
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            String leader = request.queryParams("leader");
            Team newTeam = new Team(name, description);
            newTeam.addMember(leader);
            model.put("team", newTeam);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        //get: show all posts
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Team> teams = Team.getAllTeams();
            model.put("teams", teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Team> teams = Team.getAllTeams();
            model.put("teams", teams);
            return new ModelAndView(model, "team-details.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual post
        get("/teams/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(req.params("id"));
            Team foundTeam = Team.findTeamById(teamId);
            model.put("team", foundTeam);
            return new ModelAndView(model, "team-details.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a post
        get("/teams/:id/update-name", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(req.params("id"));
            Team editTeam = Team.findTeamById(teamId);
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:id/add-member", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(req.params("id"));
            Team editTeam = Team.findTeamById(teamId);
            model.put("addMember", editTeam);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a post
        post("/teams/:id/update-name", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int teamId = Integer.parseInt(request.params("id"));
            Team editTeam = Team.findTeamById(teamId);
            String name = request.queryParams("name");
            editTeam.setName(name);
            model.put("newName", editTeam);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/:id/add-member", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int teamId = Integer.parseInt(request.params("id"));
            Team editTeam = Team.findTeamById(teamId);
            String member = request.queryParams("member");
            editTeam.addMember(member);
            model.put("addMember", editTeam);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        //get: delete an individual post

        //get: delete all posts
    }
}
