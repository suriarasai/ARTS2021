from pyspark.sql import SparkSession
from pyspark.sql.types import StructType


spark = SparkSession \
    .builder \
    .appName("KConsumer") \
    .getOrCreate()
temperatureSchema = StructType().add("day", "string").add("tempInCelsius", "string")
temperatureSchema.simpleString()
temperature_streaming_df = spark \
    .readStream \
    .option("sep", ",") \
    .schema(temperatureSchema) \
    .csv("E://PycharmProjects//pythonProject//data//tempratureData.csv")
temperature_streaming_df.isStreaming
temperature_streaming_df.printSchema()
query = temperature_streaming_df \
    .writeStream \
    .format("console") \
    .outputMode("append") \
    .start()