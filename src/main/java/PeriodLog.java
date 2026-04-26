public class PeriodLog {
    private int id;
    private String logDate;
    private String mood;
    private String energyLevel;
    private String symptoms;
    private String notes;
    private String encouragement;
    private int waterIntake;
    private float sleepHours;
    private int stressLevel;

    public PeriodLog(int id, String logDate, String mood, String energyLevel, String symptoms, String notes, String encouragement, int waterIntake, float sleepHours, int stressLevel) {
        this.id = id;
        this.logDate = logDate;
        this.mood = mood;
        this.energyLevel = energyLevel;
        this.symptoms = symptoms;
        this.notes = notes;
        this.encouragement = encouragement;
        this.waterIntake = waterIntake;
        this.sleepHours = sleepHours;
        this.stressLevel = stressLevel;
    }

    public int getId() { return id; }
    public String getLogDate() { return logDate; }
    public String getMood() { return mood; }
    public String getEnergyLevel() { return energyLevel; }
    public String getSymptoms() { return symptoms; }
    public String getNotes() { return notes; }
    public String getEncouragement() { return encouragement; }
    public int getWaterIntake() { return waterIntake; }
    public float getSleepHours() { return sleepHours; }
    public int getStressLevel() { return stressLevel; }
}