package org.ideoholic.curium.model.mess.stockentry.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.mess.item.action.MessItemActionAdapter;
import org.ideoholic.curium.model.mess.item.dto.ItemDetailsDto;
import org.ideoholic.curium.model.mess.item.dto.ItemsDto;
import org.ideoholic.curium.model.mess.item.dto.MessIdsDto;
import org.ideoholic.curium.model.mess.item.service.MessItemsService;
import org.ideoholic.curium.model.mess.stockentry.service.MessStockEntryService;
import org.ideoholic.curium.model.mess.supplier.service.MessSuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/stockentry")
public class MessStockEntryApiAction {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private MessItemActionAdapter messItemActionAdapter;

    @Autowired
    private MessStockEntryActionAdapter messStockEntryActionAdapter;

    private MessItemsService messItemsService;


    @GetMapping("/mrvDetails")
    public ResponseEntity mrvDetails() throws IOException {
        try {
            messStockEntryActionAdapter.getMRVDetails();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/savePurchase")
    public ResponseEntity savePurchase() {

        return ResponseEntity.ok("purchase");
    }

    @PostMapping("/deleteItems")
    public ResponseEntity<ResultResponse> deleteItems(@RequestBody MessIdsDto dto, @RequestHeader(value = "branchid") String branchId) {
       messItemsService.deleteMultipleItems(dto);
        return viewItems(branchId);
    }

    @PostMapping("/updateItems")
    public ResponseEntity<ResultResponse> updateItems(@RequestBody ItemsDto dto, @RequestHeader(value = "branchid") String branchId) {
        messItemsService.updateItems(dto);
        return viewItems(branchId);
    }

    @GetMapping("/viewItems")
    public ResponseEntity<ResultResponse> viewItems(@RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = messItemsService.viewItemDetails(branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/addItems")
    public ResponseEntity<ResultResponse> addItems(@RequestBody ItemDetailsDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userid") String userId) {
        messItemsService.addItemDetails(dto, userId, branchId);
        return viewItems(branchId);
    }

    @GetMapping("/purchaseItems")
    public ResponseEntity<ResultResponse> purchaseItems(@RequestHeader(value = "branchid") String branchId) {
        // TODO: Need to fix this after migrating MessSuppliersService.
        new MessSuppliersService(request, response).viewSuppliersDetails();
        ResultResponse result = messItemsService.viewItemDetails(branchId);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/addSuppliers", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity addSuppliers() {
        return ResponseEntity.ok("addsuppliers");
    }
}
