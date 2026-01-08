package org.ideoholic.curium.model.importfile.action;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.importfile.service.ImportFileService;
import org.ideoholic.curium.util.Constants;
import org.ideoholic.curium.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImportFileActionAdapter {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ImportFileService importFileService;

    public boolean readFile(MultipartFile uploadedFiles) throws FileNotFoundException, IOException {
        ResultResponse result = importFileService.readFile(uploadedFiles,DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));

        return result.isSuccess();
    }

}