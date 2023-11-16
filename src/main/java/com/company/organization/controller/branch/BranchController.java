package com.company.organization.controller.branch;

import com.company.organization.controller.BaseController;
import com.company.organization.payload.branch.BranchDTO;
import com.company.organization.response.ResponseData;
import com.company.organization.service.branch.BranchService;
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
@RequestMapping(BRANCH_URL)
@Tag(name = "Branch", description = "This API is used for branch crud")
public class BranchController extends BaseController<BranchService> {
    public BranchController(BranchService service) {
        super(service);
    }


    @Operation(summary = "This API is used for create branch", responses = {
            @ApiResponse(responseCode = "201", description = "Create Branch", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @PostMapping(CREATE_URL)
    public ResponseEntity<ResponseData<?>> create(@Valid @RequestBody BranchDTO dto) {
        return ResponseEntity.status(201)
                .body(ResponseData.builder().data(service.create(dto)).code(131210)
                        .message("Branch successfully create").success(true).build());
    }


    @Operation(summary = "This API is used for update by id branch", responses = {
            @ApiResponse(responseCode = "202", description = "update Branch", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @PutMapping(UPDATE_URL)
    public ResponseEntity<ResponseData<?>> updateById(@PathVariable(value = "id") Long id, @Valid @RequestBody BranchDTO dto) {
        return ResponseEntity.status(202)
                .body(ResponseData.builder().data(service.updateById(id, dto)).code(131211)
                        .message("Branch successfully update by id").success(true).build());
    }


    @Operation(summary = "This API is used for delete by id branch", responses = {
            @ApiResponse(responseCode = "202", description = "delete Branch", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @DeleteMapping(DELETE_URL)
    public ResponseEntity<ResponseData<?>> deleteById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(202)
                .body(ResponseData.builder().data(service.deleteById(id)).code(131212)
                        .message("Branch successfully delete by id").success(true).build());
    }


    @Operation(summary = "This API is used for get by id branch", responses = {
            @ApiResponse(responseCode = "200", description = "get Branch", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @GetMapping(GET_URL)
    public ResponseEntity<ResponseData<?>> getById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(200)
                .body(ResponseData.builder().data(service.getById(id)).code(131213)
                        .message("Branch successfully get by id").success(true).build());
    }


    @Operation(summary = "This API is used for get all branch", responses = {
            @ApiResponse(responseCode = "200", description = "get all Branch", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @GetMapping(GET_ALL_URL)
    public ResponseEntity<ResponseData<?>> getAll() {
        return ResponseEntity.status(200)
                .body(ResponseData.builder().data(service.getAll()).code(131214)
                        .message("Branch successfully get all").success(true).build());
    }


    @Operation(summary = "This API is used for get all fixed branch", responses = {
            @ApiResponse(responseCode = "200", description = "get all fixed Branch", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @GetMapping(GET_ALL_PAGEABLE_URL)
    public ResponseEntity<ResponseData<?>> getAllByPageable(@RequestParam(required = false, defaultValue = "10") Integer size,
                                                            @RequestParam(required = false, defaultValue = "1") @Min(value = 1) Integer page) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
        return ResponseEntity.status(200)
                .body(ResponseData.builder().data(service.getAllFixed(pageRequest)).code(131215)
                        .message("Branch successfully get all fixed").success(true).build());
    }


}
