package com.joalvarez.keycloakauth.data.dto.generals;

import java.util.List;

public record ResponseDTO (int code, String message, List<String> details) implements BaseDTO {}
