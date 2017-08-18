import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import models.Member;
import models.Team;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);

        //Create
        //get: show new post form
        get("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new post form
        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Team> teams = teamDao.getAll();
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            Team newTeam = new Team(name, description);
            teamDao.add(newTeam);
            model.put("team", newTeam);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new member form
        get("/teams/:id/add-member", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(req.params("id"));
            Team editTeam = teamDao.findById(teamId);
            model.put("addMember", editTeam);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new member form
        post("/teams/:id/add-member", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int teamId = Integer.parseInt(request.params("id"));
            Team editTeam = teamDao.findById(teamId);
            String first = request.queryParams("first");
            String last = request.queryParams("last");
            Member newMember = new Member(first, last, editTeam.getId());
            memberDao.add(newMember);
            List<Member> members = teamDao.getAllMembersByTeam(editTeam.getId());
            model.put("addMember", editTeam);
            model.put("members", members);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //Read
        //get: show all posts
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "team-details.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual post
        get("/teams/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(req.params("id"));
            Team foundTeam = teamDao.findById(teamId);
            model.put("team", foundTeam);
            return new ModelAndView(model, "team-details.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a post
        get("/teams/:id/update-name", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(req.params("id"));
            Team editTeam = teamDao.findById(teamId);
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a post
        post("/teams/:id/update-name", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int teamId = Integer.parseInt(request.params("id"));
            Team editTeam = teamDao.findById(teamId);
            String name = request.queryParams("name");
            editTeam.setName(name);
            model.put("newName", editTeam);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        //get: delete an individual post

        //get: delete all posts
    }
}
