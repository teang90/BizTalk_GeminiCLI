package com.biztone.converter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConvertResponse {
    private String converted_text;
    private String target_audience;
    private String original_text;
}
