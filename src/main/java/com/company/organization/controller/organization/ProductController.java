package com.company.organization.controller.organization;

import com.company.organization.controller.BaseController;
import com.company.organization.payload.organization.ProductDTO;
import com.company.organization.response.ResponseData;
import com.company.organization.service.organization.ProductService;
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
@RequestMapping(PRODUCT_URL)
@Tag(name = "Product", description = "This API is used for product crud")
public class ProductController extends BaseController<ProductService> {
    public ProductController(ProductService service) {
        super(service);
    }


    @Operation(summary = "This API is used for create product", responses = {
            @ApiResponse(responseCode = "201", description = "Create Product", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @PostMapping(CREATE_URL)
    public ResponseEntity<ResponseData<?>> create(@Valid @RequestBody ProductDTO dto) {
        return ResponseEntity.status(201)
                .body(ResponseData.builder().data(service.create(dto)).code(161210)
                        .message("Product successfully create").success(true).build());
    }


    @Operation(summary = "This API is used for update by id product", responses = {
            @ApiResponse(responseCode = "202", description = "update Product", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @PutMapping(UPDATE_URL)
    public ResponseEntity<ResponseData<?>> updateById(@PathVariable(value = "id") Long id, @Valid @RequestBody ProductDTO dto) {
        return ResponseEntity.status(202)
                .body(ResponseData.builder().data(service.updateById(id, dto)).code(161211)
                        .message("Product successfully update by id").success(true).build());
    }


    @Operation(summary = "This API is used for delete by id product", responses = {
            @ApiResponse(responseCode = "202", description = "delete Product", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @DeleteMapping(DELETE_URL)
    public ResponseEntity<ResponseData<?>> deleteById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(202)
                .body(ResponseData.builder().data(service.deleteById(id)).code(161212)
                        .message("Product successfully delete by id").success(true).build());
    }


    @Operation(summary = "This API is used for get by id product", responses = {
            @ApiResponse(responseCode = "200", description = "get Product", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @GetMapping(GET_URL)
    public ResponseEntity<ResponseData<?>> getById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(200)
                .body(ResponseData.builder().data(service.getById(id)).code(161213)
                        .message("Product successfully get by id").success(true).build());
    }


    @Operation(summary = "This API is used for get all product", responses = {
            @ApiResponse(responseCode = "200", description = "get all Product", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @GetMapping(GET_ALL_URL)
    public ResponseEntity<ResponseData<?>> getAll() {
        return ResponseEntity.status(200)
                .body(ResponseData.builder().data(service.getAll()).code(161214)
                        .message("Product successfully get all").success(true).build());
    }


    @Operation(summary = "This API is used for get all fixed product", responses = {
            @ApiResponse(responseCode = "200", description = "get all fixed Product", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @GetMapping(GET_ALL_PAGEABLE_URL)
    public ResponseEntity<ResponseData<?>> getAllByPageable(@RequestParam(required = false, defaultValue = "10") Integer size,
                                                            @RequestParam(required = false, defaultValue = "1") @Min(value = 1) Integer page) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
        return ResponseEntity.status(200)
                .body(ResponseData.builder().data(service.getAllFixed(pageRequest)).code(161215)
                        .message("Product successfully get all fixed").success(true).build());
    }


}
