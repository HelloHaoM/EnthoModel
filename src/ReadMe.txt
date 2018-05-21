1. Commands for Running the Replication Model:
		javac MainSimulator.java
		java MainSimulator
Then a result.csv file will be generated. You can view different parameters we used in our experiments. 

2. Commands for Running the Model with Extension1:
		javac MainSimulator_Extension1.java
		java MainSimulator_Extension1
Then a result_extension1 file will be generated. You can see the changes of a parameter called 'number-of-region' in 5 experiments. A region is a colour in the original model. 

3. Commands for Running the Model with Extension2:
		javac MainSimulator_Extension2.java
		java MainSimulator_Extension2
Then a result_extension2 file will be generated. In this experiment, we run the model 5 times with same parameters to ensure the reliability of our data output. And you can also see the parameter 'advantage', which is a 'resource' to make some blocks(patches) are richer than others, in the output csv file.
