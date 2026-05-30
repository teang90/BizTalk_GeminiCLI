from fastapi import APIRouter, HTTPException
from backend.models.schemas import ConvertRequest, ConvertResponse
from backend.services.tone_converter import ToneConverter

router = APIRouter()
converter = ToneConverter()

@router.post("/convert", response_model=ConvertResponse)
async def convert_text(request: ConvertRequest):
    try:
        converted = await converter.convert(request.text, request.target_audience)
        return ConvertResponse(
            converted_text=converted,
            target_audience=request.target_audience,
            original_text=request.text
        )
    except ValueError as e:
        raise HTTPException(status_code=400, detail=str(e))
    except Exception as e:
        # 상세 에러 로그는 서버 콘솔에 남기고 사용자에게는 간략히 전달
        print(f"Error during conversion: {e}")
        raise HTTPException(status_code=500, detail="AI 변환 처리 중 오류가 발생했습니다.")
