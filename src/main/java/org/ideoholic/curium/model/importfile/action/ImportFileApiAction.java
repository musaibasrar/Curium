package org.ideoholic.curium.model.importfile.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/importProcess")
public interface ImportFileApiAction {
    @PostMapping(value = "/postReadFile")
    public ResponseEntity<ResultResponse> readFile(@RequestParam("fileToImport") MultipartFile uploadedFiles, @RequestHeader(value = Constants.BRANCHID) String branchId);

    @PostMapping(value = "/postReadFileFees")
    public ResponseEntity<ResultResponse> readFileForFees(@RequestParam("fileToImport") MultipartFile uploadedFiles, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear, @RequestHeader(value = Constants.BRANCHID) String branchId, @RequestHeader(value = Constants.USERID) String userId, @RequestHeader(value = Constants.USERNAME) String userName);

}