# 🌸 Bloom — Women's Wellness Tracker

A full-stack web application for daily mood, cycle, and wellness tracking with AI-driven health insights, built with Java, Spark, MySQL, and vanilla JavaScript.

---

## 🚀 Features

### 📊 Dashboard
- Daily mood logging (Happy, Sad, Anxious, Irritable, Calm, Tired)
- Energy level tracking (High, Medium, Low)
- Symptom tracking (Cramps, Bloating, Headache, Fatigue, Acne)
- Water intake tracker with sarcastic taunts 💧
- Sleep hours tracker with funny feedback 😴
- Stress level slider (1–10) with sarcastic + supportive messages 😰
- Personal notes
- Personalized encouragement messages based on mood

### 📈 Analytics
- Mood trend graph (weekly/monthly view)
- Mood breakdown chart
- Symptom pattern analysis
- Mood correlation — does sleep affect your mood?
- Wellness score (0–100)
- Stress trigger analysis — most/least stressful days
- Sleep recommendation based on your data
- Weekly report card (A/B/C grade)

### 🔮 Tracker Tab
- Period start & end date logging
- Next period prediction
- Ovulation window prediction (fertile window)
- PMS alert — warns 5 days before predicted period
- Period history

### 📝 Diary Tab
- Personal diary entries (private, not shared)
- Title, mood tag, and content
- Search through past entries
- Delete entries

### 🔥 Gamification
- Streak counter — consecutive days logged
- 10 badges (First Log, Week Warrior, Hydration Queen, Zen Master, etc.)

### 👤 User System
- Signup & Login
- Each user sees only their own data
- Diary is fully private

### 🌙 UI/UX
- Dark mode toggle
- Motivational quote of the day
- Responsive design
- Sarcastic & warm feedback system

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Backend | Java, Spark Framework |
| Database | MySQL, JDBC |
| Frontend | HTML, CSS, JavaScript |
| Build | Maven |
| Version Control | Git, GitHub |

---

## 📁 Project Structure

```
Period_Tracker/
├── src/main/java/
│   ├── Server.java           # Spark server & API endpoints
│   ├── DatabaseConnection.java
│   ├── PeriodLog.java        # Model
│   ├── PeriodLogDAO.java     # Mood log database operations
│   ├── User.java             # Model
│   ├── UserDAO.java          # User authentication
│   ├── PeriodTrackerDAO.java # Period tracking operations
│   ├── DiaryDAO.java         # Diary operations
│   └── index.html            # Frontend
├── pom.xml
└── README.md
```

---

## 🗄️ Database Schema

```sql
-- Users table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Mood logs table
CREATE TABLE mood_logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT DEFAULT 1,
    log_date DATE,
    mood VARCHAR(30),
    energy_level VARCHAR(20),
    symptoms VARCHAR(100),
    notes TEXT,
    encouragement TEXT,
    water_intake INT DEFAULT 0,
    sleep_hours FLOAT DEFAULT 0,
    stress_level INT DEFAULT 0
);

-- Period tracker table
CREATE TABLE period_tracker (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    period_start DATE NOT NULL,
    period_end DATE NOT NULL,
    cycle_length INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Diary table
CREATE TABLE diary (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    entry_date DATE NOT NULL,
    title VARCHAR(100),
    content TEXT NOT NULL,
    mood VARCHAR(30),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## ⚙️ Setup & Installation

### Prerequisites
- Java JDK 17+
- MySQL 8.0+
- Maven
- IntelliJ IDEA (recommended)

### Steps

**1. Clone the repository**
```bash
git clone https://github.com/jahnavi-binary-bandit/bloom-wellness-tracker.git
cd bloom-wellness-tracker
```

**2. Set up MySQL database**
```sql
CREATE DATABASE period_tracker;
USE period_tracker;
-- Run the schema SQL above
```

**3. Update database credentials**

Open `DatabaseConnection.java` and update:
```java
private static final String URL = "jdbc:mysql://localhost:3306/period_tracker";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

**4. Update static files path**

Open `Server.java` and update the path:
```java
staticFiles.externalLocation("YOUR_PATH/src/main/java");
```

**5. Run the project**
- Open in IntelliJ
- Run `Server.java`
- Open browser and go to `http://localhost:8080/index.html`

---

## 🌐 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/signup` | Create new account |
| POST | `/login` | Login |
| POST | `/log` | Save mood log |
| GET | `/logs?userId=` | Get all logs for user |
| POST | `/period` | Save period dates |
| GET | `/periods?userId=` | Get period history |
| POST | `/diary` | Save diary entry |
| GET | `/diary?userId=` | Get diary entries |
| GET | `/diary/delete?id=&userId=` | Delete diary entry |

---

## 📄 Conference Paper

This project was developed as part of an internship research project titled:

**"Bloom: A Multi-Dimensional Women's Wellness Tracking System with Mood Analytics, Cycle Prediction and Personalised Health Recommendations"**

### Research Contributions
- Rule-based cycle and ovulation prediction algorithm
- Mood correlation analysis (sleep vs mood)
- Context-aware health recommendation engine
- Gamification for wellness habit formation
- Privacy-controlled personal diary system

---

## 👩‍💻 Developer

**Jahnavi U C**
- GitHub: [@jahnavi-binary-bandit](https://github.com/jahnavi-binary-bandit)

---

## 📸 Screenshots

> Add screenshots here after deployment

---

## 🔮 Future Work
- Friend collaboration with privacy controls
- Community mood board
- PDF wellness report export
- Mobile app (Android/iOS)
- Wearable device integration
- Multilingual support

---

*Built with 💜 for women's wellness*
