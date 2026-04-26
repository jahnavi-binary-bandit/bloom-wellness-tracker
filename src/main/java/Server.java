import static spark.Spark.*;
import com.google.gson.Gson;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        port(8080);
        staticFiles.externalLocation("C:/Users/jahna/IdeaProjects/Java/Period_Tracker/src/main/java");

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type");
        });

        options("/*", (request, response) -> {
            response.status(200);
            return "OK";
        });

        PeriodLogDAO dao = new PeriodLogDAO();
        UserDAO userDAO = new UserDAO();
        PeriodTrackerDAO periodDAO = new PeriodTrackerDAO();
        Gson gson = new Gson();

        post("/signup", (request, response) -> {
            response.type("application/json");
            AuthRequest body = gson.fromJson(request.body(), AuthRequest.class);
            boolean success = userDAO.signup(body.username, body.password);
            if (success) return "{\"status\":\"success\"}";
            else return "{\"status\":\"error\",\"message\":\"Username already exists\"}";
        });

        post("/login", (request, response) -> {
            response.type("application/json");
            AuthRequest body = gson.fromJson(request.body(), AuthRequest.class);
            User user = userDAO.login(body.username, body.password);
            if (user != null) return "{\"status\":\"success\",\"userId\":" + user.getId() + ",\"username\":\"" + user.getUsername() + "\"}";
            else return "{\"status\":\"error\",\"message\":\"Invalid username or password\"}";
        });

        post("/log", (request, response) -> {
            response.type("application/json");
            LogRequest body = gson.fromJson(request.body(), LogRequest.class);
            dao.insertLog(body.userId, body.date, body.mood, body.energy, body.symptoms, body.notes, body.waterIntake, body.sleepHours, body.stressLevel);
            return "{\"status\":\"success\"}";
        });

        get("/logs", (request, response) -> {
            response.type("application/json");
            int userId = Integer.parseInt(request.queryParams("userId"));
            List<PeriodLog> logs = dao.getAllLogs(userId);
            return gson.toJson(logs);
        });

        post("/period", (request, response) -> {
            response.type("application/json");
            PeriodRequest body = gson.fromJson(request.body(), PeriodRequest.class);
            boolean success = periodDAO.logPeriod(body.userId, body.startDate, body.endDate);
            if (success) return "{\"status\":\"success\"}";
            else return "{\"status\":\"error\"}";
        });

        get("/periods", (request, response) -> {
            response.type("application/json");
            int userId = Integer.parseInt(request.queryParams("userId"));
            List<String[]> history = periodDAO.getPeriodHistory(userId);
            return gson.toJson(history);
        });
        DiaryDAO diaryDAO = new DiaryDAO();

// SAVE DIARY ENTRY
        post("/diary", (request, response) -> {
            response.type("application/json");
            DiaryRequest body = gson.fromJson(request.body(), DiaryRequest.class);
            boolean success = diaryDAO.saveEntry(body.userId, body.entryDate, body.title, body.content, body.mood);
            if (success) return "{\"status\":\"success\"}";
            else return "{\"status\":\"error\"}";
        });

// GET DIARY ENTRIES
        get("/diary", (request, response) -> {
            response.type("application/json");
            int userId = Integer.parseInt(request.queryParams("userId"));
            List<String[]> entries = diaryDAO.getEntries(userId);
            return gson.toJson(entries);
        });

// DELETE DIARY ENTRY
        get("/diary/delete", (request, response) -> {
            response.type("application/json");
            int id = Integer.parseInt(request.queryParams("id"));
            int userId = Integer.parseInt(request.queryParams("userId"));
            boolean success = diaryDAO.deleteEntry(id, userId);
            if (success) return "{\"status\":\"success\"}";
            else return "{\"status\":\"error\"}";
        });

        System.out.println("Server running at http://localhost:8080");
    }

    static class AuthRequest { String username, password; }
    static class LogRequest { int userId; String date, mood, energy, symptoms, notes; int waterIntake; float sleepHours; int stressLevel; }
    static class PeriodRequest { int userId; String startDate, endDate; }
    static class DiaryRequest { int userId; String entryDate, title, content, mood; }
}