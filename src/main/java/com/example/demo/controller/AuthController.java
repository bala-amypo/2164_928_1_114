@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        // (skip real authentication for now)
        String token = jwtTokenProvider.generateToken(request.getUsername());

        return ResponseEntity.ok(
                new AuthResponse(token)
        );
    }
}
