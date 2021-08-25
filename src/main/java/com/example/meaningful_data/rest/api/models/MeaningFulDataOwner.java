package com.example.meaningful_data.rest.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Authot: Albert Akimov
 * @Date: 23.08.2021
 * @Description:
 */
@Entity
@Table(name = "meaningful_data_owner")
@Data
public class MeaningFulDataOwner extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "inn")
    private Integer inn;

/*    @OneToMany(mappedBy = "meaningFulDataOwner",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "meaningFulDataOwner")
    private List<MeaningFulData> meaningFulData;*/
}
