package com.goals.avigailfohrman.ripple;

/**
 * Created by avigailfohrman on 5/8/16.
 */
public class Goal {
    private String goalName;
    private int goalTargetAmount;
    private String userName;
    private int amountCompleted;
    private static int numberOfGoalsOutThere = 0;
    private int goalId;
    private int creatorId;

    public Goal(String goalName, int goalTargetAmount, String userName, int creatorId) {
        this.goalName = goalName;
        this.goalTargetAmount = goalTargetAmount;
        this.userName = userName;
        this.amountCompleted = 0;
        this.creatorId = creatorId;
        this.goalId = numberOfGoalsOutThere++;
    }

    public Goal() {
        this.goalName = "Help the homeless";
        this.goalTargetAmount = 500;
        this.userName = "Avigail";
        this.amountCompleted = 0;
        this.creatorId = 0;
        this.goalId = numberOfGoalsOutThere++;
    }

    public int getGoalId() {
        return goalId;
    }

    public static int getNumberOfGoalsOutThere() {
        return numberOfGoalsOutThere;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public int getGoalTargetAmount() {
        return goalTargetAmount;
    }

    public void setGoalTargetAmount(int goalTargetAmount) {
        this.goalTargetAmount = goalTargetAmount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAmountCompleted() {
        return amountCompleted;
    }

    public void setAmountCompleted(int amountCompleted) {
        this.amountCompleted = amountCompleted;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Goal name: ");
        sb.append(goalName);
        sb.append("\nTarget amount: ");
        sb.append(goalTargetAmount);
        sb.append("\nAmount of times it has been completed: ");
        sb.append(amountCompleted);
        return sb.toString();
    }

    public int getCreatorId() {
        return creatorId;
    }
}
