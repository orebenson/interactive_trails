package com.Team4.SmartTowns.qrpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QRController {

    @GetMapping("/scan")
    public String qrScannerPage() {
        // Assuming the HTML file is named 'qr-scanner.html' and located under 'src/main/resources/templates'
        return "/QRpage/scan-qr";
    }

}
