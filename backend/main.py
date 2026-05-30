from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from fastapi.staticfiles import StaticFiles
from fastapi.responses import FileResponse
from backend.routers import convert
import os

app = FastAPI(title="BizTone Converter API")

# CORS 설정
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # 실제 서비스 시 특정 도메인으로 제한 권장
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# API 라우터 등록
app.include_router(convert.router, prefix="/api")

# Health Check 엔드포인트
@app.get("/health")
async def health_check():
    return {"status": "ok"}

# 정적 파일 서빙 (프론트엔드)
if os.path.exists("frontend"):
    # css, js 디렉토리를 각각 마운트하여 index.html의 상대 경로와 일치시킴
    if os.path.exists("frontend/css"):
        app.mount("/css", StaticFiles(directory="frontend/css"), name="css")
    if os.path.exists("frontend/js"):
        app.mount("/js", StaticFiles(directory="frontend/js"), name="js")

    # 루트 경로 접속 시 index.html 반환
    @app.get("/")
    async def read_index():
        index_path = os.path.join("frontend", "index.html")
        if os.path.exists(index_path):
            return FileResponse(index_path)
        return {"message": "index.html not found in frontend directory"}
