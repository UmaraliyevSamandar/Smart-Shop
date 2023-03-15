package com.example.smartwop.model

class BaseResponseModel <T>(
    val success: Boolean=true,
    val data: T,
    val message: String,
    val error_code: Int
)