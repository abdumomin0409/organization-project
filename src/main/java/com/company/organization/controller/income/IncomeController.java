package com.company.organization.controller.income;

import com.company.organization.controller.BaseController;
import com.company.organization.payload.income.IncomeDTO;
import com.company.organization.response.ResponseData;
import com.company.organization.service.income.IncomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.company.organization.utils.BaseURL.*;

@RestController
@RequestMapping(INCOME_URL)
@Tag(name = "Income Product", description = "This API is used for income crud")
public class IncomeController extends BaseController<IncomeService> {
    public IncomeController(IncomeService service) {
        super(service);
    }


    @Operation(summary = "This API is used for create income", responses = {
            @ApiResponse(responseCode = "201", description = "Create Income Product", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @PostMapping(CREATE_URL)
    public ResponseEntity<ResponseData<?>> create(@Valid @RequestBody IncomeDTO dto) {
        return ResponseEntity.status(201)
                .body(ResponseData.builder().data(service.create(dto)).code(151210)
                        .message("Income product successfully create").success(true).build());
    }


    @Operation(summary = "This API is used for get all incomes by warehouseId", responses = {
            @ApiResponse(responseCode = "200", description = "get Income Product", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @GetMapping(GET_ALL_INCOME_OR_OUTCOME_BY_WAREHOUSE_URL)
    public ResponseEntity<ResponseData<?>> getById(@PathVariable(value = "warehouseId") Long id) {
        return ResponseEntity.status(200)
                .body(ResponseData.builder().data(service.getIncomesResponseByWarehouseId(id)).code(151213)
                        .message("Income product successfully get by id").success(true).build());
    }


}
