package com.example.meaningful_data.rest.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

/**
 * @Authot: Albert Akimov
 * @Date: 23.08.2021
 * @Description:
 */
@Entity
@Table(name = "meaningful_data")
@Data
public class MeaningFulData extends AbstractEntity {

    @Column(name = "access")
    private String access;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type")
    private MeaningFulDataType meaningFulDataType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_own")
    private MeaningFulDataOwner meaningFulDataOwner;

}
