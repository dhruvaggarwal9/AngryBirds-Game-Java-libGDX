
# 🎯 Angry Birds Game — Full Game Implementation
### 📚 Course: CSE201 – Advanced Programming

**Project Type:** Group Project
**Phase:** Final Submission
**Team Name:** GameChangers

---

## 👥 Team Members

| Name                 | Student ID |
| -------------------- | ---------- |
| Dhruv Aggarwal       | 2023197    |
| Pranav Prakash Kadam | 2023382    |

> Both members contributed equally to this project.

---

## 🕹️ Project Overview

This project is a complete recreation of the classic **Angry Birds** game using **Java** and the **LibGDX** framework. It includes a rich **graphical user interface**, realistic **physics-based mechanics**, multiple levels, and **serialization features** for saving and resuming gameplay.

The development emphasizes **Object-Oriented Programming**, clean architecture, and extensibility using modular classes and design patterns.

---

## ⚙️ Tech Stack

| Component           | Technology                    |
| ------------------- | ----------------------------- |
| 🎮 Game Engine      | [LibGDX](https://libgdx.com)  |
| 💻 Programming Lang | Java                          |
| 🔧 Build System     | Gradle                        |
| 🧠 IDE Support      | IntelliJ IDEA (Recommended)   |
| 🧪 Testing          | JUnit                         |
| 📁 Assets           | Canva / AI-generated / Online |

---

## 🧱 Game Features

### ✅ Core Gameplay

* 🪀 **Catapult-based bird launching**
* 🧮 **Physics engine** for trajectory, gravity, and collisions
* 💥 **Destructible structures** made of wood, glass, and steel
* 🐷 **Dynamic pig health system**
* 🎯 **Multiple birds** with unique damage profiles
* 🧩 **Victory condition**: Eliminate all pigs with limited birds

### ✅ Game Mechanics

* 🧱 Structural blocks with breakability based on material
* 🐦 Bird types with varying speed and strength
* 🐖 Pig types with different health and behavior
* 🔄 **Serialization** for saving/loading game progress

### ✅ UI Components

* 🏠 Main Menu
* 📜 Level Selection Screen
* 🕹️ Game Screen (with active physics)
* 🏆 Win / ❌ Lose Screens
* ⏸️ Pause Menu
* ⚙️ Settings & Profile Pages
* 🪙 Reward/Score Display

---

## 🧪 Serialization Details

The game can be saved and resumed mid-level. Serialized data includes:

* Remaining birds
* Pigs and structure damage state
* Collapsed elements and game status

---

## 📥 Setup Instructions

### ✅ Prerequisites

* Java JDK 8+
* Gradle
* IntelliJ IDEA *(Optional)*

---

### ▶️ How to Run

#### 🐧 Linux/macOS (Terminal)

```bash
cd Game
chmod +x gradlew
./gradlew lwjgl3:run
```

#### 🪟 Windows (CMD)

```cmd
cd Game
gradlew.bat lwjgl3:run
```

#### 🧠 IntelliJ IDEA Method

1. Open the `Game/` folder as a project
2. Import as a **Gradle Project**
3. Navigate to:
   `Gradle → lwjgl3 → Tasks → application → run`
4. Double-click `run` to launch

---

## 📂 Key Classes & Components

### 🎮 Game Objects

* `Bird`, `Pig`, `Block`, `Building`, `Catapult`, `Season`
* `GameState`, `SaveLoadUtility`

### 🖥️ Screens

* `MainScreen`, `LevelScreen`, `WinScreen`, `LoseScreen`
* `PauseScreen`, `SettingsScreen`, `ProfileScreen`

---

## 🧰 Assets & Resources

* [LibGDX Documentation](https://libgdx.com/dev/)
* [Angry Birds Official](https://www.angrybirds.com/)
* Assets from:

  * Canva
  * Royalty-Free Web Sources
  * AI-generated content

---

## 📎 Links

* [📊 UML Diagram](https://drive.google.com/file/d/1N7mgZVRql_FDOavQDjVUejiojY-9DyMN/view?usp=drive_link)
* [🎥 Demo Video](https://drive.google.com/file/d/1N5H-aROP1yapsBjCCmB8R7QRWXyfBHuc/view?usp=drive_link)

---

## 💡 Notes

* Designed for **desktop platforms** only.
* Fully functional with physics, levels, and saving.
* Permissions required for shell scripts on Unix systems.
* Further enhancements can include sound, analytics, and mobile deployment.

---
