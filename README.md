# Insta-Backend
  
  [![Instagram-clone 완성본](http://img.youtube.com/vi/9VqcakSSotU/0.jpg)](https://www.youtube.com/watch?v=9VqcakSSotU) 


2021.11.15 ~ 2021.12.03   
- Frontend: React
- Backend: Springboot & java
- connection: TCP/IP Socket

## 🏁 TEAM
[김민주](https://github.com/MINJU-KIMmm)|[김윤서](https://github.com/ottl-seo)|[이채은](https://github.com/lcheun)|
|:---:|:---:|:---:|
<img src="https://github.com/MINJU-KIMmm.png" width="150" height="150">|<img src="https://github.com/ottl-seo.png" width="150" height="150" >|<img src="https://github.com/lcheun.png" width="150" height="150">|

## 📝 API 명세
### 1. 실시간 피드 업로드
<details>
<summary>[POST] 게시글 업로드</summary>
<div markdown="1">

### Request

| Method | URL | 설명 |
| --- | --- | --- |
| POST | api/posts | 포스트 게시 |

### Request 형식

| Name | type | 설명 |
| --- | --- | --- |
| image | MultipartFile | image 파일 |
| requestDto | String | requestDto json 형태로 |

### request 예시

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2539e444-2219-4e4f-8087-07c6fb1c953c/Untitled.png)

requestDto 예시

```json
{
    "user":{
        "userNo":1,
        "userId":"efub",
        "fileSize":0,
        "originalFileName":"null",
        "filePath":"null"
    },
    "content":"두번게시물테스트!"
}
```

### Response

| postNo | String | 새롭게 게시된 post 번호를 String 형태로 반환 |
| --- | --- | --- |


</div>
</details>

<details>
<summary>[게시글 목록 불러오기</summary>
<div markdown="1">
  
### Request

| Method | URL | 설명 |
| --- | --- | --- |
| Get | /postList | 모든 포스트 불러오기 |

### Response

| PostList | List<PostDto> | 새롭게 게시된 post 번호를 String 형태로 반환 |
| --- | --- | --- |

**response 예시**

```json
[
    {
        "postNo": 1,
        "userInfo": {
            "userNo": 1,
            "userId": "efub",
            "fileSize": 0,
            "originalFileName": "null",
            "filePath": null
        },
        "content": "첫게시물테스트!",
        "fileSize": 39366,
        "originalFileName": "review post api ex.png",
        "filePath": "https://efub-insta-bucket.s3.ap-northeast-2.amazonaws.com/review%20post%20api%20ex.png"
    },
    {
        "postNo": 2,
        "userInfo": {
            "userNo": 1,
            "userId": "efub",
            "fileSize": 0,
            "originalFileName": "null",
            "filePath": null
        },
        "content": "두번게시물테스트!",
        "fileSize": 39366,
        "originalFileName": "review post api ex.png",
        "filePath": "https://efub-insta-bucket.s3.ap-northeast-2.amazonaws.com/review%20post%20api%20ex.png"
    }
]
```
  </div>
</details>
  

### 2. 게시글 좋아요
<details>
<summary>[GET] 좋아요 누른 사용자 목록 불러오기 </summary>
<div markdown="1">

### Request

| Method | URL | 설명 |
| --- | --- | --- |
| Get | /{postNo}/likeList | 해당 포스트 좋아요 한 유저 목록 불러오기 |

### Request 형식

| Name | type | 설명 |
| --- | --- | --- |
| postNo | Long | 게시글 번호 |
|  |  |  |

### request 예시

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/44191a38-13b1-44ed-8895-6562c9d39d9c/Untitled.png)

### Response

| UserLisst | List<UserDto> | 해당 게시글을 좋아요한 User 정보를 List 형식으로 반환 |
| --- | --- | --- |

**response 예시**

```json
[
    {
        "userNo": 1,
        "userId": "efub",
        "fileSize": 0,
        "originalFileName": "null",
        "filePath": null
    },
    {
        "userNo": 2,
        "userId": "efub2",
        "fileSize": 0,
        "originalFileName": "null",
        "filePath": null
    }
]
```
  </div>
</details>

<details>
<summary>[GET] 좋아요 누른 사용자 수 불러오기</summary>
<div markdown="1">
  
### Request

| Method | URL | 설명 |
| --- | --- | --- |
| Get | /{postNo}/likeListCount | 해당 포스트 좋아요 한 유저 수 불러오기 |

### Request 형식

| Name | type | 설명 |
| --- | --- | --- |
| postNo | Long | 게시글 번호 |
|  |  |  |

### request 예시

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1353e750-ef30-44b0-abdf-40454463d8a4/Untitled.png)

### Response

| likeUserCount | Long | 해당 게시글을 좋아요한 유저 수 반환 |
| --- | --- | --- |
  </div>
</details>

<details>
<summary>[PATCH] 게시글 좋아요 취소</summary>
<div markdown="1">
  
### Request

| Method | URL | 설명 |
| --- | --- | --- |
| Patch | /{postNo}/{userNo}/like | 포스트 좋아요/취소 |

### Request 형식

| Name | type | 설명 |
| --- | --- | --- |
| postNo | Long | 게시글 번호 |
| userNo | Long | 유저 번호 |

### request 예시

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/79dc87cd-0111-411d-a1f4-4125572422a8/Untitled.png)

### Response

| ok | String | 작업이 제대로 수행되면 "ok" 문자열 반환 |
| --- | --- | --- |

  </div>
</details>

### 3. 채팅 (Direct Message)
    
<details>
<summary>[POST] 채팅방 생성</summary>
<div markdown="1">

### Request

| Method | URL | 설명 |
| --- | --- | --- |
| POST | http://localhost:8080/chat/room | 채팅방 만들기 |

### Request 형식

| Name | type | 설명 |
| --- | --- | --- |
| name | String | 채팅방 이름 |

### Response

| roomNo | String | 채팅방 고유 ID 생성하여 반환 |
| --- | --- | --- |
| name | String | 입력받은 값으로 채팅방 이름 설정 |

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/aadc5860-0e6e-4b8c-9d47-85e9c5a08b74/Untitled.png)

채팅방이 만들어진 것임. 고유 ID(roomNo)반환. 

이제 이 roomNo로 채팅방에 입장 가능.

  </div>
</details>

<details>
<summary>[MESSAGE] 채팅방 입장 </summary>
<div markdown="1">
### Request

채팅 메소드는 `@MessageMapping` 사용, URL도 [http://가](http://가) 아닌 ws:// 사용함.

| Method | URL | 설명 |
| --- | --- | --- |
| Message | localhost:8080/pub/chat/message | 채팅 메시지 전송 |

### Request 형식

| Name | type | 설명 |
| --- | --- | --- |
| ChatMsgDto | JSON | ChatMsgDto json 형태로 |

Request 예시

```json
{
  "type":"ENTER",
  "roomNo":"5007fc94-ebbe-486c-ae00-7e21f632109b",
  "sender":"yoonseo",
  "content":""
}
```

### Response

[localhost:8080/sub/chat/room/{roomNo}](http://localhost:8081/sub/chat/room/{roomNo}) 로 채팅이 전송됨.

  </div>
</details>

<details>
<summary>[MESSAGE] 메시지 전송</summary>
<div markdown="1">
  
### Request

type이 `TALK` 인 것만 다름.

| Method | URL | 설명 |
| --- | --- | --- |
| Message | localhost:8080/pub/chat/message | 채팅 메시지 전송 |

### Request 형식

| Name | type | 설명 |
| --- | --- | --- |
| ChatMsgDto | JSON | ChatMsgDto json 형태로 |

Request 예시

```json
{
  "type":"TALK",
  "roomNo":"5007fc94-ebbe-486c-ae00-7e21f632109b",
  "sender":"yoonseo",
  "content":"hihi"
}
```

### Response

[localhost:8080/sub/chat/room/{roomNo}](http://localhost:8081/sub/chat/room/{roomNo}) 로 채팅이 전송됨.

  </div>
</details>

<details>
<summary>[GET] 채팅방 목록 불러오기 </summary>
<div markdown="1">
  
### Request

| Method | URL | 설명 |
| --- | --- | --- |
| GET | localhost:8080/chat/rooms |  |
  </div>
</details>

<details>
<summary>[POST] 채팅 메시지 DB 저장</summary>
<div markdown="1">
### Request

| Method | URL | 설명 |
| --- | --- | --- |
| POST | localhost:8080/send | 채팅 메시지 DB에 쌓기 |

### Request 형식

| Name | type | 설명 |
| --- | --- | --- |
| requestDto | JSON | requestDto json 형태로 |

requestDto 예시

```json
{
        "messageNo": 11,
        "chatRoom": {
            "roomNo": "dfds",
            "roomName": "sdfsd",
            "sender": {
                "userNo": 1,
                "userId": "efub",
                "fileSize": 0,
                "originalFileName": "null",
                "filePath": null
            },
            "receiver": {
                "userNo": 2,
                "userId": "kimmnju",
                "fileSize": 0,
                "originalFileName": "null",
                "filePath": "null"
            }
        },
        "nickname": "ottl.seo",
        "flag": true,
        "message": "안녕"
    }
```

### Response

| Name | type | 설명 |
| --- | --- | --- |
| messageNo | int | 메시지 번호 반환 |

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e97c901a-10e0-45d8-8abc-16d30b3cbf17/Untitled.png)
  </div>
</details>


