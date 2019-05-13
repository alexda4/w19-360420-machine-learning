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
	
	int neighbourNumber = 3;
	
	int loopNumber = 1000;

	
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
	
	KNNClassifier predicktor = new KNNClassifier(neighbourNumber);
	
	System.out.println(predicktor.predict(trainingSet, trainingSet.get(6)));



    // TASK 6: loop over the datapoints in the held out test set, and make predictions for Each
    // point based on nearest neighbors in training set. Calculate accuracy of model. 
	String[] predictedLabels = new String[testSet.size()];
	
	double[] accuracies = new double[loopNumber];
	double[] precisions = new double[loopNumber];
	double[] recall = new double[loopNumber];
	
	for(int j = 0; j < accuracies.length; j++) {
		
		help = DataSet.readDataSet(args[0]);
		
		testSet = DataSet.getTestSet(help, fracTest);

		trainingSet = DataSet.getTrainingSet(help, fracTrain);
		
		for(int i = 0; i < testSet.size(); i++) {
		
			predictedLabels[i] = predicktor.predict(trainingSet, testSet.get(i));
		
		}
	
		int gotRight = 0;
		int gotWrong = 0;
		int truePositives = 0;
		int falsePositives = 0;
		int malignants = 0;
	
		for(int i = 0; i < testSet.size(); i++) {
		
			//System.out.println(predictedLabels[i].equals(testSet.get(i).getLabel()));
		
			if(predictedLabels[i].equals(testSet.get(i).getLabel())) {
				gotRight++;
				if(predictedLabels[i].equals("malignant")) {
					truePositives++;
				}
				if(testSet.get(i).getLabel().equals("malignant")) {
					malignants++;
				}
			}
			
			else {
				gotWrong++;
				if(predictedLabels[i].equals("malignant")) {
					falsePositives++;
				}
				if(testSet.get(i).getLabel().equals("malignant")) {
					malignants++;
				}
			}
		}
	
		accuracies[j] = gotRight/(double)(testSet.size());
		precisions[j] = truePositives/(double)(truePositives+falsePositives);
		recall[j] = truePositives/(double)(malignants);
	}
	
	System.out.println("Accuracy mean over " + loopNumber + " loops is: " + (mean(accuracies)*100));
	System.out.println("Accuracy standard dev over " + loopNumber + " loops is: " + (standardDeviation(accuracies)*100));
	System.out.println("Precision mean over " + loopNumber + " loops is: " + (mean(precisions)*100));
	System.out.println("Precision standard dev over " + loopNumber + " loops is: " + (standardDeviation(precisions)*100));
	System.out.println("Recall mean over " + loopNumber + " loops is: " + (mean(recall)*100));
	System.out.println("Recall standard dev over " + loopNumber + " loops is: " + (standardDeviation(recall)*100));
	
	//System.out.println(predictedLabels[3]);
	//System.out.println(testSet.get(3).getLabel());
  }

  public static double mean(double[] arr){
    /*
    Method that takes as an argument an array of doubles
    Returns: average of the elements of array, as a double
    */
    double sum = 0.0;

    for (double a : arr){
      sum += a;
    }
    return (double)sum/arr.length;
  }

  public static double standardDeviation(double[] arr){
    /*
    Method that takes as an argument an array of doubles
    Returns: standard deviation of the elements of array, as a double
    Dependencies: requires the *mean* method written above
    */
    double avg = mean(arr);
    double sum = 0.0;
    for (double a : arr){
      sum += Math.pow(a-avg,2);
    }
    return (double)sum/arr.length;
  }

}
