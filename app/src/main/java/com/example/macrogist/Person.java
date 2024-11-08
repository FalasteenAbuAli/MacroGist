package com.example.macrogist;

public class Person {

    private int age;
    private float height;
    private float weight;
    private String gender;
    private String activityLevel;
    private boolean wantsToLoseWeight;
    private boolean wantsToGainMuscle;

    public Person(int age, float height, float weight, String gender, String activityLevel,
                  boolean wantsToLoseWeight, boolean wantsToGainMuscle) {
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.activityLevel = activityLevel;
        this.wantsToLoseWeight = wantsToLoseWeight;
        this.wantsToGainMuscle = wantsToGainMuscle;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public boolean wantsToLoseWeight() {
        return wantsToLoseWeight;
    }

    public void setWantsToLoseWeight(boolean wantsToLoseWeight) {
        this.wantsToLoseWeight = wantsToLoseWeight;
    }

    public boolean wantsToGainMuscle() {
        return wantsToGainMuscle;
    }

    public void setWantsToGainMuscle(boolean wantsToGainMuscle) {
        this.wantsToGainMuscle = wantsToGainMuscle;
    }

    public String calculateMacros() {
        double bmr = 0;
        if (gender.equals("Male")) {
            bmr = 10 * weight + 6.25 * height - 5 * age + 5;
        } else if (gender.equals("Female")) {
            bmr = 10 * weight + 6.25 * height - 5 * age - 161;
        }

        double activityMultiplier = 1.2;
        if (activityLevel.equals("Active")) {
            activityMultiplier = 1.55;
        } else if (activityLevel.equals("Very Active")) {
            activityMultiplier = 1.9;
        }

        double dailyCalories = bmr * activityMultiplier;

        if (wantsToLoseWeight) {
            dailyCalories -= 500;
        } else if (wantsToGainMuscle) {
            dailyCalories += 500;
        }

        double proteinCalories = dailyCalories * 0.15; // 15% of calories from protein
        double carbCalories = dailyCalories * 0.55; // 55% of calories from carbs
        double fatCalories = dailyCalories * 0.30; // 30% of calories from fat

        // Convert calories to grams (Protein and Carbs = 4 calories per gram, Fat = 9 calories per gram)
        double proteinGrams = proteinCalories / 4;
        double carbGrams = carbCalories / 4;
        double fatGrams = fatCalories / 9;

        // Return the result as a formatted string
        return String.format("Calories: %.0f\nProtein: %.0f g\nCarbs: %.0f g\nFat: %.0f g",
                dailyCalories, proteinGrams, carbGrams, fatGrams);
    }
}
