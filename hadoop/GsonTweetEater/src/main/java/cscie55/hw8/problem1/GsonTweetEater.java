package cscie55.hw8.problem1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import static java.lang.String.valueOf;

public class GsonTweetEater {
    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            // TODO: AH's code


//            try {
                Gson gson = new Gson();
                InputStream in = IOUtils.toInputStream(String.valueOf(value));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line ="";
                while((line = reader.readLine()) != null){
                    // convert to generic JsonElement
                    JsonElement je = gson.fromJson(line, JsonElement.class);
                    // test to see what kind of object the Element is
                    if(je == null){
                        continue;
                    }
                    if(je.isJsonObject()){
                        JsonObject jo = (JsonObject) je;
                        JsonElement user = jo.get("user");

                        JsonElement source = jo.get("source");//source provedes android info
                        if(source != null && source.toString().contains("android")) {

                            // examine nested retrieved object, again to determine type
                            if (user.isJsonArray()) {
                                JsonArray arr = user.getAsJsonArray();
                                JsonElement name = arr.get(0);
                                word.set(name.toString());
                            } else if (user.isJsonObject()) {
                                JsonObject ob = user.getAsJsonObject();
                                JsonElement name = ob.get("name");
                                word.set(name.toString());
                            }
                            context.write(word, one);
                        }
                    }
                }

//            } catch(IOException e){
//
//            } catch(InterruptedException e){
//
//            }

        }
    }

    public static class IntSumReducer
            extends Reducer<Text,IntWritable,Text,IntWritable> {
        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException {
            // TODO: AH's code
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }


    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(GsonTweetEater.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
