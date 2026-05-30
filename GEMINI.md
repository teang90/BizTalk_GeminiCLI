# GEMINI.md: 업무 말투 변환기 (BizTone Converter)

이 파일은 **업무 말투 변환기** 프로젝트의 개발 및 운영을 위한 핵심 지침 문서입니다. Gemini CLI를 통한 모든 작업은 이 문서의 내용을 최우선으로 준수해야 합니다.

---

## 1. 프로젝트 개요 (Project Overview)

**업무 말투 변환기**는 사용자가 입력한 내용을 수신 대상(상사, 동료, 고객 등)에 최적화된 비즈니스 언어로 변환해주는 AI 서비스입니다.

-   **목적**: 비즈니스 커뮤니케이션의 효율성 증대 및 정중한 표현 자동화.
-   **핵심 기술**:
    -   **Backend**: Python (FastAPI)
    -   **Frontend**: HTML, CSS, Vanilla JavaScript
    -   **AI**: Upstage Solar-Pro2 (LangChain 연동)
-   **구조**: 
    -   사용자로부터 원문과 수신 대상을 입력받음.
    -   FastAPI 서버가 LangChain을 통해 Solar-Pro2 모델에 변환 요청.
    -   변환된 결과를 사용자에게 반환.

---

## 2. 빌드 및 실행 (Building and Running)

현재 프로젝트는 초기 기획 및 설계 단계에 있으며, 실제 코드는 `backend/` 및 `frontend/` 디렉토리에 구성될 예정입니다.

### 백엔드 (Python/FastAPI)
-   **의존성 설치**: `pip install fastapi uvicorn langchain langchain-upstage python-dotenv`
-   **실행**: `uvicorn main:app --reload --port 8000` (백엔드 디렉토리 기준)
-   **환경 변수**: `.env` 파일에 `UPSTAGE_API_KEY` 설정 필수.

### 프론트엔드 (Web)
-   **실행**: `frontend/index.html` 파일을 브라우저에서 직접 실행하거나 Live Server 사용.

---

## 3. 개발 규칙 및 컨벤션 (Development Conventions)

### 바이브 코딩 (Vibe Coding) 3원칙
1.  **완료 기준 우선 정의**: 구현 전 무엇을 만들지 체크리스트를 작성할 것.
2.  **조사 먼저, 구현 나중**: API나 라이브러리 연동 방식은 사전에 조사한 후 코드를 작성할 것.
3.  **버그 분석 우선**: 에러 발생 시 수정 전 원인을 먼저 파악하여 보고할 것.

### 보안 및 정체성 (Identity & Security)
-   **정체성**: Google Gemini 모델 기반의 전문적인 CLI 어시스턴트로 한국어를 기본으로 사용함.
-   **보안**: `.env` 및 민감 정보가 포함된 파일을 절대 출력하거나 커밋하지 않음.
-   **위험 명령**: 파괴적이거나 비가역적인 작업(파일 삭제, 강제 푸시 등) 수행 시 반드시 명시적 확인을 거침.

---

## 4. 주요 파일 및 디렉토리 (Key Files & Directories)

-   `개요서_업무말투변환기.md`: 프로젝트의 비즈니스 배경 및 핵심 기능 설명.
-   `PRD_업무말투변환기.md`: 제품 요구사항 명세서, 기술 스택, API 명세 및 구현 단계 포함.
-   `my-rules.md`: AI 어시스턴트가 준수해야 할 상세 행동 강령 및 금지 사항.
-   `SimpleHttpClient.java`: 자바 기반 HTTP 요청 참고용 샘플 코드.
-   `GEMINI.md`: 프로젝트 통합 지침서 (본 파일).

---

## 5. 단계별 구현 계획 (Implementation Roadmap)

1.  **STEP 1**: 환경 구성 및 디렉토리 구조 생성 (완료 여부 체크 필요).
2.  **STEP 2**: FastAPI 기반 백엔드 및 AI 연동 로직 구현.
3.  **STEP 3**: 웹 인터페이스(HTML/JS) 구현 및 API 연결.
4.  **STEP 4**: GitHub 업로드 및 Vercel 배포.

---
### @PRD_업무말투변환기.md 문서와 GEMINI.md 문서 항상 최신화 하기
* 모든 변경사항이 발생하면 (예를 들어 Source Code가 변경 되거나 라이브러리 버전이 변경되면) md 문서도 반드시 업데이트 합니다. 
* 구현이 완료된 사항들은 `2. 완료 체크리스트`에 모두 체크표시를 해서 완료 되었음을 반드시 표시하세요.
* `8. 단계별 구현 순서` 에서도 STEP별로 구현이 완료되면 체크표시를 해서 완료 되었음을 반드시 표시하세요.