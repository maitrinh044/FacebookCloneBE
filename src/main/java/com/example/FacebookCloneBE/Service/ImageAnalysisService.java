package com.example.FacebookCloneBE.Service;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.v3.LocationName;
import com.google.cloud.translate.v3.TranslateTextRequest;
import com.google.cloud.translate.v3.TranslateTextResponse;
import com.google.cloud.translate.v3.TranslationServiceClient;
import com.google.cloud.translate.v3.TranslationServiceSettings;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageAnalysisService {

    // private static final String CREDENTIALS_PATH = "\"C:\\Users\\CAM
    // TRI\\Downloads\\facebookclone-460009-eaad8455be57.json\"";
    private static final String CREDENTIALS_PATH = "C:\\Users\\CAM TRI\\Downloads\\facebookclone-460009-eaad8455be57.json";
    private static final String PROJECT_ID = "facebookclone-460009"; // Thay bằng project ID của bạn

    public static String analyzeImage(String imageUrl) {
        try {
            // Load credentials
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(CREDENTIALS_PATH));

            // Create Vision API client
            ImageAnnotatorClient visionClient = ImageAnnotatorClient.create(
                    ImageAnnotatorSettings.newBuilder()
                            .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                            .build());

            // Read image from URL
            ByteString imgBytes = ByteString.readFrom(new URL(imageUrl).openStream());

            Image img = Image.newBuilder().setContent(imgBytes).build();
            Feature feature = Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build();
            AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                    .addFeatures(feature)
                    .setImage(img)
                    .build();

            List<AnnotateImageRequest> requests = new ArrayList<>();
            requests.add(request);

            // Get label detection results
            List<AnnotateImageResponse> responses = visionClient.batchAnnotateImages(requests).getResponsesList();

            List<String> englishLabels = new ArrayList<>();
            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    return "Lỗi phân tích hình ảnh: " + res.getError().getMessage();
                }

                res.getLabelAnnotationsList().forEach(label -> {
                    englishLabels.add(label.getDescription());
                });
            }

            // Close Vision client
            visionClient.shutdown();

            if (englishLabels.isEmpty()) {
                return "Không tìm thấy nhãn nào trong hình ảnh.";
            }

            // Translate to Vietnamese using new Translation API client
            TranslationServiceSettings translationSettings = TranslationServiceSettings.newBuilder()
                    .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                    .build();

            try (TranslationServiceClient translationClient = TranslationServiceClient.create(translationSettings)) {
                List<String> vietnameseLabels = new ArrayList<>();
                LocationName parent = LocationName.of(PROJECT_ID, "global");

                for (String label : englishLabels) {
                    TranslateTextRequest translateRequest = TranslateTextRequest.newBuilder()
                            .setParent(parent.toString())
                            .setMimeType("text/plain")
                            .setTargetLanguageCode("vi")
                            .addContents(label)
                            .build();

                    TranslateTextResponse translateResponse = translationClient.translateText(translateRequest);
                    String translatedText = translateResponse.getTranslations(0).getTranslatedText();
                    vietnameseLabels.add(translatedText);
                }

                // Ghép lại thành câu mô tả tự nhiên
                String caption = "Hình ảnh này có thể bao gồm: " +
                        String.join(", ", vietnameseLabels) + ".";

                return caption;
            }

        } catch (IOException e) {
            return "Lỗi khi tải tệp xác thực: " + e.getMessage();
        } catch (Exception e) {
            return "Đã xảy ra lỗi: " + e.getMessage();
        }
    }
}
