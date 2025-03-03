package com.zambeyzz.MovieAPIDevelopment.mapper;

import com.zambeyzz.MovieAPIDevelopment.dto.MovieRequestDTO;
import com.zambeyzz.MovieAPIDevelopment.dto.MovieResponseDTO;
import com.zambeyzz.MovieAPIDevelopment.entity.Movie;
import com.zambeyzz.MovieAPIDevelopment.enums.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    // Convert from Entity to ResponseDTO
    @Mapping(source = "director", target = "director")
    @Mapping(source = "genre", target = "genre")
    MovieResponseDTO toResponseDTO(Movie movie);

    @Mapping(source = "director", target = "director")
    @Mapping(source = "genre", target = "genre")
    Movie toEntity(MovieRequestDTO movieRequestDTO);


}

