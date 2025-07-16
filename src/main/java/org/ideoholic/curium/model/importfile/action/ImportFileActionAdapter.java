package org.ideoholic.curium.model.importfile.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.importfile.service.ImportFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class ImportFileActionAdapter {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession httpSession;

    private String BRANCHID = "branchid";

    public boolean readFile(MultipartFile uploadedFiles, String branchId) throws FileNotFoundException, IOException {
        ImportFileService importFileService = new ImportFileService(request,response);

        ResultResponse result = importFileService.readFile(uploadedFiles,httpSession.getAttribute(BRANCHID).toString());

        return result.isSuccess();
    }

}
