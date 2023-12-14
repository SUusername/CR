package TSPP_Lab1;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
public class RunPattern{
    public static void main(String [] arguments){
        System.out.println("Example for the Visitor pattern");
        System.out.println();
        System.out.println("This sample will use a ProjectCostVisitor to calculate");
        System.out.println(" the total amount required to complete a Project.");
        System.out.println();
        
        System.out.println("Deserializing a test Project for Visitor pattern");
        System.out.println();
        if (!(new File("data.ser").exists())){
            DataCreator.serialize("data.ser");
        }
        Project project = (Project)(DataRetriever.deserializeData("data.ser"));
        
        System.out.println("Creating a ProjectCostVisitor, to calculate the total cost of the project.");
        ProjectCostVisitor visitor = new ProjectCostVisitor();
        visitor.setHourlyRate(100);
        
        System.out.println("Moving throuhg the Project, calculating total cost");
        System.out.println(" by passing the Visitor to each of the ProjectItems.");
        visitProjectItems(project, visitor);
        System.out.println("The total cost for the project is: " + visitor.getTotalCost());
        
        System.out.println("Results for Lab:");
        System.out.println("Number of Tasks visited: " +
                visitor.getNumberOfTasksVisited());
        System.out.println("Number of Deliverables visited: " +
                visitor.getNumberOfDeliverablesVisited());
        System.out.println("Number of Dependent Tasks visited: " +
                visitor.getNumberOfDependentTasksVisited());
        System.out.println("Number of Projects visited: " +
                visitor.getNumberOfProjectsVisited());

    }
    
    private static void visitProjectItems(ProjectItem item, ProjectVisitor visitor){
        item.accept(visitor);
        if (item.getProjectItems() != null){
            Iterator subElements = item.getProjectItems().iterator();
            while (subElements.hasNext()){
                visitProjectItems((ProjectItem)subElements.next(), visitor);
            }
        }
    }
    
}