package io.bhupendra.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Category {

    private String id;
    private String description;

    private Set<Recipe> recipes;

}
