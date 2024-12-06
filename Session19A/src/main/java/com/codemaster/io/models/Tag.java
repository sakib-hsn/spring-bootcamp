package com.codemaster.io.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tag_table")
public class Tag {

    @Id
    private String id;

    private String displayNameEn;

    private String displayNameBn;

    // for bidirectional
//    @ManyToMany(mappedBy = "tags")
//    List<Product> products;
}
