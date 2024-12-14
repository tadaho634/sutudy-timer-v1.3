// script.js
let timeLeft = 25 * 60; // 25分 (秒単位)
let breakTime = 5 * 60; // 5分の休憩
let isRunning = false;
let level = 1;
let popcornCount = 0;
let interval;

const timeLeftDisplay = document.getElementById("time-left");
const levelDisplay = document.getElementById("level");
const popcornCountDisplay = document.getElementById("popcorn-count");
const startButton = document.getElementById("start-btn");
const resetButton = document.getElementById("reset-btn");

function updateDisplay() {
    let minutes = Math.floor(timeLeft / 60);
    let seconds = timeLeft % 60;
    timeLeftDisplay.textContent = `残り時間: ${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
    popcornCountDisplay.textContent = `ポップコーン: ${popcornCount} 粒`;
    levelDisplay.textContent = `レベル: ${level}`;
}

function startTimer() {
    if (isRunning) return;

    isRunning = true;
    interval = setInterval(() => {
        if (timeLeft > 0) {
            timeLeft--;
            popcornCount++;
            updateDisplay();
        } else {
            clearInterval(interval);
            alert(`レベル${level} 完了！5分休憩を始めましょう！`);
            startBreak();
        }
    }, 1000);
}

function startBreak() {
    timeLeft = breakTime;
    updateDisplay();

    let breakInterval = setInterval(() => {
        if (timeLeft > 0) {
            timeLeft--;
            updateDisplay();
        } else {
            clearInterval(breakInterval);
            level++;
            popcornCount += 5; // 休憩でボーナスポップコーン
            timeLeft = 25 * 60; // 25分の再設定
            isRunning = false;
            alert("休憩終了！次のレベルに進みましょう！");
            updateDisplay();
        }
    }, 1000);
}

function resetTimer() {
    clearInterval(interval);
    isRunning = false;
    timeLeft = 25 * 60;
    level = 1;
    popcornCount = 0;
    updateDisplay();
}

startButton.addEventListener("click", startTimer);
resetButton.addEventListener("click", resetTimer);

updateDisplay();
