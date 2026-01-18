package com.speedrun.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryResponseDto {
    private List<CategoryDto> data;

    public List<CategoryDto> getData() { return data; }
    public void setData(List<CategoryDto> data) { this.data = data; }
}