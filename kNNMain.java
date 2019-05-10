import java.util.List;
import java.io.FileNotFoundException;
import java.util.Arrays;



public class kNNMain{

  public static void main(String... args) throws FileNotFoundException{

    // TASK 1: Use command line arguments to point DataSet.readDataSet method to
    // the desired file. Choose a given DataPoint, and print its features and label
	int n = 20;
	
	double fracTest = 0.2;
	double fracTrain = 0.8;

	
	List<DataPoint> help = DataSet.readDataSet(args[0]);
	
	
	String label = help.get(n).getLabel();
	
	double[] x = help.get(n).getX();
	
	System.out.println(label);
	
	for(int i = 0; i<x.length; i++) {
		
		System.out.println(x[i]);
	
	}
	
	

    //TASK 2:Use the DataSet class to split the fullDataSet into Training and Held Out Test Dataset
	
	List<DataPoint> testSet = DataSet.getTestSet(help, fracTest);

	List<DataPoint> trainingSet = DataSet.getTrainingSet(help, fracTrain);
	
	String trainLabel = trainingSet.get(n).getLabel();
	
	double[] xTraining = help.get(n).getX();
	
	System.out.println(trainLabel);
	
	for(int i = 0; i<xTraining.length; i++) {
		
		System.out.println(xTraining[i]);
	}

    // TASK 3: Use the DataSet class methods to plot the 2D data (binary and multi-class)



    // TASK 4: write a new method in DataSet.java which takes as arguments to DataPoint objects,
    // and returns the Euclidean distance between those two points (as a double)
	
	double distTest = DataSet.distanceEuclid(trainingSet.get(4), trainingSet.get(5));
	
	System.out.println(distTest);

    // TASK 5: Use the KNNClassifier class to determine the k nearest neighbors to a given DataPoint,
    // and make a print a predicted target label
	
	KNNClassifier predicktor = new KNNClassifier(3);
	
	System.out.println(predicktor.predict(trainingSet, trainingSet.get(6)));



    // TASK 6: loop over the datapoints in the held out test set, and make predictions for Each
    // point based on nearest neighbors in training set. Calculate accuracy of model.


  }

}
