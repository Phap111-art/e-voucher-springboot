package com.example.evoucherproject.mapper;

import org.modelmapper.ModelMapper;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <T> T toEntity(Object dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    public static <T> T toDTO(Object object, Class<T> dtoClass) {
        return modelMapper.map(object, dtoClass);
    }
    public static <T> List<T> toDTOList(List<?> objects, Class<T> dtoClass) {
        ModelMapper modelMapper = new ModelMapper();

        return objects.stream()
                .map(object -> modelMapper.map(object, dtoClass))
                .collect(Collectors.toList());
    }
}