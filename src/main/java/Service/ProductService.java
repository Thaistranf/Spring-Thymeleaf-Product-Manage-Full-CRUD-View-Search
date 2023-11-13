package Service;

import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService implements IProductService{
    //Tao 1 doi tuong list de luu thong tin product
    List<Product> productList = new ArrayList<>();
    //Constructor de add thong tin product vao list
    public ProductService(){
        productList.add(new Product(1, "May tinh", 45000, "MT", "Dell"));
        productList.add(new Product(2, "May giat", 26000, "MG", "Toshiba"));
        productList.add(new Product(3, "Noi com", 15000, "NC", "Cookcu"));
        productList.add(new Product(4, "Tu lanh", 75000, "TL", "Sharp"));
        productList.add(new Product(5, "Ti vi", 20000, "TV", "Sony"));
    }

    public List<Product> productList(){
        return productList;
    }

    @Override
    public void add(Product product) {
        //Id duoc tao random tu dong
        product.setId((int)(Math.random() * 100));
        //Dung ham add de them
        productList.add(product);
    }

    @Override
    public void update(int id, Product product) {
        //Tao 1 doi tuong product duoc tim theo id
        Product product1 = this.findById(id);
        //Neu doi tuong product co ton tai trong list thi sua
        if(product1 != null){
            //set goi ra thuoc tinh (vd: name) can sua, value trong set la thu can sua
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            product1.setDescription(product.getDescription());
            product1.setBrand(product.getBrand());
        }
    }

    @Override
    public void delete(int id) {
        //Tao 1 doi tuong product duoc tim theo id
        Product product1 = this.findById(id);
        //Neu doi tuong product co ton tai trong list thi xoa
        if(product1 != null){
            //doi tuong de luu ds se goi ham remove de xoa doi tuong product can xoa co trong ds
            productList.remove(product1);
        }
    }

    @Override
    public Product findById(int id) {
        //Duyet list => neu ton tai id thi tra ve doi tuong
        for (Product product: productList) {
            if (product.getId() == id){
                return product;
            }
        }
        //id khong ton tai tra ve null
        return null;
    }

    @Override
    public List<Product> search(String keyword) {
        return productList.stream()
                .filter(product -> product.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

}
