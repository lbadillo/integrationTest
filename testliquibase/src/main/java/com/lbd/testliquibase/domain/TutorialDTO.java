package com.lbd.testliquibase.domain;


import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TutorialDTO implements Serializable {

    @Nullable
    private int id;
    private String name;
    private String description;
    private Long duration;

}
