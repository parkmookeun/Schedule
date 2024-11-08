# API 명세서

## schedule
|    기능    | Method |             URL             |                 request                 | response |          상태코드           
|:--------:|:------:|:---------------------------:|:---------------------------------------:|:--------:|:-----------------------:|
|  일정 등록   |  POST  |       /api/schedules        |            body: 일정생성요청 DTO             |  등록 정보   | 201: 정상등록 <br/> 400: 오류 |
|  일정 조회   |  GET   | /api/schedules/{scheduleId} |                    -                    | 단건 응답 정보 |        200: 정상조회 <br/> 404: 오류
| 일정 목록 조회 |  GET   |  /api/schedules  | param: 페이지 번호<br>페이지 크기<br>작성자명<br>수정날짜 | 다건 응답 정보 |        200: 정상조회 <br/> 400: 오류       
|  일정 수정   |  PUT   | /api/schedules/{scheduleId} |            body: 일정수정요청 DTO             |  수정 정보   |        200: 정상수정 <br/> 406: 오류       
|  일정 삭제   | DELETE | /api/schedules/{scheduleId} |            body: 일정삭제요청 DTO             |    -     |        200: 정상삭제 <br/> 406: 오류       

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
        "writerId" : 1,
        "password" : "abc!123",
        "todo" : "나는 오늘 강의를 들을 것이다."
  }
  ```
  - 응답: HTTP/1.1 201 Created

  ```json
  {
        "scheduleId" : 3,
        "writerId" : 1,
        "todo" : "나는 오늘 강의를 들을 것이다.",
        "createdDate" : "2024-10-31",
        "editDate" : "2024-10-31"
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
            <td ><b>writerId</b></td>
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
            <td ><b>scheduleId</b></td>
            <td ><b>int</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>writerId</b></td>
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
            <td ><b>createdDate</b></td>
            <td ><b>문자열->DATETIME</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>editDate</b></td>
            <td ><b>문자열->DATETIME</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
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
        <td >/api/schedules?pageNumber=XX&pageSize=XX&&writer=XX&date=XX</td>
      </tr>
    </table>
- 예시
  - 요청: GET /api/schedules?pageNumber=1&pageSize=5&&writer=익명1&date=2024-11-01

  - 응답: HTTP/1.1 200 OK

  ```json
  "schedules" : [
    {
        "scheduleId" : 3,
        "writerId" : 1,
        "todo" : "나는 오늘 강의를 들을 것이다.",
        "createdDate" : "2024-10-31",
  },
  {
        "scheduleId" : 2,
        "writerId" : 1,
        "todo" : "나는 오늘 강의를 들을 것이다.",
        "createdDate" : "2024-10-31",
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
            <td ><b>scheduleId</b></td>
            <td ><b>int</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>writerId</b></td>
            <td ><b>int</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>writer</b></td>
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
          <tr>
            <td ><b>editDate</b></td>
            <td ><b>문자열->DATETIME</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
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
    - 요청: GET /api/schedules/2

    - 응답: HTTP/1.1 200 OK
  ```json
  {
        "scheduleId" : 2,
        "writerId" : 1,
        "todo" : "나는 오늘 강의를 들을 것이다.",
        "createdDate" : "2024-10-31",
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
            <td ><b>scheduleId</b></td>
            <td ><b>int</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>writerId</b></td>
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
            <td ><b>createdDate</b></td>
            <td ><b>문자열->DATETIME</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
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
        "scheduleId" : 2,
        "password" : "abc!123",
        "todo" : "나는 오늘 쉴 것이다." -> 수정된 내용
  }
  ```
    - 응답: HTTP/1.1 200 OK
  ```json
  {
        "scheduleId" : 2,
        "todo" : "나는 오늘 쉴 것이다.",
        "editDate" : "2024-11-1"
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
            <td ><b>scheduleId</b></td>
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
            <td ><b>scheduleId</b></td>
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
            <td ><b>editDate</b></td>
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
    - 요청: DELETE /api/schedules/13
  ```json
  {
        "password" : "abc!123"
  }
  ```
    - 응답: HTTP/1.1 200 OK
   ```json
  {
        13
  }
  ```
</details>

## writer
|   기능   | Method |            URL            |      request      | response |          상태코드           
|:------:|:------:|:-------------------------:|:-----------------:|:--------:|:-----------------------:|
| 작성자 등록 |  POST  |       /api/writers        | body: 작성자생성요청 DTO |  작성자 정보  | 201: 정상등록 <br/> 400: 오류 |
| 작성자 조회 |  GET   |  /api/writers/{writerId}  |         -         |  작성자 정보  |        200: 정상조회 <br/> 404: 오류
| 작성자 수정 |  PUT   |  /api/writers/{writerId}  | body: 작성자수정요청 DTO |  수정된 정보  |        200: 정상조회 <br/> 406: 오류
| 작성자 삭제 | DELETE | /api/writers/{scheduleId} |         -         |    -     |        200: 정상삭제 <br/>       

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
        "writerId" : 1,
        "email" : "a@b.com",
        "name" : "아무개1",
        "createdDate" : "2024-10-31",
        "editDate" : "2024-10-31"
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
            <td ><b>writerId</b></td>
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
            <td ><b>createdDate</b></td>
            <td ><b>문자열->DATETIME</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
          </tr>
          <tr>
            <td ><b>editDate</b></td>
            <td ><b>문자열->DATETIME</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
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
    - 요청: GET /api/writers/1

    - 응답: HTTP/1.1 200 OK
  
  ```json
  {
        "writerId" : 1,
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
            <td ><b>writerId</b></td>
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
    - 요청: PUT /api/writers/1
  ```json
  {
        "email": "b@c.com", -> 수정된 내용
        "name" : "아무개1"
  }
  ```
    - 응답: HTTP/1.1 200 OK
  ```json
  {
        "writerId" : 1,
        "email": "b@c.com",
        "name" : "아무개1",
        "editDate" : "2024-11-1"
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
            <td ><b>writerId</b></td>
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
            <td ><b>editDate</b></td>
            <td ><b>문자열->DATETIME</b></td>
            <td ><b></b></td>
            <td ><b>필수</b></td>
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

