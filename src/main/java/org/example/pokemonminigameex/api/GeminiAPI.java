package org.example.pokemonminigameex.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.pokemonminigameex.model.dto.GeminiImageDTO;
import org.example.pokemonminigameex.model.dto.GeminiRequestDTO;
import org.example.pokemonminigameex.util.DotenvMixin;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.logging.Logger;

@Component
public class GeminiAPI implements DotenvMixin {
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = Logger.getLogger(GeminiAPI.class.getName());

    public GeminiImageDTO generateImage(GeminiRequestDTO dto) throws IOException, InterruptedException {
        //
        String model = dotenv.get("MODEL_ID");
        String contentApi = dotenv.get("GENERATE_CONTENT_API");
        String GeminiapiKey = dotenv.get("GEMINI_API_KEY");
//        {
//            "contents": [
//            {
//                "role": "user",
//                    "parts": [
//                {
//                    "text": "인사하는 프레리독 이미지를 그려줘"
//                },
//        ]
//            },
//            {
//                "role": "model",
//                    "parts": [
//                {
//                    "text": "Thinking...\**Prepare the Korean Response:**\n    *   **Acknowledge the request:** \"네, 인사하는 프레리독 이미지를 생성했습니다.\" (Yes, I have generated an image of a greeting prairie dog.)\n    *   **Present the image:** (Insert the generated image here).\n    *   **Describe the image:** Explain what's depicted, reinforcing the \"greeting\" aspect.\n        *   \"귀여운 프레리독이 두 발로 서서 앞발 하나를 들고 반갑게 인사하는 모습입니다.\" (It shows a cute prairie dog standing on its hind legs, raising one front paw, and greeting cheerfully.)\n        *   \"맑은 날, 풀밭 위에서 햇살을 받으며 호기심 가득한 눈으로 바라보고 있네요.\" (It's standing on the grass on a clear day, bathed in sunlight, looking with curious eyes.)\n    *   **Offer further assistance:** \"이 이미지가 마음에 드시길 바랍니다! 다른 필요한 그림이 있으시면 언제든지 말씀해주세요.\" (I hope you like this image! If you need any other pictures, please let me know anytime.)\n\n9.  **Final Review:** Check the Korean text for natural phrasing and politeness. Ensure the description accurately matches the generated image."
//                },
//                {
//                    "text": "네, 인사하는 귀여운 프레리독 이미지를 생성했습니다.\n\n![인사하는 프레리독 이미지](https://image.pollinations.ai/prompt/A%20cute%20prairie%20dog%20standing%20on%20its%20hind%20legs%2C%20raising%20one%20paw%20in%20a%20friendly%20greeting%20gesture%2C%20set%20against%20a%20sunny%20prairie%20background%2C%20charming%20illustration%20style)\n\n귀여운 프레리독이 두 발로 서서 앞발 하나를 들고 반갑게 인사하는 모습입니다. 햇살 좋은 날, 초원 위에서 호기심 가득한 표정을 짓고 있네요.\n\n이 이미지가 마음에 드시길 바랍니다! 다른 필요한 그림이 있으시면 언제든지 말씀해주세요."
//                },
//        ]
//            }

        String url = "https://generativelanguage.googleapis.com/v1beta/models/%s:%s?key=%s".formatted(model, contentApi, GeminiapiKey);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(dto)))
                .build();
        // 요청
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        // 응답
        logger.info(response.body());

        return new GeminiImageDTO();
    }
    public GeminiRequestDTO ImageRequest(String username) {
        String prompt = "%s 와 어울리는 포켓몬을 임의로 하나 선정해서 웃는모습을 그려줘".formatted(username);
        List<GeminiRequestDTO.Content> contents = List.of(new GeminiRequestDTO.Content("user",
                List.of(new GeminiRequestDTO.Part(prompt))));
        GeminiRequestDTO geminiRequestDTO = new GeminiRequestDTO(contents);
        return new GeminiRequestDTO(contents); // DTO 레코드 자체를 받아야하므로 contents만 보낼 수 없다.
    }

}
