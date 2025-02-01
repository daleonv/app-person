package com.ec.app.microservices.controllers;

import com.ec.app.microservices.AccountStatusRequestVo;
import com.ec.app.microservices.AccountStatusResponseVo;
import com.ec.app.microservices.config.Response;
import com.ec.app.microservices.services.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("reports")
@Lazy
public class ReportController {
    @Lazy
    @Autowired
    private IReportService reportService;

    @PostMapping("find")
    public ResponseEntity<Response<List<AccountStatusResponseVo>>> findAccountStatus(
            @RequestBody AccountStatusRequestVo filter) {
        return new ResponseEntity<>(Response.<List<AccountStatusResponseVo>>builder()
                .data(reportService.findAccountStatus(filter))
                .code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }

    @PostMapping("/download")
    public ResponseEntity<byte[]> downloadAccountStatus(
            @RequestBody AccountStatusRequestVo filter) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        byte[] fileBytes = reportService.downloadAccountStatus(filter);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= \"" +
                "report" + "." + filter.getExtension() + "\"");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileBytes);
    }
}
