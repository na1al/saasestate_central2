package com.saasestate.app.entity;

import com.saasestate.app.component.parser.dto.Item;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Table(indexes = @Index(columnList = "state ASC"))
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class EstateData extends BaseEntity {

    @Setter
    @Getter
    @Id
    @Column(columnDefinition = "varchar(32)")
    public String id;

    @JsonIgnore
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    public Item data;

    @Getter
    @Setter
    @Column(columnDefinition = "boolean default false not null")
    protected boolean state = false;


}
