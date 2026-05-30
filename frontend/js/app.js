// app.js
const API_BASE = ""; // 동일 도메인(FastAPI 서빙)이므로 비워둡니다.

document.addEventListener("DOMContentLoaded", () => {
    const targetBtns = document.querySelectorAll(".target-btn");
    const convertBtn = document.getElementById("convertBtn");
    const copyBtn = document.getElementById("copyBtn");
    const inputText = document.getElementById("inputText");
    const outputText = document.getElementById("outputText");
    const resultArea = document.getElementById("resultArea");
    const loadingOverlay = document.getElementById("loadingOverlay");

    let selectedTarget = null;

    // 수신 대상 버튼 클릭 이벤트
    targetBtns.forEach(btn => {
        btn.addEventListener("click", () => {
            targetBtns.forEach(b => b.classList.remove("active"));
            btn.classList.add("active");
            selectedTarget = btn.dataset.target;
        });
    });

    // 변환하기 버튼 클릭 이벤트
    convertBtn.addEventListener("click", async () => {
        const text = inputText.value.trim();

        if (!selectedTarget) {
            alert("수신 대상을 선택해주세요.");
            return;
        }
        if (!text) {
            alert("내용을 입력해주세요.");
            return;
        }

        setLoading(true);

        try {
            const response = await fetch(`${API_BASE}/api/convert`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    text: text,
                    target_audience: selectedTarget
                }),
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.detail || "변환 중 오류가 발생했습니다.");
            }

            const data = await response.json();
            outputText.value = data.converted_text;
            resultArea.style.display = "block";
            
            // 결과창으로 스크롤
            resultArea.scrollIntoView({ behavior: "smooth" });

        } catch (error) {
            alert(`오류: ${error.message}`);
        } finally {
            setLoading(false);
        }
    });

    // 복사하기 버튼 클릭 이벤트
    copyBtn.addEventListener("click", () => {
        outputText.select();
        document.execCommand("copy");
        
        const originalText = copyBtn.innerText;
        copyBtn.innerText = "복사 완료! ✅";
        setTimeout(() => {
            copyBtn.innerText = originalText;
        }, 2000);
    });

    function setLoading(isLoading) {
        loadingOverlay.style.display = isLoading ? "flex" : "none";
        convertBtn.disabled = isLoading;
    }
});
