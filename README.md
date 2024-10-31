# API 명세서

## schedule
|    기능    | Method |             URL             |      request      | response |          상태코드           
|:--------:|:------:|:---------------------------:|:-----------------:|:--------:|:-----------------------:|
|  일정 등록   |  POST  |       /api/schedules        | 요청 body: schedule |  등록 정보   | 201: 정상등록 <br/> 400: 오류 |
|  일정 조회   |  GET   | /api/schedules/{scheduleId} |         -         | 단건 응답 정보 |        200: 정상조회 <br/> 404: 오류
| 일정 목록 조회 |  GET   |  /api/schedules?writerId=3  |  param: writerId  | 다건 응답 정보 |        200: 정상조회 <br/> 400: 오류       
|  일정 수정   |  PUT   | /api/schedules/{scheduleId} | 요청 body: schedule |  수정 정보   |        200: 정상수정 <br/> 400: 오류       
|  일정 삭제   | DELETE | /api/schedules/{scheduleId} |         -         |    -     |        200: 정상삭제 <br/> 400: 오류       

[//]: # (일정 등록)
<details>
    <summary>일정 등록</summary>
    
- 요청 정보
    <table>
      <tr>
        <td ><b>메소드</b></td>
        <td ><b>요청 URL</b></td>
      </tr>
      <tr>
        <td>POST</td>
        <td >/api/schedules</td>
      </tr>
    </table>
- 예시
  - 요청: POST /api/schedules

  ```json
  {     
        "writer_id" : 1,
        "password" : "abc!123",
        "todo" : "나는 오늘 강의를 들을 것이다."
  }
  ```
  - 응답: HTTP/1.1 201 Created

  ```json
  {
        "schedule_id" : 3,
        "writer_id" : 1,
        "todo" : "나는 오늘 강의를 들을 것이다.",
        "created_date" : "2024-10-31",
        "edit_date" : "2024-10-31"
  }
  ```
- 본문 
  - 요청
    <table>
          <tr>
            <td ><b>이름</b></td>
            <td ><b>타입</b></td>
            <td ><b>설명</b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>writer_id</b></td>
            <td ><b>int</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>password</b></td>
            <td ><b>문자열</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>todo</b></td>
            <td ><b>문자열</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
        </table>   
  - 응답
    <table>
          <tr>
            <td ><b>이름</b></td>
            <td ><b>타입</b></td>
            <td ><b>설명</b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>schedule_id</b></td>
            <td ><b>int</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>writer_id</b></td>
            <td ><b>int</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>todo</b></td>
            <td ><b>문자열</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>created_date</b></td>
            <td ><b>문자열->DATETIME</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>edit_date</b></td>
            <td ><b>문자열->DATETIME</b></td>
            <td ><b></b></td>
            <td ><b>옵션</b></td>
          </tr>
    </table>   
</details>

[//]: # (일정 목록 조회)
<details>
    <summary>일정 목록 조회</summary>

- 요청 정보
    <table>
      <tr>
        <td ><b>메소드</b></td>
        <td ><b>요청 URL</b></td>
      </tr>
      <tr>
        <td>GET</td>
        <td >/api/schedules?writerId=XXX</td>
      </tr>
    </table>
- 예시
  - 요청: GET /api/schedules?writerId=1

  - 응답: HTTP/1.1 200 OK

  ```json
  "schedules" : [
    {
        "schedule_id" : 3,
        "writer_id" : 1,
        "todo" : "나는 오늘 강의를 들을 것이다.",
        "created_date" : "2024-10-31",
  },
  {
        "schedule_id" : 2,
        "writer_id" : 1,
        "todo" : "나는 오늘 강의를 들을 것이다.",
        "created_date" : "2024-10-31",
  }
  ]
  ```
- 본문
  - 요청 x
    
  - 응답
    <table>
          <tr>
            <td ><b>이름</b></td>
            <td ><b>타입</b></td>
            <td ><b>설명</b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>schedule_id</b></td>
            <td ><b>int</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>writer_id</b></td>
            <td ><b>int</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>todo</b></td>
            <td ><b>문자열</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>created_date</b></td>
            <td ><b>문자열->DATETIME</b></td>
            <td ><b></b></td>
            <td ><b>옵션</b></td>
          </tr>
    </table> 
</details>

[//]: # (일정 단건 조회)
<details>
    <summary>일정 단건 조회</summary>

- 요청 정보
    <table>
      <tr>
        <td ><b>메소드</b></td>
        <td ><b>요청 URL</b></td>
      </tr>
      <tr>
        <td>GET</td>
        <td >/api/schedules/{scheduleId}</td>
      </tr>
    </table>
- 예시
    - 요청: GET /api/schedules/{scheduleId}

    - 응답: HTTP/1.1 200 OK
  ```json
  {
        "schedule_id" : 2,
        "writer_id" : 1,
        "todo" : "나는 오늘 강의를 들을 것이다.",
        "created_date" : "2024-10-31",
  }
  
  ```
- 본문
  - 요청 x

  - 응답
    <table>
          <tr>
            <td ><b>이름</b></td>
            <td ><b>타입</b></td>
            <td ><b>설명</b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>schedule_id</b></td>
            <td ><b>int</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>writer_id</b></td>
            <td ><b>int</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>todo</b></td>
            <td ><b>문자열</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>created_date</b></td>
            <td ><b>문자열->DATETIME</b></td>
            <td ><b></b></td>
            <td ><b>옵션</b></td>
          </tr>
    </table> 
</details>

[//]: # (일정 수정)
<details>
    <summary>일정 수정</summary>

- 요청 정보
    <table>
      <tr>
        <td ><b>메소드</b></td>
        <td ><b>요청 URL</b></td>
      </tr>
      <tr>
        <td>PUT</td>
        <td>/api/schedules/{scheduleId}</td>
      </tr>
    </table>
  
- 예시
    - 요청: PUT /api/schedules/{scheduleId}
  ```json
  {
        "schedule_id" : 2,
        "password" : "abc!123",
        "todo" : "나는 오늘 쉴 것이다." -> 수정된 내용
  }
  ```
    - 응답: HTTP/1.1 200 OK
  ```json
  {
        "schedule_id" : 2,
        "todo" : "나는 오늘 쉴 것이다.",
        "edit_date" : "2024-11-1"
  }
  ```
- 본문
  - 요청 
    <table>
          <tr>
            <td ><b>이름</b></td>
            <td ><b>타입</b></td>
            <td ><b>설명</b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>schedule_id</b></td>
            <td ><b>int</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>password</b></td>
            <td ><b>문자열</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>todo</b></td>
            <td ><b>문자열</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
    </table> 
  - 응답
    <table>
          <tr>
            <td ><b>이름</b></td>
            <td ><b>타입</b></td>
            <td ><b>설명</b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>schedule_id</b></td>
            <td ><b>int</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>todo</b></td>
            <td ><b>문자열</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>edit_date</b></td>
            <td ><b>문자열->DATETIME</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
    </table> 
</details>

[//]: # (일정 삭제)
<details>
    <summary>일정 삭제</summary>

- 요청 정보
    <table>
      <tr>
        <td ><b>메소드</b></td>
        <td ><b>요청 URL</b></td>
      </tr>
      <tr>
        <td>DELETE</td>
        <td>/api/schedules/{scheduleId}</td>
      </tr>
    </table>

- 예시
    - 요청: DELETE /api/schedules/{scheduleId}
    - 응답: HTTP/1.1 200 OK
</details>

## writer
|   기능   | Method |             URL             |     request     | response |          상태코드           
|:------:|:------:|:---------------------------:|:---------------:|:--------:|:-----------------------:|
| 작성자 등록 |  POST  |        /api/writers         | 요청 body: writer |  작성자 정보  | 201: 정상등록 <br/> 400: 오류 |
| 작성자 조회 |  GET   |   /api/writers/{writerId}   |        -        |  작성자 정보  |        200: 정상조회 <br/> 404: 오류
| 작성자 수정 |  PUT   |   /api/writers/{writerId}   | 요청 body: writer |  수정된 정보  |        200: 정상조회 <br/> 404: 오류
| 작성자 삭제 | DELETE | /api/schedules/{scheduleId} |        -        |    -     |        200: 정상삭제 <br/> 400: 오류       

[//]: # (작성자 등록)
<details>
    <summary>작성자 등록</summary>

- 요청 정보
    <table>
      <tr>
        <td ><b>메소드</b></td>
        <td ><b>요청 URL</b></td>
      </tr>
      <tr>
        <td>POST</td>
        <td >/api/writers</td>
      </tr>
    </table>
- 예시
    - 요청: POST /api/writers

  ```json
  {     
        "email" : "a@b.com",
        "name" : "아무개1"
  }
  ```
    - 응답: HTTP/1.1 201 Created

  ```json
  {
        "writer_id" : 1,
        "email" : "a@b.com",
        "name" : "아무개1",
        "created_date" : "2024-10-31",
        "edit_date" : "2024-10-31"
  }
  ```
- 본문
  - 요청
    <table>
          <tr>
            <td ><b>이름</b></td>
            <td ><b>타입</b></td>
            <td ><b>설명</b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>email</b></td>
            <td ><b>문자열</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>name</b></td>
            <td ><b>문자열</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
        </table>   
  - 응답
    <table>
          <tr>
            <td ><b>이름</b></td>
            <td ><b>타입</b></td>
            <td ><b>설명</b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>writer_id</b></td>
            <td ><b>int</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>email</b></td>
            <td ><b>문자열</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>name</b></td>
            <td ><b>문자열</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>created_date</b></td>
            <td ><b>문자열->DATETIME</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>edit_date</b></td>
            <td ><b>문자열->DATETIME</b></td>
            <td ><b></b></td>
            <td ><b>옵션</b></td>
          </tr>
    </table>  
</details>

[//]: # (작성자 단건 조회)
<details>
    <summary>작성자 단건 조회</summary>

- 요청 정보
    <table>
      <tr>
        <td ><b>메소드</b></td>
        <td ><b>요청 URL</b></td>
      </tr>
      <tr>
        <td>GET</td>
        <td >/api/writers/{writerId}</td>
      </tr>
    </table>
- 예시
    - 요청: GET /api/writers/{writerId}

    - 응답: HTTP/1.1 200 OK
  
  ```json
  {
        "writer_id" : 1,
        "email" : "a@b.com",
        "name" : "아무개1"
  }
  ```
- 본문
  - 요청 x
  - 응답
    <table>
          <tr>
            <td ><b>이름</b></td>
            <td ><b>타입</b></td>
            <td ><b>설명</b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>writer_id</b></td>
            <td ><b>int</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>email</b></td>
            <td ><b>문자열</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>name</b></td>
            <td ><b>문자열</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
    </table>  
</details>

[//]: # (일정 수정)
<details>
    <summary>작성자 수정</summary>

- 요청 정보
    <table>
      <tr>
        <td ><b>메소드</b></td>
        <td ><b>요청 URL</b></td>
      </tr>
      <tr>
        <td>PUT</td>
        <td>/api/writers/{writerId}</td>
      </tr>
    </table>

- 예시
    - 요청: PUT /api/writers/{writerId}
  ```json
  {
        "writer_id" : 1,
        "email": "b@c.com", -> 수정된 내용
        "name" : "아무개1"
  }
  ```
    - 응답: HTTP/1.1 200 OK
  ```json
  {
        "writer_id" : 1,
        "email": "b@c.com",
        "name" : "아무개1",
        "edit_date" : "2024-11-1"
  }
  ```
- 본문
  - 요청
    <table>
          <tr>
            <td ><b>이름</b></td>
            <td ><b>타입</b></td>
            <td ><b>설명</b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>writer_id</b></td>
            <td ><b>int</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>email</b></td>
            <td ><b>문자열</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>name</b></td>
            <td ><b>문자열</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
        </table>   
  - 응답
    <table>
          <tr>
            <td ><b>이름</b></td>
            <td ><b>타입</b></td>
            <td ><b>설명</b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>writer_id</b></td>
            <td ><b>int</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>email</b></td>
            <td ><b>문자열</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>name</b></td>
            <td ><b>문자열</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>edit_date</b></td>
            <td ><b>문자열->DATETIME</b></td>
            <td ><b></b></td>
            <td ><b>옵션</b></td>
          </tr>
    </table>  
</details>

[//]: # (작성자 삭제)
<details>
    <summary>작성자 삭제</summary>

- 요청 정보
    <table>
      <tr>
        <td ><b>메소드</b></td>
        <td ><b>요청 URL</b></td>
      </tr>
      <tr>
        <td>DELETE</td>
        <td>/api/writers/{writerId}</td>
      </tr>
    </table>

- 예시
    - 요청: DELETE /api/writers/{writerId}
    - 응답: HTTP/1.1 200 OK
</details>

# ERD

---
![ERD이미지](erd.png)

