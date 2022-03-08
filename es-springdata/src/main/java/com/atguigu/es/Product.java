package com.atguigu.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "product", shards = 3, replicas = 1)
public class Product {
    /**
     * @Field(type=FieldType.Text, analyzer=“ik_max_word”) 表示该字段是一个文本，并作最大程度拆分 相反ik_smart最细粒度差分，默认建立索引
     *
     * @Field(type=FieldType.Text,index=false) 表示该字段是一个文本，不建立索引
     *
     * @Field(type=FieldType.Date) 表示该字段是一个文本，日期类型，默认不建立索引
     *
     * @Field(type=FieldType.Long) 表示该字段是一个长整型，默认建立索引
     *
     * @Field(type=FieldType.Keyword) 表示该字段内容是一个文本并作为一个整体不可分，默认建立索引
     *
     * @Field(type=FieldType.Float) 表示该字段内容是一个浮点类型并作为一个整体不可分，默认建立索引
     */
    @Id
    private Long id;//商品唯一标识
    @Field(type = FieldType.Text)
    private String title;//商品名称
    @Field(type = FieldType.Keyword)
    private String category;//分类名称
    @Field(type = FieldType.Double)
    private Double price;//商品价格
    @Field(type = FieldType.Keyword, index = false)
    private String images;//图片地址 不建立索引，默认建立索引
}
