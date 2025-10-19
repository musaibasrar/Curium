package org.ideoholic.curium.model.importfile.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.importfile.service.ImportFileService;
import org.ideoholic.curium.util.Constants;
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
    @Autowired
    private ImportFileService importFileService;

    public boolean readFile(MultipartFile uploadedFiles) throws FileNotFoundException, IOException {
        ResultResponse result = importFileService.readFile(uploadedFiles,httpSession.getAttribute(Constants.BRANCHID).toString());

        return result.isSuccess();
    }

}