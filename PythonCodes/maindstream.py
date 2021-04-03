# Create a local SparkContext and Streaming Contexts
from pyspark import SparkContext
from pyspark.streaming import StreamingContext
# Create sc with two working threads
sc = SparkContext("local[2]", "NetworkWordCount")
# Create local StreamingContextwith batch interval of 1 second
ssc = StreamingContext(sc, 1)
# Create DStream that connects to localhost:9999
lines = ssc.socketTextStream("localhost", 9999)
 # Split lines into words
words = lines.flatMap(lambda line: line.split(" "))
# Count each word in each batch
pairs = words.map(lambda word: (word, 1))
wordCounts = pairs.reduceByKey(lambda x, y: x + y)
# Print the first ten elements of each RDD in this DStream
wordCounts.pprint()
# Start the computation
ssc.start()
# Wait for the computation to terminate
ssc.awaitTermination()
