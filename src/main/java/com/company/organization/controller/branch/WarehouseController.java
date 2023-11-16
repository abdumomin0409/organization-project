package com.company.organization.controller.branch;

import com.company.organization.controller.BaseController;
import com.company.organization.payload.branch.WarehouseDTO;
import com.company.organization.response.ResponseData;
import com.company.organization.service.branch.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.company.organization.utils.BaseURL.*;

@RestController
@RequestMapping(WAREHOUSE_URL)
@Tag(name = "Warehouse", description = "This API is used for warehouse crud")
@PreAuthorize("isAuthenticated()")
public class WarehouseController extends BaseController<WarehouseService> {
    public WarehouseController(WarehouseService service) {
        super(service);
    }


    @Operation(summary = "This API is used for create warehouse", responses = {
            @ApiResponse(responseCode = "201", description = "Create Warehouse", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @PostMapping(CREATE_URL)
    public ResponseEntity<ResponseData<?>> create(@Valid @RequestBody WarehouseDTO dto) {
        return ResponseEntity.status(201)
                .body(ResponseData.builder().data(service.create(dto)).code(141210)
                        .message("Branch successfully create").success(true).build());
    }


    @Operation(summary = "This API is used for update by id warehouse", responses = {
            @ApiResponse(responseCode = "202", description = "update Warehouse", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @PutMapping(UPDATE_URL)
    public ResponseEntity<ResponseData<?>> updateById(@PathVariable(value = "id") Long id, @Valid @RequestBody WarehouseDTO dto) {
        return ResponseEntity.status(202)
                .body(ResponseData.builder().data(service.updateById(id, dto)).code(141211)
                        .message("Branch successfully update by id").success(true).build());
    }


    @Operation(summary = "This API is used for delete by id warehouse", responses = {
            @ApiResponse(responseCode = "202", description = "delete Warehouse", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @DeleteMapping(DELETE_URL)
    public ResponseEntity<ResponseData<?>> deleteById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(202)
                .body(ResponseData.builder().data(service.deleteById(id)).code(141212)
                        .message("Branch successfully delete by id").success(true).build());
    }


    @Operation(summary = "This API is used for get by id warehouse", responses = {
            @ApiResponse(responseCode = "200", description = "get Warehouse", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @GetMapping(GET_URL)
    public ResponseEntity<ResponseData<?>> getById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(200)
                .body(ResponseData.builder().data(service.getId(id)).code(141213)
                        .message("Branch successfully get by id").success(true).build());
    }


    @Operation(summary = "This API is used for get all warehouse", responses = {
            @ApiResponse(responseCode = "200", description = "get all Warehouse", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @GetMapping(GET_ALL_URL)
    public ResponseEntity<ResponseData<?>> getAll() {
        return ResponseEntity.status(200)
                .body(ResponseData.builder().data(service.getAll()).code(141214)
                        .message("Branch successfully get all").success(true).build());
    }


    @Operation(summary = "This API is used for get all fixed warehouse", responses = {
            @ApiResponse(responseCode = "200", description = "get all fixed Warehouse", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @GetMapping(GET_ALL_PAGEABLE_URL)
    public ResponseEntity<ResponseData<?>> getAllByPageable(@RequestParam(required = false, defaultValue = "10") Integer size,
                                                            @RequestParam(required = false, defaultValue = "1") @Min(value = 1) Integer page) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
        return ResponseEntity.status(200)
                .body(ResponseData.builder().data(service.getAllFixed(pageRequest)).code(141215)
                        .message("Branch successfully get all fixed").success(true).build());
    }


}
