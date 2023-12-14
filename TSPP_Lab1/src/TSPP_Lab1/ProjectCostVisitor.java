package TSPP_Lab1;

public class ProjectCostVisitor implements ProjectVisitor {
    private double totalCost;
    private double hourlyRate;
    private int numberOfTasksVisited;
    private int numberOfDeliverablesVisited;
    private int numberOfDependentTasksVisited;
    private int numberOfProjectsVisited;

    public ProjectCostVisitor() {
        resetCounts();
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getNumberOfTasksVisited() {
        return numberOfTasksVisited;
    }

    public int getNumberOfDeliverablesVisited() {
        return numberOfDeliverablesVisited;
    }

    public int getNumberOfDependentTasksVisited() {
        return numberOfDependentTasksVisited;
    }

    public int getNumberOfProjectsVisited() {
        return numberOfProjectsVisited;
    }

    public void setHourlyRate(double rate) {
        hourlyRate = rate;
    }

    public void resetCounts() {
        totalCost = 0.0;
        numberOfTasksVisited = 0;
        numberOfDeliverablesVisited = 0;
        numberOfDependentTasksVisited = 0;
        numberOfProjectsVisited = 0;
    }

    public void visitDependentTask(DependentTask p) {
        double taskCost = p.getTimeRequired() * hourlyRate;
        taskCost *= p.getDependencyWeightingFactor();
        totalCost += taskCost;
        numberOfDependentTasksVisited++;
    }

    public void visitDeliverable(Deliverable p) {
        totalCost += p.getMaterialsCost() + p.getProductionCost();
        numberOfDeliverablesVisited++;
    }

    public void visitTask(Task p) {
        totalCost += p.getTimeRequired() * hourlyRate;
        numberOfTasksVisited++;
    }

    public void visitProject(Project p) {
        numberOfProjectsVisited++;
    }
}
