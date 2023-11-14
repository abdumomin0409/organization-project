package com.company.organization.controller.auth;

import com.company.organization.controller.BaseController;
import com.company.organization.domain.user.User;
import com.company.organization.enums.SmsCodeType;
import com.company.organization.payload.auth.RefreshTokenRequest;
import com.company.organization.payload.auth.TokenResponse;
import com.company.organization.payload.user.UserResetPasswordDTO;
import com.company.organization.payload.user.UserSignInDto;
import com.company.organization.payload.user.UserSignUpDto;
import com.company.organization.payload.user.UserSmsDto;
import com.company.organization.response.ResponseData;
import com.company.organization.service.auth.AuthService;
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
@RequestMapping(AUTH_URL)
@Tag(name = "Authenticate users", description = "This API is used for authenticate users")
public class AuthController extends BaseController<AuthService> {

    public AuthController(AuthService service) {
        super(service);
    }

    @Operation(summary = "This API is used for register users", responses = {
            @ApiResponse(responseCode = "201", description = "User Successfully Registered", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<ResponseData<?>> register(@Valid @RequestBody UserSignUpDto dto) {
        return ResponseEntity.status(201)
                .body(ResponseData.builder().data(this.service.signUp(dto)).code(121212)
                        .message("User successfully registered").success(true).build());
    }


    @Operation(summary = "This API is used for checked activate code user", responses = {
            @ApiResponse(responseCode = "200", description = "User Successfully Checked Activated Code", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))
    })
    @PostMapping("/activate")
    public ResponseEntity<ResponseData<?>> activate(@Valid @RequestBody UserSmsDto dto) {
        String activate = this.service.activate(dto);
        return ResponseEntity.ok(null);
    }


    @Operation(summary = "This API is used for login user", responses = {
            @ApiResponse(responseCode = "200", description = "User Successfully Login", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))
    })
    @PostMapping("/get/accessToken")
    public ResponseEntity<ResponseData<TokenResponse>> getAccessToken(@Valid @RequestBody UserSignInDto dto) {
        TokenResponse login = this.service.getAccessToken(dto);
        return ResponseEntity.ok(null);
    }


    @Operation(summary = "This API is used for refresh token generation", responses = {
            @ApiResponse(responseCode = "200", description = "Refresh Token Generated", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))
    })
    @PostMapping("/refresh/token")
    public ResponseEntity<ResponseData<?>> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        TokenResponse refreshedToken = this.service.refreshToken(refreshTokenRequest);
        return ResponseEntity.ok(ResponseData.builder().code(4545).data(refreshedToken).build());
    }

    @Operation(summary = "This API is used for user activating users through the activation code that was sent via SMS", responses = {
            @ApiResponse(responseCode = "200", description = "User activated", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @PostMapping("/code/resend")
    public ResponseEntity<ResponseData<Void>> resendCode(@RequestParam String phoneNumber) {
        this.service.resendCode(phoneNumber, SmsCodeType.ACTIVATION);
        return ResponseEntity.ok(null);
    }


    @Operation(summary = "This API is used for user that forgot password ", responses = {
            @ApiResponse(responseCode = "200", description = "Sms code sent", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @PostMapping("/forget/password")
    public ResponseEntity<ResponseData<Void>> forgetPasswordRequest(@RequestParam String phoneNumber) {
        this.service.resendCode(phoneNumber, SmsCodeType.FORGET_PASSWORD);
        return ResponseEntity.ok(null);
    }


    @Operation(summary = "This API is used for user ", responses = {
            @ApiResponse(responseCode = "200", description = "Changed user's password ", content = @Content(schema = @Schema(implementation = ResponseData.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ResponseData.class)))})
    @PostMapping("/forget/password/activate")
    public ResponseEntity<ResponseData<Void>> forgetPasswordActivate(@Valid @RequestBody UserResetPasswordDTO dto) {
        this.service.forgetPasswordActivate(dto);
        return ResponseEntity.ok(null);
    }


}
