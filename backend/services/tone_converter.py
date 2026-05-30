import os
from dotenv import load_dotenv
from langchain_upstage import ChatUpstage
from langchain_core.prompts import ChatPromptTemplate
from backend.prompts.templates import PROMPTS

# .env 로드
load_dotenv()

class ToneConverter:
    def __init__(self):
        api_key = os.getenv("UPSTAGE_API_KEY")
        if not api_key:
            raise ValueError("UPSTAGE_API_KEY가 설정되어 있지 않습니다. .env 파일을 확인해주세요.")
        
        # LangChain 1.x 방식의 ChatUpstage 초기화
        # 모델명 'solar-pro'는 Solar-Pro3를 포함한 최신 시리즈를 지칭합니다.
        self.llm = ChatUpstage(model="solar-pro", upstage_api_key=api_key)

    async def convert(self, text: str, target_audience: str) -> str:
        if target_audience not in PROMPTS:
            raise ValueError(f"지원하지 않는 수신 대상입니다: {target_audience}")

        system_prompt = PROMPTS[target_audience]
        
        # 로깅: 요청 내역 출력
        print(f"\n[LLM Request - Target: {target_audience}]")
        print(f"- System Prompt: {system_prompt}")
        print(f"- User Text: {text}")

        # ChatPromptTemplate과 LCEL(pipe |) 사용
        prompt = ChatPromptTemplate.from_messages([
            ("system", system_prompt),
            ("human", "{text}")
        ])
        
        chain = prompt | self.llm
        
        # 비동기 호출 실행
        response = await chain.ainvoke({"text": text})
        
        # 로깅: 응답 내역 출력
        print(f"\n[LLM Response]")
        print(f"- Converted: {response.content}\n")
        
        return response.content
