package com.example.codevault.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class testcase2 {

    private String name;
    private String description;
    private String visibility;
    private String mainBranch = "main";
    private Map<String, List<String>> branches;

    // Constructor
    public testcase2(String name, String description, String visibility) {
        this.name = name;
        this.description = description;
        this.visibility = visibility;
        this.branches = new HashMap<>();
        branches.put(mainBranch, new ArrayList<>());  // You will initialize main branch with no commits
    }

    // The method to create a branch
    public void createBranch(String branchName) {
        if (branches.containsKey(branchName)) {
            throw new IllegalArgumentException("Branch already exists.");
        }
        branches.put(branchName, new ArrayList<>(branches.get(mainBranch)));  // Then you will create a branch from the main branch
    }

    // Make sure to commit the changes to a branch
    public void commitToBranch(String branchName, String commitMessage) {
        if (!branches.containsKey(branchName)) {
            throw new IllegalArgumentException("Branch does not exist.");
        }
        branches.get(branchName).add(commitMessage);  // Also add commit to the branch
    }

    // The you will merge a branch into the main branch
    public void mergeBranch(String branchName) {
        if (!branches.containsKey(branchName)) {
            throw new IllegalArgumentException("Branch does not exist.");
        }
        branches.get(mainBranch).addAll(branches.get(branchName));  // Also merge the branch into the main
    }

    // Then you will retrive the commits of a branch
    public List<String> getCommits(String branchName) {
        return branches.getOrDefault(branchName, new ArrayList<>());
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getVisibility() {
        return visibility;
    }
}
