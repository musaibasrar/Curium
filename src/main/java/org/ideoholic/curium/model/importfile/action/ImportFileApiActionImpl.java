package org.ideoholic.curium.model.importfile.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.feescollection.service.FeesCollectionService;
import org.ideoholic.curium.model.importfile.service.ImportFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImportFileApiActionImpl implements ImportFileApiAction {

    @Autowired
    private ImportFileService importFileService;
    @Autowired
    private FeesCollectionService feesCollectionService;

    public ResponseEntity<ResultResponse> readFile(MultipartFile uploadedFiles, String branchId) {
        try {
            ResultResponse result = importFileService.readFile(uploadedFiles, branchId);
            if (result.isSuccess()) {
                return ResponseEntity.ok(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new CustomResponseException(CustomErrorMessage.IMPORTFAILURE);
    }

    public ResponseEntity<ResultResponse> readFileForFees(MultipartFile uploadedFiles,String currentAcademicYear, String branchId, String userId,String userName) {
        try {
            ResultResponse result = feesCollectionService.readFileForFees(uploadedFiles,currentAcademicYear,branchId,userId,userName);
            if (result.isSuccess()) {
                return ResponseEntity.ok(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new CustomResponseException(CustomErrorMessage.IMPORTFAILURE);
    }
}