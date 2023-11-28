document.addEventListener('DOMContentLoaded', function () {
    const video = document.getElementById('qr-video');
    const canvasElement = document.getElementById('qr-canvas');
    const canvas = canvasElement.getContext('2d');
    const qrResult = document.getElementById('qr-result');
    const startScanBtn = document.getElementById('start-scan');
    let scanning = false;

    startScanBtn.onclick = function() {
        if (scanning) {
            scanning = false;
            video.srcObject.getTracks().forEach(track => track.stop());
            startScanBtn.textContent = 'Start Scan';
        } else {
            navigator.mediaDevices.getUserMedia({ video: { facingMode: 'environment' } })
                .then(function(stream) {
                    scanning = true;
                    startScanBtn.textContent = 'Stop Scan';
                    video.srcObject = stream;
                    video.play();
                    scanQRCode();
                }).catch(function(error) {
                console.error(error);
                qrResult.textContent = 'Error accessing the camera';
            });
        }
    };

    function scanQRCode() {
        if (scanning) {
            canvasElement.hidden = false;
            canvas.drawImage(video, 0, 0, canvasElement.width, canvasElement.height);
            const imageData = canvas.getImageData(0, 0, canvasElement.width, canvasElement.height);
            const code = jsQR(imageData.data, imageData.width, imageData.height, {
                inversionAttempts: "dontInvert",
            });
            if (code) {
                qrResult.textContent = 'QR Code Result: ' + code.data;
                scanning = false;
                video.srcObject.getTracks().forEach(track => track.stop());
                startScanBtn.textContent = 'Start Scan';
            } else {
                requestAnimationFrame(scanQRCode);
            }
        } else {
            canvasElement.hidden = true;
        }
    }
});