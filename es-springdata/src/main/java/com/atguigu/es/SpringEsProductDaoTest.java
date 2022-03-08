package com.atguigu.es;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

/**
 * @author 火火
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringEsProductDaoTest {

    @Autowired
    private ProductDao productDao;

    /**
     * 新增
     */
    @Test
    public void save(){
        Product product = new Product();
        product.setId(1L);
        product.setCategory("手机");
        product.setPrice(222.3);
        product.setTitle("华为手机");
        product.setImages("http://www.sjdkszjd.com/a.jpg");
        productDao.save(product);
    }

    /**
     * 修改
     */
    @Test
    public void update(){
        Product product = new Product();
        product.setId(1L);
        product.setCategory("手机");
        product.setPrice(222.3);
        product.setTitle("华为手机");
        product.setImages("http://www.sjdkszjd.com/a.jpg");
        productDao.save(product);
    }

    @Test
    public void findById(){
        Product product = productDao.findById(1L).get();
        System.out.println(product);
    }
    @Test
    public void findAll(){
        Iterable<Product> products = productDao.findAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Test
    public void delete(){
        productDao.deleteById(1L);
        Product product = new Product();
        product.setId(1L);
        productDao.delete(product);
    }

    @Test
    public void saveAll() {
        ArrayList<Product> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setId(1L);
            product.setCategory("手机");
            product.setPrice(222.3);
            product.setTitle("华为手机");
            product.setImages("http://www.sjdkszjd.com/a.jpg");
            arrayList.add(product);
        }
        productDao.saveAll(arrayList);
    }

    @Test
    public void findByPageable() {
        Sort orders = Sort.by(Sort.Direction.DESC, "id");
        int curr = 0;
        int pageSize = 5;
        PageRequest pageRequest = PageRequest.of(curr, pageSize, orders);
        Page<Product> daoAll = productDao.findAll(pageRequest);
        for (Product product : daoAll) {
            System.out.println(product);
        }
    }

}
