package eu.accesa.hackit.th.sample.gcp;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.videointelligence.v1.*;


public class DetectAnalyzeShots {

    public static void main(String[] args) {
        try {
            argsHelper(args);
        } catch (Exception e) {
            System.out.println("Exception while running:\n" + e.getMessage() + "\n");
            e.printStackTrace(System.out);
        }
    }

    public static void argsHelper(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage:");
            System.out.printf(
                    "\tjava %s \"<command>\" \"<path-to-video>\"\n"
                            + "Commands:\n"
                            + "\tlabels | shots\n"
                            + "Path:\n\tA URI for a Cloud Storage resource (gs://...)\n"
                            + "Examples: ",
                    Detect.class.getCanonicalName());
            return;
        }
        String command = args[0];
        String path = args.length > 1 ? args[1] : "";

        if (command.equals("shots")) {
            analyzeShots(path);
        }
    }


    public static void analyzeShots(String gcsUri) throws Exception {
        // [START video_analyze_shots]
        // Instantiate a com.google.cloud.videointelligence.v1.VideoIntelligenceServiceClient
        try (VideoIntelligenceServiceClient client = VideoIntelligenceServiceClient.create()) {
            // Provide path to file hosted on GCS as "gs://bucket-name/..."
            AnnotateVideoRequest request = AnnotateVideoRequest.newBuilder()
                    .setInputUri(gcsUri)
                    .addFeatures(Feature.SHOT_CHANGE_DETECTION)
                    .build();

            // Create an operation that will contain the response when the operation completes.
            OperationFuture<AnnotateVideoResponse, AnnotateVideoProgress> response =
                    client.annotateVideoAsync(request);

            System.out.println("Waiting for operation to complete...");
            // Print detected shot changes and their location ranges in the analyzed video.
            for (VideoAnnotationResults result : response.get().getAnnotationResultsList()) {
                if (result.getShotAnnotationsCount() > 0) {
                    System.out.println("Shots: ");
                    for (VideoSegment segment : result.getShotAnnotationsList()) {
                        double startTime = segment.getStartTimeOffset().getSeconds()
                                + segment.getStartTimeOffset().getNanos() / 1e9;
                        double endTime = segment.getEndTimeOffset().getSeconds()
                                + segment.getEndTimeOffset().getNanos() / 1e9;
                        System.out.printf("Location: %.3f:%.3f\n", startTime, endTime);
                    }
                } else {
                    System.out.println("No shot changes detected in " + gcsUri);
                }
            }
        }
    }

}