package com.br.yurisimao.secondarydb.entity;

import com.br.yurisimao.primarydb.entity.PrimaryEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "SECONDARY_DATABASE_TEST")
public class SecondaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PROCESSED_ITEM")
    private Long processedItem;

    @Column(name = "REFERENCE_UUID")
    private String uuid;

    public static SecondaryEntity toSecondaryEntity(@NotNull final PrimaryEntity primaryEntity) {

        final SecondaryEntity secondaryEntity = new SecondaryEntity();
        secondaryEntity.setProcessedItem(primaryEntity.getId());
        secondaryEntity.setUuid(primaryEntity.getUuid());

        return secondaryEntity;

    }

}
