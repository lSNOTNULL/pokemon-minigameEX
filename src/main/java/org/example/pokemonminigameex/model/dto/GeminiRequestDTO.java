package org.example.pokemonminigameex.model.dto;

import java.util.List;

public record GeminiRequestDTO(List<Content> contents) {
    public record Content(String role, List<Part> parts){}
    public record Part(String text){}

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
//                    "text": "Thinking.."
//                },
//                {
//                    "text": "네, 인사하는 귀여운 프레리독 이미지를 생성했습니다.\n\n![인사하는 프레리독 이미지](https://image.pollinations.ai/prompt/A%20cute%20prairie%20dog%20standing%20on%20its%20hind%20legs%2C%20raising%20one%20paw%20in%20a%20friendly%20greeting%20gesture%2C%20set%20against%20a%20sunny%20prairie%20background%2C%20charming%20illustration%20style)\n\n귀여운 프레리독이 두 발로 서서 앞발 하나를 들고 반갑게 인사하는 모습입니다. 햇살 좋은 날, 초원 위에서 호기심 가득한 표정을 짓고 있네요.\n\n이 이미지가 마음에 드시길 바랍니다! 다른 필요한 그림이 있으시면 언제든지 말씀해주세요."
//                },
//        ]
//            }

}
