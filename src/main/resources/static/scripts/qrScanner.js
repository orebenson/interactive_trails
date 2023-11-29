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

                scanning = false;
                video.srcObject.getTracks().forEach(track => track.stop());
                startScanBtn.textContent = 'Start Scan';
                qrResult.textContent = 'QR Code Detected. Sending...';

                // Send the QR code to the server.
                sendQRCodeToServer(code.data);
            } else {
                // If no QR code was detected, continue scanning.
                requestAnimationFrame(scanQRCode);
            }
        } else {
            canvasElement.hidden = true;
        }
    }


    function sendQRCodeToServer(qrData) {
        // Construct the POST request to send the QR code data to the server
        fetch('/QRpage/scan', {
            method: 'POST',
            headers: {
                'Content-Type': 'text/plain',
                // Add any other headers your server requires, such as CSRF tokens
            },
            body: qrData
        })
            .then(response => {
                if (response.ok) {
                    return response.text();
                } else {
                    throw new Error('Server returned an error while scanning QR code.');
                }
            })
            .then(text => {
                qrResult.textContent = 'QR Code Result: ' + text;
            })
            .catch(error => {
                console.error('Error during QR code scan:', error);
                qrResult.textContent = 'Error during QR code scan.';
            });
    }

});