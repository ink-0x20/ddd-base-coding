package com.inkblogdb.ddd.presentation.error;

public record ErrorResponse(
    String type,
    String message,
    String detail
) {

}
