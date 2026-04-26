import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PeriodLogDAO {

    private String getEncouragement(String mood) {
        String[] happy = {
                "You're radiating good energy today! Keep shining 🌸",
                "Love this for you! Happiness looks good on you 💛",
                "Your smile is your superpower today 🌟",
                "Good days like this are worth celebrating 🎉",
                "Look at you being genuinely happy… we love this for you 💛",
                "This happiness suits you. Try not to overthink it this time 😌",
                "You’re doing amazing… yes, even if you’re surprised by it ✨",
                "Enjoy this moment. You actually deserve it, you know 🌈",
                "You’re glowing today. Don’t question it, just vibe 🌞",
                "Not you thriving like this… proud of you 💫",
                "This energy? Keep it. It’s working wonders 🌱",
                "You’ve come far. Even if you pretend it was easy 🏆",
                "You’re allowed to feel this good. No plot twist needed 💖",
                "Stay in this moment. No unnecessary overthinking allowed 🚫",
                "This version of you is powerful… and slightly suspicious 😏",
                "You’re doing great. Don’t suddenly doubt everything now 😊",
                "Happiness looks good on you. Keep wearing it ✨",
                "You earned this peace… even if you forgot how 💙",
                "Keep going like this. Future you will be impressed 🚀"

        };
        String[] sad = {
                "It's okay to not be okay. Be gentle with yourself 💜",
                "This too shall pass. You are stronger than you know 🌙",
                "Rest, cry if you need to, and know you are loved 💙",
                "Hard days don't last forever. You've survived every bad day so far 🌿",
                "Do it. Or don’t. But don’t complain later 😏",
                "You’ve wasted enough time. Let’s pretend we’re productive now.",
                "Start ugly. It’s still better than not starting.",
                "Future you is already judging you. Act accordingly.",
                "At least try… for the plot.",
                "Bad day? Not a bad life. Relax.",
                "Even this phase will end. Annoyingly slow, but it will.",
                "I know today feels heavy… but you’re still here, and that matters 💙",
                "It’s okay to feel like this. You don’t have to fix everything right now 😌",
                "Yeah, it sucks. No fancy words can change that instantly… but it won’t stay forever 🌧️",
                "Take your time. Healing isn’t exactly known for being fast, unfortunately 🐢",
                "You’ve handled tough days before. Not fun, but you did it 💪",
                "You don’t have to be strong all the time… even superheroes take breaks 🛑",
                "Feel it fully, but don’t let it convince you this is all there is 💭",
                "You’re allowed to slow down. The world can wait a bit 🌿",
                "Not your best day… but definitely not your final chapter 📖",
                "You’re doing better than you think, even if it doesn’t feel like it right now 💫",
                "Bad days happen. Annoyingly often, but they pass 🌙",
                "You don’t need to have answers today. Just getting through is enough 🤍",
                "Even now, you’re trying… and that counts more than you realize 🌱",
                "This feeling will fade. Slowly, yes… but it will 🌅",
                "Be gentle with yourself today. You’ve earned that much 💛"
        };
        String[] anxious = {
                "Take a deep breath. In through the nose, out through the mouth 💛",
                "You've handled hard things before. You can handle this too 🌸",
                "One step at a time. You don't have to figure it all out today 🍃",
                "Your feelings are valid. Be kind to yourself right now 💜",
                "Relax, your brain is just speedrunning worst-case scenarios again 🏃",
                "Take a breath. Not everything is a disaster… just your thoughts right now.",
                "You’ve panicked before and survived. Shocking, I know.",
                "Calm down. Your anxiety is being a bit dramatic today 🎭",
                "Nothing is chasing you. Except your own overthinking",
                "Hey, breathe. Not everything your mind is saying is true right now 💙",
                "You’re safe. Your thoughts are just being a little dramatic today 😌",
                "Slow down. You don’t have to solve everything in one moment 🌿",
                "It feels overwhelming, but you’ve handled things like this before 💪",
                "Take it one step at a time. Your brain doesn’t need to sprint 🐢",
                "You’re okay. Even if your thoughts are trying to convince you otherwise 💭",
                "Let’s not jump to worst-case scenarios… your mind does that enough already 😏",
                "Focus on what’s real, not what-ifs 🌱",
                "Your anxiety is loud, but that doesn’t make it right 🔇",
                "Pause. Breathe. You’re doing better than it feels right now 🤍",
                "You don’t need all the answers immediately. Relax, this isn’t a speed test ⏳",
                "Feelings aren’t facts… even if they’re being very convincing today 🎭",
                "You’re in control, even if it doesn’t feel like it yet 🌼",
                "Let’s take this one thought at a time… not the entire future 😅",
                "You’ve survived 100% of your anxious moments so far. Annoying, but impressive ✨"

        };
        String[] irritable = {
                "Totally valid. Your body is working hard and that's exhausting 💙",
                "Rest, hydrate, and give yourself grace today 🌿",
                "It's okay to set boundaries when you're not feeling your best 🌸",
                "You don't have to be okay all the time. Rest is productive too 💜",
                "Yes, everything is annoying. Especially your mood right now 😌",
                "Drink water before you start a villain arc.",
                "Maybe it’s not everyone else… just a thought.",
                "Take a break before you say something you’ll screenshot and regret.",
                "Congratulations, you’re one inconvenience away from chaos.",
                "Hey, it’s okay. Not everything needs your full reaction right now 💙",
                "Take a breath. The situation isn’t as intense as it feels 😌",
                "You’re allowed to feel annoyed… just don’t let it run the whole show 🎭",
                "Pause for a second. Responding calmly might actually help here 🌿",
                "Not everything deserves your energy today. Save it for better things ✨",
                "You’ve handled worse without exploding… you’ve got this 💪",
                "Take a step back. Even legends need cooldown time ⏳",
                "Maybe it’s not worth the stress… just a thought 😏",
                "You’re in control here, not the irritation 🔇",
                "Let’s not turn this into a full episode. It’s just a moment 📺",
                "You can choose calm… even if it’s slightly annoying to do so 😅",
                "Give yourself a second before reacting. Future you will appreciate it 🤍",
                "This feeling will pass. No need to build a whole story around it 🌱",
                "You don’t have to win every situation. Peace is a pretty good option too 🕊️",
                "Stay grounded. Not everything deserves a dramatic response 💛"
        };
        String[] calm = {
                "This peaceful energy suits you so well 🌿",
                "Calm is a superpower. You're doing amazing 🍃",
                "Love this grounded version of you 💛",
                "Keep this energy — you're exactly where you need to be 🌸",
                "Wow, peace? In this economy? Impressive.",
                "Look at you being emotionally stable for once ✨",
                "Don’t get used to it, but enjoy this calm while it lasts.",
                "This version of you is suspiciously put-together.",
                "Stay like this. It confuses people.",
                "This calm suits you. Let’s keep it going, shall we? 🌿",
                "You’re at peace right now… try not to overthink your way out of it 😌",
                "Everything feels steady. Not bad for someone who overthinks sometimes 💙",
                "Stay here for a while. No need to rush back into chaos ⏳",
                "You’ve found a good rhythm. Don’t question it too much 🌊",
                "This is what balance looks like. Kinda nice, right? ✨",
                "Enjoy this moment. It’s not asking anything from you 🌤️",
                "You’re doing well… even if your brain is suspicious about it 😏",
                "Let this calm recharge you. You’ll need it later 🔋",
                "Nothing urgent, nothing overwhelming. Just peace. Rare, but real 🌈",
                "Stay grounded. This is your reset point 🌱",
                "You don’t need to do more right now. This is enough 🤍",
                "Look at you being all composed… growth is showing 💫",
                "Keep this energy. It’s quietly powerful 🕊️",
                "Calm mind, steady heart. Try not to disturb it yourself 😅"
        };
        String[] tired = {
                "Rest is not laziness. You deserve to recharge 💤",
                "Listen to your body — it's asking for a break 🌙",
                "Even on tired days, you're still doing your best 💜",
                "Sleep, rest, recover. Tomorrow is a new day 🌿",
                "Your body is working hard, give it the care it deserves 💙",
                "It's okay to slow down and take it easy today 🌸",
                "You are not lazy, you are just tired. Rest is important 💛",
                "Your energy may be low, but your worth is not. Take care of yourself 💜",
                "Rest is a form of self-love. You deserve it 💖",
                "Even on your tired days, you're still a rockstar 🌟",
                "You’re tired, and that’s okay. Even machines need a recharge 🔋",
                "Rest a bit. You’re not meant to run on empty all the time 😌",
                "You’ve done enough for now. Your energy deserves a break 💙",
                "Sleep isn’t optional, no matter how much you try to negotiate with it 😏",
                "Take it slow. Productivity can wait, your well-being can’t 🌿",
                "You’re not lazy, just low on energy. Big difference ✨",
                "Give yourself permission to pause. You’ve earned it 🛑",
                "Even your thoughts look tired… maybe it’s time to rest 😅",
                "Recharge now so you don’t crash later ⏳",
                "You don’t have to push through everything. Rest is part of progress 🌱",
                "Your body’s asking for a break… might be wise to listen 🤍",
                "Slow down. You’re not in a race right now 🐢",
                "Being tired and still trying? That’s strength 💪",
                "Take care of yourself first. Everything else can wait 💛",
                "Rest properly. Half-resting and scrolling doesn’t count 😄"



        };
        String[] general = {
                "You are doing better than you think 💪",
                "Every day you show up is a win 🌟",
                "Your strength is quiet but real 💜",
                "Be proud of yourself today 🌸",
                "You are enough, just as you are 💛",
                "Keep going, you're doing great 🌿",
                "Your resilience is inspiring 💙",
                "Small steps forward are still progress 🌱",
                "You are worthy of love and care, especially from yourself 💜",
                "Remember, self-care isn't selfish. You deserve kindness too 💖",
                "You’re doing better than you think. Try not to forget that 💙",
                "Take things one step at a time. No need to rush everything 😌",
                "You’ve handled tough moments before… this isn’t your first level 🎮",
                "It’s okay to not have everything figured out yet 🌿",
                "Keep going. Progress doesn’t always make noise ✨",
                "You’re allowed to grow at your own pace ⏳",
                "Not every day has to be perfect. Just showing up counts 💛",
                "You’ve come a long way… even if you don’t give yourself credit 😏",
                "Small steps still move you forward 🌱",
                "Be patient with yourself. You’re learning as you go 🤍",
                "You don’t need to have all the answers right now 💭",
                "Just keep trying. That’s already more than enough 💪",
                "You’re stronger than your doubts… even if they’re loud sometimes 🔇",
                "Take a breath. You’ve got this, one way or another 🌈",
                "You’re not stuck. You’re just in progress 🚀"
        };

        Random random = new Random();
        switch (mood.toUpperCase()) {
            case "happy":     return happy[random.nextInt(happy.length)];
            case "sad":       return sad[random.nextInt(sad.length)];
            case "anxious":   return anxious[random.nextInt(anxious.length)];
            case "irritable": return irritable[random.nextInt(irritable.length)];
            case "calm":      return calm[random.nextInt(calm.length)];
            case "tired":     return tired[random.nextInt(tired.length)];
            default:          return general[random.nextInt(general.length)];
        }
    }

    private String getSleepTaunt(float hours) {
        if (hours < 4)
            return "Running on 3 hours? Bold strategy. Try sleeping before you start hallucinating 😴";

        else if (hours < 5)
            return "Your brain is buffering… maybe give it actual rest tonight 💙";

        else if (hours < 6)
            return "Almost there. Your pillow still believes in you 🛏️";

        else if (hours < 7)
            return "Decent… but you and your sleep schedule need to have a serious talk 😏";

        else if (hours <= 8)
            return "Nice! This is what functioning human energy looks like ✨";

        else if (hours <= 9)
            return "Look at you getting proper rest. Growth is happening 🌱";

        else
            return "Okay sleeping beauty, balance is key too 😄 but hey, well rested!";
    }

    private String getWaterTaunt(int glasses) {
        if (glasses == 0)
            return "Zero water? Your body is filing a complaint right now 💀";

        else if (glasses <= 2)
            return "That’s… concerning. Maybe drink water before becoming a raisin 😌";

        else if (glasses <= 4)
            return "Getting there. Your cells are cautiously optimistic 💧";

        else if (glasses <= 6)
            return "Nice! Your body appreciates the effort 💙";

        else if (glasses <= 8)
            return "Hydration level: solid. Keep this up 👏";

        else if (glasses <= 10)
            return "Okay overachiever, we get it. You love water 😏";

        else if (glasses <= 12)
            return "Are you training to be a fish or just very committed? 🐟";

        else
            return "Relax, hydration hero. Too much of a good thing exists too 😅";
    }

    private String getStressTaunt(int level) {
        String[] stress_extra = {
                "You're handling a lot right now... maybe don't try to solve your entire life today 💙",
                "Take a breath. Your brain is doing a bit too much multitasking 😑",
                "This is stress, not a final boss fight. You can pause ⏸",
                "You've survived worse days. Annoying, but true 💪",
                "Your thoughts are loud, not necessarily right 📵",
                "Okay, things feel intense... but let's not spiral for free 😏",
                "One thing at a time. You're not a productivity machine 🌿",
                "Stress level rising... hydration and breathing recommended immediately 💧",
                "You're doing your best, even if your brain disagrees 🌫",
                "Pause. Reset. Continue. That's the plan now 🔄",
                "You don't need to have everything figured out in this exact moment ⏳",
                "This is temporary. Even if it feels like forever right now 🌙",
                "You're allowed to slow down before you burn out 🔥",
                "Okay, dramatic thoughts detected... let's tone it down a bit 🥲",
                "You're not behind. You're just overwhelmed. Big difference 💙",
                "Take 5 minutes. The world will survive without you fixing everything 😄",
                "Your cortisol is working overtime... maybe give it a break 🧘",
                "Even now, you're holding it together. That counts 🖤",
                "You don't need to win today. Just getting through is enough 🌱",
                "Stress is loud, but you're still in control 💪"
        };

        Random random = new Random();
        String extra = stress_extra[random.nextInt(stress_extra.length)];

        if (level <= 2) return "Peaceful and thriving. Teach the rest of us your ways 🧘 | " + extra;
        else if (level <= 4) return "Chill vibes. You're handling life pretty well 😎 | " + extra;
        else if (level == 5) return "Balanced... for now. Let's keep it that way 🌿 | " + extra;
        else if (level <= 7) return "Alright, breathe. You're not solving everything today 😑 | " + extra;
        else if (level == 8) return "You're one notification away from chaos. Maybe step back 💙 | " + extra;
        else if (level == 9) return "Stress is winning a bit... time to pause and reset ⏳ | " + extra;
        else return "10/10 stress? Okay, stop everything and take care of yourself first 🖤 | " + extra;
    }

    public void insertLog(int userId, String logDate, String mood, String energyLevel, String symptoms, String notes, int waterIntake, float sleepHours, int stressLevel) {
        String encouragement = getEncouragement(mood);
        String sql = "INSERT INTO mood_logs (user_id, log_date, mood, energy_level, symptoms, notes, encouragement, water_intake, sleep_hours, stress_level) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, logDate);
            stmt.setString(3, mood);
            stmt.setString(4, energyLevel);
            stmt.setString(5, symptoms);
            stmt.setString(6, notes);
            stmt.setString(7, encouragement);
            stmt.setInt(8, waterIntake);
            stmt.setFloat(9, sleepHours);
            stmt.setInt(10, stressLevel);
            stmt.executeUpdate();
            System.out.println("Log saved! Encouragement: " + encouragement);
            System.out.println("Sleep taunt: " + getSleepTaunt(sleepHours));
            System.out.println("Water taunt: " + getWaterTaunt(waterIntake));
            System.out.println("Stress taunt: " + getStressTaunt(stressLevel));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PeriodLog> getAllLogs(int userId) {
        List<PeriodLog> logs = new ArrayList<>();
        String sql = "SELECT * FROM mood_logs WHERE user_id = ? ORDER BY log_date DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                logs.add(new PeriodLog(
                        rs.getInt("id"),
                        rs.getString("log_date"),
                        rs.getString("mood"),
                        rs.getString("energy_level"),
                        rs.getString("symptoms"),
                        rs.getString("notes"),
                        rs.getString("encouragement"),
                        rs.getInt("water_intake"),
                        rs.getFloat("sleep_hours"),
                        rs.getInt("stress_level")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }
}