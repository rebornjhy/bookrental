package bookrental;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long>{
    public Product findByProductId(long productId);

}