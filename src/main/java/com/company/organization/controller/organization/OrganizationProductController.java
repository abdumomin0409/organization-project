package com.company.organization.controller.organization;

import com.company.organization.controller.BaseController;
import com.company.organization.payload.organization.OrganizationProductDTO;
import com.company.organization.response.ResponseData;
import com.company.organization.service.organization.OrganizationProductService;
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
import org.springframework.web.bind.annotation.*;

import static com.company.organization.utils.BaseURL.*;

@RestController
@RequestMapping(ORGANIZATION_PRODUCT_URL)
@Tag(name = "Organization_Product", description = "This API is used for organization product crud")
public class OrganizationProductController extends BaseController<OrganizationProductService> {
    public OrganizationProductController(OrganizationProductService service) {
        super(service);
    }


    @Operation(summary = "This API is used for create organization product", responses = {
            @ApiResponse(responseCode = "201", description = "Create Organization Product", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @PostMapping(CREATE_URL)
    public ResponseEntity<ResponseData<?>> create(@Valid @RequestBody OrganizationProductDTO dto) {
        return ResponseEntity.status(201)
                .body(ResponseData.builder().data(service.create(dto)).code(151210)
                        .message("Organization product successfully create").success(true).build());
    }


    @Operation(summary = "This API is used for delete by id organization product", responses = {
            @ApiResponse(responseCode = "202", description = "delete Organization Product", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @DeleteMapping(DELETE_WITHOUT_ID_URL)
    public ResponseEntity<ResponseData<?>> deleteById(@RequestBody @Valid OrganizationProductDTO dto) {
        service.deleteById(dto);
        return ResponseEntity.status(202)
                .body(ResponseData.builder().code(151212)
                        .message("Organization product successfully delete by id").success(true).build());
    }


    @Operation(summary = "This API is used for get by id organization product", responses = {
            @ApiResponse(responseCode = "200", description = "get Organization Product", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @GetMapping(GET_URL)
    public ResponseEntity<ResponseData<?>> getById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(200)
                .body(ResponseData.builder().data(service.getById(id)).code(151213)
                        .message("Organization product successfully get by id").success(true).build());
    }


    @Operation(summary = "This API is used for get all organization product", responses = {
            @ApiResponse(responseCode = "200", description = "get all Organization Product", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @GetMapping(GET_ALL_URL)
    public ResponseEntity<ResponseData<?>> getAll() {
        return ResponseEntity.status(200)
                .body(ResponseData.builder().data(service.getAll()).code(151214)
                        .message("Organization product successfully get all").success(true).build());
    }


    @Operation(summary = "This API is used for get all fixed organization product", responses = {
            @ApiResponse(responseCode = "200", description = "get all fixed Organization Product", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @GetMapping(GET_ALL_PAGEABLE_URL)
    public ResponseEntity<ResponseData<?>> getAllByPageable(@RequestParam(required = false, defaultValue = "10") Integer size,
                                                            @RequestParam(required = false, defaultValue = "1") @Min(value = 1) Integer page) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
        return ResponseEntity.status(200)
                .body(ResponseData.builder().data(service.getAllFixed(pageRequest)).code(151215)
                        .message("Organization product successfully get all fixed").success(true).build());
    }


}
