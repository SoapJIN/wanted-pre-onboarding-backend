# 📒 원티드 프리온보딩 백엔드 인턴십 선발과제

## ✔ 프로젝트 소개
- 본 서비스는 기업의 채용을 위한 웹 서비스 입니다.
- 회사는 채용공고를 생성하고, 이에 사용자는 지원합니다.

&nbsp;

## ✔ 시스템 환경
- java 17
- Spring Boot 3.1.4
- MYSQL
- JPA

&nbsp;

## ✔ 프로젝트 설정
- `application.properties` DB연결을 위해 수정 필요

&nbsp;

## ✔ API
| HTTP Method | URI                             | 기능             |
|-------------|---------------------------------|----------------|
| POST        | /api/recruits                   | 채용공고 등록        |
| PATCH       | /api/recruits/{id}              | 채용공고 수정        |
| DELETE      | /api/recruits/{id}              | 채용공고 삭제        |
| GET         | /api/recruits                   | 채용공고 목록 조회     |
| GET        | /api/recruits?keyword={keyword} | 채용공고 검색 기능     |
| GET        | /api/recruits/{id}              | 채용공고 상세 페이지 조회 |
| POST        | /api/applies                    | 채용공고 사용자 지원    |

&nbsp;

## ✔ 기능구현
- 채용공고 등록
- 채용공고 수정
- 채용공고 삭제
- 채용공고 목록 조회 및 검색기능
- 채용공고 상세 페이지 조회
- 채용공고 사용자 지원

&nbsp;

1. 채용공고 등록
```java
    @PostMapping("")
    public ResponseEntity<String> registerRecruit(@RequestBody RecruitRequestDTO dto) {
        recruitService.register(dto);

        return ResponseEntity.ok("채용공고 성공적으로 등록됐습니다.");
    }
```

```java
//request
POST /api/recruits

{
  "companyId" : 1,
  "position" : "백엔드 주니어 개발자",
  "compensation" : 1000000,
  "content" : "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "techStack" : "Python"
}
```

```java
//response
-성공
"채용공고 성공적으로 등록됐습니다."

------------------------------

-실패(회사가 존재하지 않을때)
{
  "status": 404,
  "name": "COMPANY_NOT_FOUND",
  "message": "회사 정보를 찾을 수 없습니다."
}


-실패2(데이터 저장 실패시)
{
  "status": 500,
  "name": "DB_SAVE_ERROR",
  "message": "Save 에러 발생."
}
```

&nbsp;

2. 채용공고 수정
```java
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateRecruit(@PathVariable("id") Long id, @RequestBody RecruitRequestDTO dto) {
        recruitService.update(id, dto);
        
        return ResponseEntity.ok("채용공고 수정이 완료됐습니다.");
    }
```

```java
//request
PATCH /api/recruits/1

{
  "position" : "백엔드 주니어 개발자",
  "compensation" : 1000000,
  "content" : "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "techStack" : "Python"
}
```

```java
//response
-성공
"채용공고 수정이 완료됐습니다."

------------------------------

-실패(채용공고가 존재하지 않을때)
{
  "status": 404,
  "name": "RECRUIT_NOT_FOUND",
  "message": "채용 정보를 찾을 수 없습니다."
}
```

&nbsp;

3. 채용공고 삭제
```java
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecruit(@PathVariable("id") Long id) {
        recruitService.delete(id);
        return ResponseEntity.ok("채용공고 삭제가 완료됐습니다.");
    }
```

```java
//request
DELETE /api/recruits/1
```

```java
//response
-성공
"채용공고 삭제가 완료됐습니다."

------------------------------

-실패(채용공고가 존재하지 않을때)
{
  "status": 404,
  "name": "RECRUIT_NOT_FOUND",
  "message": "채용 정보를 찾을 수 없습니다."
}
```

&nbsp;

4-1. 채용공고 목록 조회
```java
    @GetMapping("")
    public ResponseEntity<List<RecruitResponseDTO>> search(@RequestParam(value="keyword", required = false) String keyword){
        List<RecruitResponseDTO> list;

        if(keyword != null) {
        list = recruitService.getListByKeyword(keyword);
        }else {
        list = recruitService.getList();
        }

        return ResponseEntity.ok(list);
    }
```

```java
//request
GET /api/recruits
```

```java
//response
-성공
[
  {
    "id": 1,
    "companyName": "나비컴퍼니",
    "companyCountry": "한국",
    "companyArea": "서울",
    "position": "백엔드 주니어 개발자",
    "compensation": 1000000,
    "techStack": "Python"
  },
  {
    "id": 2,
    "companyName": "나비컴퍼니",
    "companyCountry": "한국",
    "companyArea": "서울",
    "position": "백엔드 주니어 개발자",
    "compensation": 20000,
    "techStack": "Spring"
  },
  {
    "id": 3,
    "companyName": "원티드컴퍼니",
    "companyCountry": "한국",
    "companyArea": "경기도",
    "position": "프론트 개발자",
    "compensation": 30000,
    "techStack": "react"
  },
  {
    "id": 4,
    "companyName": "원티드컴퍼니",
    "companyCountry": "한국",
    "companyArea": "경기도",
    "position": "백엔드 주니어 개발자",
    "compensation": 1000000,
    "techStack": "java, Spring"
  }
]
```

&nbsp;

4-2. 채용공고 검색 기능
```java
//request
GET /api/recruits?keyword=나비
```

```java
//response
-성공
[
  {
    "id": 1,
    "companyName": "나비컴퍼니",
    "companyCountry": "한국",
    "companyArea": "서울",
    "position": "백엔드 주니어 개발자",
    "compensation": 1000000,
    "techStack": "Python"
  },
  {
    "id": 2,
    "companyName": "나비컴퍼니",
    "companyCountry": "한국",
    "companyArea": "서울",
    "position": "백엔드 주니어 개발자",
    "compensation": 20000,
    "techStack": "Spring"
  }
]
```

&nbsp;

5. 채용공고 상세 페이지 조회
```java
    @GetMapping("/{id}")
    public ResponseEntity<RecruitDetailResponseDTO> getRecruitDetail(@PathVariable("id")Long id) {
        RecruitDetailResponseDTO dto = recruitService.getDetail(id);

        return ResponseEntity.ok(dto);
    }
```

```java
//request
GET /api/recruits/1
```

```java
//response
-성공
{
  "id": 1,
  "companyName": "나비컴퍼니",
  "companyCountry": "한국",
  "companyArea": "서울",
  "position": "백엔드 주니어 개발자",
  "compensation": 1000000,
  "content": "원티드랩에서 22백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "techStack": "Python",
  "companyRecruitIdList": [ //같은 회사에 다른 채용공고 id 리스트
    2 
  ]
}

------------------------------

-실패(채용공고가 존재하지 않을때)
{
  "status": 404,
  "name": "RECRUIT_NOT_FOUND",
  "message": "채용 정보를 찾을 수 없습니다."
}
```

&nbsp;

6. 채용공고 사용자 지원
```java
    @PostMapping("")
    public ResponseEntity<String> apply(@RequestBody ApplyRequestDTO applyRequestDTO) {
        applyService.register(applyRequestDTO);

        return ResponseEntity.ok("채용공고에 지원 성공했습니다.");
    }
```

```java
//request
GET /api/applies

{
  "recruitId": 1,
  "userId":1
}
```

```java
//response
-성공
"채용공고에 지원 성공했습니다."

------------------------------

-실패(채용공고가 존재하지 않을때)
{
  "status": 404,
  "name": "RECRUIT_NOT_FOUND",
  "message": "채용 정보를 찾을 수 없습니다."
}


-실패2(유저가 존재하지 않을때)
{
  "status": 404,
  "name": "USER_NOT_FOUND",
  "message": "유저 정보를 찾을 수 없습니다."
}


-실패2(중복 신청 할때)
{
  "status": 400,
  "name": "ALREADY_PROCESSED",
  "message": "이미 존재하는 프로세스입니다."
}
```



